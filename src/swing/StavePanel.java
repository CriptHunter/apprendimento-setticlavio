package swing;

import source.Clef;
import source.StaveNote;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPanel;

public class StavePanel extends JPanel {
    //altezza e larghezza del pannello
    private int width = 0;
    private int height = 0;
    //----------caratteri unicode corrispondenti a simboli musicali-------------
    private final String quarterNote = "\uD834\uDD5F";
    private final String noteHead = "\uD834\uDD58";
    private final String sharpSign = "\u266F";
    private final String flatSign = "\u266D";
    //--------------------------------------------------------------------------
    //--------------------variabili modificate da game--------------------------
    //array list che contiene le note da disegnare
    public ArrayList<StaveNote> noteSequence;
    //indica a quale nota della sequenza si è arrivati a rispondere
    public int highlightedNote = 0;
     //la chiave disegnata sul pentagramma
    public Clef clef;
    //--------------------------------------------------------------------------

    public StavePanel() {
        super();
        noteSequence = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        width = this.getWidth();
        height = this.getHeight();
        //antialiasing per i simboli musicali
        ((Graphics2D)g).setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        //font e dimensione dei simboli musicali, bravura è un font specifico per la notazione musicale
        Font f = new Font("Bravura", Font.PLAIN, height/4);
        g.setFont(f);
        //coordinata x da cui partono le righe del pentagramma
        int startX = width/5;
        //distanza tra le linee del pentagramma
        int linesDistance = height/12;
        //distanza tra ogni nota
        int notesDistance = (startX*4 - startX) / 6;
        drawStave(g, startX, height/2, linesDistance);
        drawNotes(g, startX, height/2, notesDistance, linesDistance);
        drawHighlight(g, startX, notesDistance, linesDistance);  
    }
    
    //disegna le 5 righe orizzontali del pentagramma
    private void drawStave(Graphics g, int x, int y, int distance) {
        g.drawLine(x, y, x*4, y);
        for(int i = 1; i < 3; i++) {
           g.drawLine(x, y + i*distance, x*4, y + i*distance);
           g.drawLine(x, y - i*distance, x*4, y - i*distance);
        }
    }
    
    private void drawNotes(Graphics g, int x, int y, int distance, int linesDistance) {
        //disegno la chiave
        g.drawString(clef.getUnicodeSign(), x, y + clef.getPosition()*linesDistance/2);
        //disegno le note
        int i = 0;
        for (StaveNote n : noteSequence) {
           i += distance;
           g.drawString(noteHead, x + i, y + n.getPosition() * linesDistance/2);
           if(n.isSharp())
                g.drawString(sharpSign, x + i - linesDistance*3/2, y + n.getPosition() * linesDistance/2);
           if(n.isFlat())
                g.drawString(flatSign, x + i - linesDistance*3/2, y + n.getPosition() * linesDistance/2);
           //disegno i tagli addizionali se ci sono
           int k = 0;
           if (Math.abs(n.getPosition()) > 5)
                do {
                    //la posizione dei tagli dipende dalla posizione della nota che può essere negativa o positiva
                    int spacing = 0;
                    if (n.getPosition() > 0)
                        spacing = linesDistance*2 + (k+1)*linesDistance;
                    else
                        spacing = -linesDistance*2 - (k+1)*linesDistance;
                    g.drawLine(x + i -linesDistance*2/3, y + spacing, x + i + linesDistance*3/2, y + spacing);
                    k++;
                } while (k < Math.abs(n.getPosition()) - 6);
        } 
    }
    
    //evidenzia la note che l'utente deve leggere
    //notedistance serve per calcolare lo spostamento sull'asse delle x dell'evidenziatore
    //linedistance serve per capire quanto deve essere largo l'evidenziatore
    private void drawHighlight(Graphics g, int x, int notesDistance, int linesDistance) {
        Graphics2D g2d = (Graphics2D)g;
        //giallo trasparente
        Color color = new Color(1, 1, 0, 0.5f);
        g2d.setPaint(color);
        g2d.fillRect(x + notesDistance*(highlightedNote+1) - linesDistance*15/10 , 0, linesDistance*4, this.getHeight());
    }
}