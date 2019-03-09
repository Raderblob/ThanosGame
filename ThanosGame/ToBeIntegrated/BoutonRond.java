package ThanosGame.ToBeIntegrated;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class BoutonRond extends JButton {

    private static final long serialVersionUID = 1L;
    private final int BULLE_WIDTH;
    private Color BULLE_COLOR;
    private Color BULLE_COLOR_D;
    private Color BULLE_COLOR_B;
    private Color currentColor;
    private Color couleurTexte;
    private Font font;
    private String texte="null";
    private int x = 4;
    private int y = 4;

    public BoutonRond(Color couleur, int taille) {
        BULLE_COLOR=couleur;
        BULLE_COLOR_D=couleur.darker();
        BULLE_COLOR_B=couleur.brighter();
        currentColor=BULLE_COLOR_D;
        BULLE_WIDTH=taille;
        initialize();
    }

    public BoutonRond(Color couleur, int taille, String texte, Font f, Color couleurTexte) {
        BULLE_COLOR=couleur;
        BULLE_COLOR_D=couleur.darker();
        BULLE_COLOR_B=couleur.brighter();
        currentColor=BULLE_COLOR;
        BULLE_WIDTH=taille;
        this.font=f;
        this.texte=texte;
        this.couleurTexte=couleurTexte;
        initialize();
    }

    public void setColor(Color c){
        this.BULLE_COLOR_D=c;
    }

    private void initialize() {
        this.setFocusPainted(false);
        this.setSize(new Dimension(BULLE_WIDTH + 10, BULLE_WIDTH + 10));
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.addMouseListener(new java.awt.event.MouseListener() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (isEnabled()) {
                    // on change la couleur
                    currentColor = BULLE_COLOR_B;
               }
            }

            public void mouseClicked(java.awt.event.MouseEvent e) {
            }

            public void mousePressed(java.awt.event.MouseEvent e) {
                x-=2;
                y-=2;
            }

            public void mouseReleased(java.awt.event.MouseEvent e) {
                x+=2;
                y+=2;
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                if (isEnabled()) {
                    // on rétablit la couleur d'origine
                    // System.out.println("couleur d'origine");
                   currentColor = BULLE_COLOR_D;
                }
            }
        });
    }

    public void paint(Graphics arg0) {
        super.paint(arg0);
        Graphics2D g2d = (Graphics2D) arg0;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(currentColor);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawOval(x, y, BULLE_WIDTH, BULLE_WIDTH);
        GradientPaint gradient = new GradientPaint(BULLE_WIDTH / 2, y,
        currentColor, (BULLE_WIDTH) / 2, y + BULLE_WIDTH, Color.white);
        g2d.setPaint(gradient);
        g2d.fillOval(x, y, BULLE_WIDTH, BULLE_WIDTH);
        gradient = new GradientPaint(BULLE_WIDTH / 2, y, Color.white,
        BULLE_WIDTH / 2, y + BULLE_WIDTH / 2, new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), 0));
        g2d.setPaint(gradient);
        g2d.fillOval(x + BULLE_WIDTH / 5, y, 5 * BULLE_WIDTH / 8,
        BULLE_WIDTH / 3);
        
        if(this.texte!="null"){
            g2d.setFont(this.font);
            float[] dist = {0.1f, 0.3f};
            Color c = new Color(22,22,22,0);
            Color[] colors = {c, this.couleurTexte};
            Paint gp = new LinearGradientPaint(new Point2D.Float(0, 0),
            new Point2D.Float(0, getHeight()), dist, colors);
            g2d.setPaint(gp);
            FontMetrics fm = g2d.getFontMetrics();
            int x0 = (this.getWidth() - fm.stringWidth(this.texte)) / 2;
            int y0 = (fm.getAscent() + (this.getHeight() -
            (fm.getAscent() + fm.getDescent())) / 2);
            g2d.drawString(this.texte, x0, y0);
        }
    }
}
