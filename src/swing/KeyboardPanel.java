package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class KeyboardPanel extends JPanel {
private Image img;

    public KeyboardPanel() {
        super();
        //disegna il bordo superiore nero
        setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));
        img = new ImageIcon("keyboard.png").getImage();
    }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);      
    //scala e disegna l'immagine sul pannello
    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null); 
  }
}
