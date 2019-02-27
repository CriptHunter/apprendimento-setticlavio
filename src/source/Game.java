package source;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Timer;
import swing.StavePanel;
import jm.JMC;
import jm.music.data.Note;
import jm.util.Play;

public class Game implements JMC { 
    //-----------------------componenti swing-----------------------------------
    private StavePanel panelStave;
    private JDialog dialogNewGame;
    private JLabel lblDialogScore;
    private JLabel lblScore; 
    private JLabel lblSequence;
    private JLabel lblTime;
    //---------------------variabili di gioco----------------------------------- 
    private int currentSequence; //indica a che sequenza si è arrivati
    private int correctAnswers; //il numero di risposte corrette
    private int wrongAnswers; // il numero di risposte sbagliate
    private long startTime; //indica quando è iniziata la partita, serve per calcolare minuti e secondi
    private int seconds;
    private int minutes;
    //---------------------impostazioni di gioco--------------------------------
    private int nSequences; //indica il numero di sequenze di note generate
    private boolean noteAlterate; //se vengono generate anche note alterate
    private int maxInterval; //intervallo in cui le note sono generate
    private boolean sound; // se ci sono i suoni
    //------------------------------chiavi musicali-----------------------------
    private final Clef chiaveBasso = new Clef(-2, 1, "\uD834\uDD22"); 
    private final Clef chiaveBaritono = new Clef(0, 3, "\uD834\uDD22");
    private final Clef chiaveViolino = new Clef(2, 6, "\uD834\uDD1E");
    private final Clef chiaveSoprano = new Clef(4, 4, "\uD834\uDD21");
    private final Clef chiaveMezzoSoprano = new Clef(2,2,"\uD834\uDD21");
    private final Clef chiaveContralto = new Clef(0, 0, "\uD834\uDD21");
    private final Clef chiaveTenore = new Clef(-2, 5, "\uD834\uDD21");  
    private Clef[] clefs;
    
    
    public Game(StavePanel panelStave, JLabel lblScore, JLabel lblSequence, JLabel lblTime,
                JDialog dialogNewGame, JLabel lblDialogScore, int nSequences) 
    {
        //Componenti swing che la classe Game modifica
        this.panelStave = panelStave;
        this.lblScore = lblScore;
        this.lblSequence = lblSequence;
        this.lblTime = lblTime;
        this.dialogNewGame = dialogNewGame;
        this.lblDialogScore = lblDialogScore;
        //variabili delle impostazioni
        this.nSequences = nSequences;
        this.noteAlterate = true;
        this.maxInterval = 9;
        this.sound = false;
        clefs = new Clef[]{chiaveBasso, chiaveBaritono, chiaveViolino, chiaveSoprano, 
                           chiaveMezzoSoprano, chiaveContralto, chiaveTenore};
        newGame();
    }
    
    //per iniziare una nuova partita, ripristina tutto allo stato iniziale
    public void newGame() {
        //ripristina i componenti swing
        this.lblScore.setText(" 0");
        this.lblSequence.setText("");
        this.lblTime.setText(" 00:00");
        //inizializza le variabili di gioco
        this.currentSequence = 0;
        this.correctAnswers = 0;
        this.wrongAnswers = 0;
        this.startTime = System.currentTimeMillis();
        this.seconds = 0;
        this.minutes = 0;
        this.panelStave.highlightedNote = 0;
        this.panelStave.noteSequence.clear();
        //appena avviato il gioco crea una sequenza di note e fa partire il timer
        nextNoteSequence();
        gameTimer.start();
    }
    
    //suona una nota
    //i pitch sono quelli del midi, 60 = DO centrale
    public void playNote(int pitch) {
        if(sound)
            Play.midi(new Note(pitch + 60, QUARTER_NOTE));
    }
    
    //quando l'utente seleziona una nota
    public void guessNote(StaveNote note) {
            if(panelStave.noteSequence.get(panelStave.highlightedNote).equals(note))
                correctAnswers ++;
            else
                wrongAnswers ++;
            lblScore.setText(" " + evalScore());
            //se non sono all'ultima nota della sequenza devo spostare l'evidenziatore
            //sulla nota successiva
            if(panelStave.highlightedNote < 4) {
                panelStave.highlightedNote ++;
                panelStave.repaint();  
            }
            //se ho appena indovinato l'ultima nota genero una nuova sequenza
            else
               nextNoteSequence();
    }
    
    private void randomNoteSequence(int n) {
        //svuoto la vecchia sequenza di note
        panelStave.noteSequence.clear();
        Random random = new Random();
        //genero una nuova chiave
        Clef randomClef = clefs[random.nextInt(clefs.length)];  
        panelStave.clef = randomClef;
        for(int i = 0; i < n; i++) {
            //numero random tra -8 e 8 per l'intervallo più grande (con tagli addizionali)
            int randomPosition;
            randomPosition = random.nextInt(maxInterval) * (random .nextBoolean() ? -1 : 1);
            //per decidere se una nota è alterata o no, è sempre false se le note alterate sono disattivate
            boolean isSharpOrFlat = false;
            boolean flat = false;
            boolean sharp = false;
            if(noteAlterate)
                isSharpOrFlat = random.nextBoolean();
            //se la nota è alterata deve scegliere random tra # e b
            if(isSharpOrFlat) {
                if(random.nextBoolean())
                    flat = true;
                else
                    sharp = true;
            }
            //crea una nota che ha pitch dipendente da posizione sul pentagramma e chiave musicale
            StaveNote randomNote = new StaveNote(randomClef.getMainNote(), sharp, flat, randomPosition);
            panelStave.noteSequence.add(randomNote);
        }
    }
    
    //viene chiamato una volta completata l'immissione di una sequenza di note e quando apro il programma
    private void nextNoteSequence() {
        //se ci sono ancora sequenze di note da visualizzare:
        //genera una nuova chiave + note random
        //sposta l'evidenziatore sulla prima nota
        if (currentSequence < nSequences) {
            randomNoteSequence(nSequences);
            panelStave.highlightedNote = 0;
            panelStave.repaint();
            currentSequence++;
            lblSequence.setText(" " + currentSequence + " / " + nSequences);
        }
        //se è finito il gioco apre il dialog di fine partita
        else {
            gameTimer.stop();
            lblDialogScore.setText(evalScore() + "");
            dialogNewGame.setVisible(true);
        }
    }
   
    //calcola il punteggio attuale in base al numero di chiavi utilizzate, alle note alterate,
    //all'intervallo massimo tra le note e al punteggio
    private int evalScore() {
        double bonus = 1;
        if(noteAlterate)
            bonus = bonus + 0.25;
        if(clefs.length == 7)
            bonus = bonus + 1;
        if(clefs.length == 3)
            bonus = bonus + 0.5;
        bonus = bonus + (double)maxInterval/4;
        double score = (correctAnswers - wrongAnswers)*bonus - (double)(minutes*60 + seconds)/10;
        //per avere una sola cifra decimale
        score =  ((double)((int)(score *10.0)))/10.0; 
        //moltiplico per 10 per ritornare un numero intero
        return (int)score*10;
    }
    
    public void setNoteAlterate(boolean noteAlterate) {
        this.noteAlterate = noteAlterate;
    }

    public void setMaxInterval(int maxInterval) {
        this.maxInterval = maxInterval;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }
    

    public void keySelection(int n) {
        switch (n) {
            case 0:
                clefs = new Clef[]{chiaveViolino};
                break;
            case 1:
                clefs = new Clef[]{chiaveBasso, chiaveBaritono, chiaveViolino};
                break;
            default:
                clefs = new Clef[]{chiaveBasso, chiaveBaritono, chiaveViolino, chiaveSoprano, chiaveMezzoSoprano, chiaveContralto, chiaveTenore};
                break;
        }
    }
    
    //timer per contare secondi e minuti da quando è iniziata la partita
    //action performed viene chiamato ogni secondo
    Timer gameTimer = new Timer(1000, new ActionListener(){
        String minutesTxt;
        String secondsTxt;
        @Override
        public void actionPerformed(ActionEvent e) {
            //calcola minuti e secondi
            seconds = (int)(System.currentTimeMillis() - startTime)/1000;
            minutes = seconds / 60;
            seconds = seconds - minutes * 60;
            //se i minuti e/o i secondi sono minori di 10, aggiunge uno 0 per avere sempre numeri a due cifre
            //esempio: 9 --> 09
            if (minutes < 10)
                minutesTxt = "0" + minutes;
            else
                minutesTxt = Integer.toString(minutes);
            if (seconds < 10)
                secondsTxt = "0" + seconds;
            else
                secondsTxt = Integer.toString(seconds);
            lblTime.setText(" " + minutesTxt + ":" + secondsTxt);
        }
    });
}


