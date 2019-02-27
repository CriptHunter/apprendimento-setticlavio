package main;

import swing.KeyboardPanel;
import swing.StavePanel;
import source.Game;
import source.StaveNote;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainForm extends javax.swing.JFrame implements ActionListener {
    public MainForm() {
        initComponents();
        //per visualizzare l'applicazione al centro dello schermo
        this.setLocationRelativeTo(null);
        //posiziona i dialog al centro del frame
        dialogSettings.setLocationRelativeTo(this);
        dialogNewGame.setLocationRelativeTo(this);
        //rende trasparenti i pannelli che contengono i bottoni della tastiera
        panelWhiteKey.setOpaque(false);
        panelBlackKey.setOpaque(false);
        //rende trasparente il bottone impostazioni ma lascia l'immagine visibile
        btnSettings.setContentAreaFilled(false);
        btnSettings.setFocusPainted(false);
        JButton[] keyboardButtons = {btnC, btnCs, btnD, btnDs, btnE, btnF, btnFs, btnG, btnGs, btnA, btnAs, btnB};
        setKeyboardButtons(keyboardButtons);
        enableSettings(false);
        game = new Game((StavePanel)panelStave, lblScore, lblSequence, lblTime, dialogNewGame, dialogLblScore, 5);
    }
    
    // rende tutti i bottoni della tastiera trasparenti e imposta il listener
    // aggiunge un nome ad ogni bottone da 0 a 11
    // i bottoni devono essere passati in ordine di pitch
    private void setKeyboardButtons(javax.swing.JButton btn[]) {
        int name = 0;
        for (javax.swing.JButton b : btn) {
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
            b.addActionListener(this);
            b.setName(Integer.toString(name));
            name++;
        }
    }
    
    //per abilitare/disabilitare le impostazioni personalizzate
    private void enableSettings(boolean b) {
        lblKey.setEnabled(b);
        cbKey.setEnabled(b);
        ckbAltered.setEnabled(b);
        lblInterval.setEnabled(b);
        cbInterval.setEnabled(b);
    }
    
    //quando un tasto della tastiera viene premuto
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();
        int name = Integer.parseInt(clicked.getName());
        StaveNote note = new StaveNote();
        switch (name) {
            case 0: //DO
                note = new StaveNote(0, false);
                break;
            case 1: //DO#
                note = new StaveNote(0, true);
                break;
            case 2: //RE
                note = new StaveNote(1, false);
                break;
            case 3: //RE#
                note = new StaveNote(1, true);
                break;
            case 4: //MI
                note = new StaveNote(2, false);
                break;
            case 5: //FA
                note = new StaveNote(3, false);
                break;
           case 6: //FA#
                note = new StaveNote(3, true);
                break;
           case 7: //SOL
                note = new StaveNote(4, false);
                break;
            case 8: //SOL#
                note = new StaveNote(4, true);
                break;
            case 9: //LA
                note = new StaveNote(5, false);
                break;
            case 10: //LA#
                note = new StaveNote(5, true);
                break;
            case 11: //SI
                note = new StaveNote(6, false);
                break;
        }
        game.playNote(name);
        game.guessNote(note);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogNewGame = new javax.swing.JDialog();
        dialogPanelScore = new javax.swing.JPanel();
        dialogLblScoreText = new javax.swing.JLabel();
        dialogLblScore = new javax.swing.JLabel();
        dialogPanelButtons = new javax.swing.JPanel();
        dialogLblTryAgainText = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        dialogBtnYes = new javax.swing.JButton();
        dialogBtnNo = new javax.swing.JButton();
        dialogSettings = new javax.swing.JDialog();
        dialogSettingsPanel = new javax.swing.JPanel();
        lblLevel = new javax.swing.JLabel();
        cbLevel = new javax.swing.JComboBox<>();
        ckbSound = new javax.swing.JCheckBox();
        ckbAltered = new javax.swing.JCheckBox();
        lblKey = new javax.swing.JLabel();
        cbKey = new javax.swing.JComboBox<>();
        lblInterval = new javax.swing.JLabel();
        cbInterval = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        panelControls = new javax.swing.JPanel();
        lblScoreText = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        lblSequenceText = new javax.swing.JLabel();
        lblSequence = new javax.swing.JLabel();
        lblTimeText = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        btnSettings = new javax.swing.JButton();
        panelGame = new javax.swing.JPanel();
        panelStave = new StavePanel();
        panelKeyboard = new KeyboardPanel();
        panelBlackKey = new javax.swing.JPanel();
        btnFs = new javax.swing.JButton();
        btnCs = new javax.swing.JButton();
        btnGs = new javax.swing.JButton();
        btnDs = new javax.swing.JButton();
        btnAs = new javax.swing.JButton();
        panelWhiteKey = new javax.swing.JPanel();
        btnC = new javax.swing.JButton();
        btnD = new javax.swing.JButton();
        btnE = new javax.swing.JButton();
        btnF = new javax.swing.JButton();
        btnG = new javax.swing.JButton();
        btnA = new javax.swing.JButton();
        btnB = new javax.swing.JButton();

        dialogNewGame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogNewGame.setTitle("Fine partita");
        dialogNewGame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dialogNewGame.setMinimumSize(new java.awt.Dimension(400, 300));
        dialogNewGame.setModal(true);
        dialogNewGame.setModalExclusionType(null);
        dialogNewGame.getContentPane().setLayout(new java.awt.GridLayout(2, 1));

        dialogPanelScore.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 20));
        dialogPanelScore.setLayout(new java.awt.GridLayout(1, 2));

        dialogLblScoreText.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        dialogLblScoreText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dialogLblScoreText.setText("Punti:");
        dialogPanelScore.add(dialogLblScoreText);

        dialogLblScore.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        dialogLblScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dialogLblScore.setText("0");
        dialogPanelScore.add(dialogLblScore);

        dialogNewGame.getContentPane().add(dialogPanelScore);

        dialogPanelButtons.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 20, 20));
        dialogPanelButtons.setLayout(new java.awt.GridLayout(2, 2, 20, 0));

        dialogLblTryAgainText.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        dialogLblTryAgainText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dialogLblTryAgainText.setText("Riprovare?");
        dialogPanelButtons.add(dialogLblTryAgainText);
        dialogPanelButtons.add(filler1);

        dialogBtnYes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        dialogBtnYes.setText("Si");
        dialogBtnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogBtnYesActionPerformed(evt);
            }
        });
        dialogPanelButtons.add(dialogBtnYes);

        dialogBtnNo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        dialogBtnNo.setText("No");
        dialogBtnNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogBtnNoActionPerformed(evt);
            }
        });
        dialogPanelButtons.add(dialogBtnNo);

        dialogNewGame.getContentPane().add(dialogPanelButtons);

        dialogSettings.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogSettings.setTitle("Impostazioni");
        dialogSettings.setMinimumSize(new java.awt.Dimension(525, 325));
        dialogSettings.setModal(true);
        dialogSettings.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        dialogSettings.setPreferredSize(new java.awt.Dimension(525, 325));
        dialogSettings.setResizable(false);
        dialogSettings.setSize(new java.awt.Dimension(525, 325));

        dialogSettingsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dialogSettingsPanel.setMinimumSize(new java.awt.Dimension(525, 300));
        dialogSettingsPanel.setPreferredSize(new java.awt.Dimension(525, 275));
        dialogSettingsPanel.setLayout(new java.awt.GridLayout(5, 2, 10, 10));

        lblLevel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblLevel.setText("Livello di difficoltà:");
        dialogSettingsPanel.add(lblLevel);

        cbLevel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facilissimo", "Facile", "Normale", "Medio", "Difficile", "Difficilissimo", "Setticlavio", "Personalizzato" }));
        cbLevel.setSelectedIndex(6);
        cbLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLevelActionPerformed(evt);
            }
        });
        dialogSettingsPanel.add(cbLevel);

        ckbSound.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ckbSound.setText("Suoni");
        dialogSettingsPanel.add(ckbSound);

        ckbAltered.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ckbAltered.setSelected(true);
        ckbAltered.setText("Note alterate");
        dialogSettingsPanel.add(ckbAltered);

        lblKey.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblKey.setText("Chiavi musicali:");
        dialogSettingsPanel.add(lblKey);

        cbKey.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbKey.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Violino", "Violino, baritono, basso", "Tutte" }));
        cbKey.setSelectedIndex(2);
        dialogSettingsPanel.add(cbKey);

        lblInterval.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblInterval.setText("Intervallo massimo:");
        dialogSettingsPanel.add(lblInterval);

        cbInterval.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbInterval.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "4", "5", "6", "7", "8", "9" }));
        dialogSettingsPanel.add(cbInterval);

        btnSave.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSave.setText("Salva");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        dialogSettingsPanel.add(btnSave);

        btnCancel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnCancel.setText("Annulla");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        dialogSettingsPanel.add(btnCancel);

        javax.swing.GroupLayout dialogSettingsLayout = new javax.swing.GroupLayout(dialogSettings.getContentPane());
        dialogSettings.getContentPane().setLayout(dialogSettingsLayout);
        dialogSettingsLayout.setHorizontalGroup(
            dialogSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dialogSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dialogSettingsLayout.setVerticalGroup(
            dialogSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dialogSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Learn cleff placement on the stave");

        panelControls.setBackground(new java.awt.Color(0, 0, 0));
        panelControls.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        panelControls.setLayout(new java.awt.GridLayout(1, 3));

        lblScoreText.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblScoreText.setForeground(new java.awt.Color(255, 255, 255));
        lblScoreText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblScoreText.setText("Punteggio:");
        panelControls.add(lblScoreText);

        lblScore.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblScore.setForeground(new java.awt.Color(255, 255, 255));
        lblScore.setText("0");
        panelControls.add(lblScore);

        lblSequenceText.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSequenceText.setForeground(new java.awt.Color(255, 255, 255));
        lblSequenceText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSequenceText.setText("Sequenza:");
        panelControls.add(lblSequenceText);

        lblSequence.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSequence.setForeground(new java.awt.Color(255, 255, 255));
        lblSequence.setText("1");
        panelControls.add(lblSequence);

        lblTimeText.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTimeText.setForeground(new java.awt.Color(255, 255, 255));
        lblTimeText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTimeText.setText("Tempo:");
        panelControls.add(lblTimeText);

        lblTime.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText(" 00:00");
        panelControls.add(lblTime);

        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/settings-icon.png"))); // NOI18N
        btnSettings.setBorderPainted(false);
        btnSettings.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });
        panelControls.add(btnSettings);

        getContentPane().add(panelControls, java.awt.BorderLayout.PAGE_START);

        panelGame.setLayout(new java.awt.GridLayout(2, 0));

        panelStave.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelStaveLayout = new javax.swing.GroupLayout(panelStave);
        panelStave.setLayout(panelStaveLayout);
        panelStaveLayout.setHorizontalGroup(
            panelStaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 887, Short.MAX_VALUE)
        );
        panelStaveLayout.setVerticalGroup(
            panelStaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        panelGame.add(panelStave);

        panelKeyboard.setLayout(new java.awt.GridLayout(2, 7));

        btnFs.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnFs.setForeground(new java.awt.Color(255, 255, 255));
        btnFs.setText("<html>F#<br>Gb</html>");
        btnFs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFs.setPreferredSize(new java.awt.Dimension(65, 32));

        btnCs.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnCs.setForeground(new java.awt.Color(255, 255, 255));
        btnCs.setText("<html>C#<br>Db</html>");
        btnCs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCs.setPreferredSize(new java.awt.Dimension(65, 32));

        btnGs.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnGs.setForeground(new java.awt.Color(255, 255, 255));
        btnGs.setText("<html>G#<br>Ab</html>");
        btnGs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGs.setPreferredSize(new java.awt.Dimension(65, 32));

        btnDs.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDs.setForeground(new java.awt.Color(255, 255, 255));
        btnDs.setText("<html>D#<br>Eb</html>");
        btnDs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDs.setPreferredSize(new java.awt.Dimension(65, 32));

        btnAs.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnAs.setForeground(new java.awt.Color(255, 255, 255));
        btnAs.setText("<html>A#<br>Bb</html>");
        btnAs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAs.setPreferredSize(new java.awt.Dimension(65, 32));

        javax.swing.GroupLayout panelBlackKeyLayout = new javax.swing.GroupLayout(panelBlackKey);
        panelBlackKey.setLayout(panelBlackKeyLayout);
        panelBlackKeyLayout.setHorizontalGroup(
            panelBlackKeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBlackKeyLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(btnCs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(btnDs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(btnFs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnGs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(btnAs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        panelBlackKeyLayout.setVerticalGroup(
            panelBlackKeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBlackKeyLayout.createSequentialGroup()
                .addComponent(btnFs, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(btnCs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnGs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelKeyboard.add(panelBlackKey);

        panelWhiteKey.setLayout(new java.awt.GridLayout(1, 7));

        btnC.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnC.setText("C");
        panelWhiteKey.add(btnC);

        btnD.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnD.setText("D");
        panelWhiteKey.add(btnD);

        btnE.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnE.setText("E");
        panelWhiteKey.add(btnE);

        btnF.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnF.setText("F");
        panelWhiteKey.add(btnF);

        btnG.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnG.setText("G");
        panelWhiteKey.add(btnG);

        btnA.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnA.setText("A");
        panelWhiteKey.add(btnA);

        btnB.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnB.setText("B");
        panelWhiteKey.add(btnB);

        panelKeyboard.add(panelWhiteKey);

        panelGame.add(panelKeyboard);

        getContentPane().add(panelGame, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //listener bottone SI del dialog new game
    //fa iniziare una nuova partita
    private void dialogBtnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialogBtnYesActionPerformed
        game.newGame();
        dialogNewGame.dispose();
    }//GEN-LAST:event_dialogBtnYesActionPerformed

    //listener bottone NO del dialog new game
    //chiude l'applicazione
    private void dialogBtnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialogBtnNoActionPerformed
        System.exit(0);
    }//GEN-LAST:event_dialogBtnNoActionPerformed

    //rende visibili le impostazioni
    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        dialogSettings.setVisible(true);
    }//GEN-LAST:event_btnSettingsActionPerformed

    //salva le impostazioni selezionate
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int selectedIndex = cbLevel.getSelectedIndex();
        boolean alterate = false;
        int maxInterval = 0;
        int key = 0;
        boolean sound = ckbSound.isSelected();
 
        //modifico impostazioni in base al livello di difficoltà
        switch(selectedIndex) {
            case 0: //facilissimo
                key = 0;
                maxInterval = 3;
                alterate = false;
                break;
            case 1: //facile
                key = 0;
                maxInterval = 5;
                alterate = false;
                break;
            case 2: //normale
                key = 0;
                maxInterval = 5;
                alterate = true;
                break;  
            case 3: //medio
                key = 1;
                maxInterval = 5;
                alterate = true;
                break;
            case 4: //difficile
                key = 1;
                maxInterval = 7;
                alterate = true;
                break;
            case 5: //difficilissimo
                key = 2;
                maxInterval = 7;
                alterate = true;
                break;
            case 6: //setticlavio
                key = 3;
                maxInterval = 9;
                alterate = true;
                break;
            case 7: //personalizzato
                key = cbKey.getSelectedIndex();
                maxInterval = Integer.parseInt((String)cbInterval.getSelectedItem());
                alterate = ckbAltered.isSelected();
                break;
        }
        
        game.setNoteAlterate(alterate);
        game.setMaxInterval(maxInterval);
        game.keySelection(key);
        game.setSound(sound);
        game.newGame();
        dialogSettings.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    //listener bottone annulla delle impostazioni, chiude il dialog
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dialogSettings.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    //quando scelgo il livello nel combobox:
    //se è personalizzato abilito le impostazioni extra
    //altrimenti le disabilito
    private void cbLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLevelActionPerformed
        if(cbLevel.getSelectedIndex() == 7)
            enableSettings(true);
        else
            enableSettings(false);
    }//GEN-LAST:event_cbLevelActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //prova a impostare il look and feel di windows
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {            
                    System.err.println("Look and feel not supported");
                }
                MainForm mainForm = new MainForm();
                mainForm.setVisible(true);
            }
        });
    }

    private Game game;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnAs;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCs;
    private javax.swing.JButton btnD;
    private javax.swing.JButton btnDs;
    private javax.swing.JButton btnE;
    private javax.swing.JButton btnF;
    private javax.swing.JButton btnFs;
    private javax.swing.JButton btnG;
    private javax.swing.JButton btnGs;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSettings;
    private javax.swing.JComboBox<String> cbInterval;
    private javax.swing.JComboBox<String> cbKey;
    private javax.swing.JComboBox<String> cbLevel;
    private javax.swing.JCheckBox ckbAltered;
    private javax.swing.JCheckBox ckbSound;
    private javax.swing.JButton dialogBtnNo;
    private javax.swing.JButton dialogBtnYes;
    private javax.swing.JLabel dialogLblScore;
    private javax.swing.JLabel dialogLblScoreText;
    private javax.swing.JLabel dialogLblTryAgainText;
    private javax.swing.JDialog dialogNewGame;
    private javax.swing.JPanel dialogPanelButtons;
    private javax.swing.JPanel dialogPanelScore;
    private javax.swing.JDialog dialogSettings;
    private javax.swing.JPanel dialogSettingsPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel lblInterval;
    private javax.swing.JLabel lblKey;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblScoreText;
    private javax.swing.JLabel lblSequence;
    private javax.swing.JLabel lblSequenceText;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTimeText;
    private javax.swing.JPanel panelBlackKey;
    private javax.swing.JPanel panelControls;
    private javax.swing.JPanel panelGame;
    private javax.swing.JPanel panelKeyboard;
    private javax.swing.JPanel panelStave;
    private javax.swing.JPanel panelWhiteKey;
    // End of variables declaration//GEN-END:variables
}
