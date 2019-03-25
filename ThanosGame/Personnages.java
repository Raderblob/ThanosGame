package ThanosGame;

import ThanosGame.graphics.AnimatedPerson;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.terrain.TerrainMap;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;


public class Personnages {
    public Point2D mySize;
    private boolean marche;
    private boolean aDroite;
    public int compt; // nb de pas du personnage
    protected AnimatedPerson myAnimation;

    // CONSTRUCTEUR
    public Personnages(int x, int y) {
        mySize = new Point2D(10, 10);
        myAnimation = new AnimatedPerson(ImagesSaves.thanosSprites, new Point2D(400, 330), 100);
        this.marche=false; this.aDroite=true;
        this.compt=0;


}
    //GETTERS

    public Point2D getMySize() { return mySize; }
    public boolean isMarche() { return marche; }
    public boolean isaDroite() { return aDroite; }
    public int getCompt() { return compt; }
    public AnimatedPerson getMyAnimation() { return myAnimation; }


    //SETTERS


    public void setMySize(Point2D mySize) { this.mySize = mySize; }
    public void setMyAnimation(AnimatedPerson myAnimation) { this.myAnimation = myAnimation; }
    public void setMarche(boolean marche) { this.marche = marche; }
    public void setaDroite(boolean aDroite) { this.aDroite = aDroite; }
    public void setCompt(int compt) { this.compt = compt; }

    // METHODES UTILISABLES

    // Deplacement du personnage

    public void draw(GraphicsContext gc) {
        myAnimation.draw(gc, new Point2D(this.x, this.y, new Point2D(mySize.getX() * 2, mySize.getY() * 2));
    }

    public Image marcher(int freq, String nom){
        String a;
        ImageIcon figure = new ImageIcon();
        Image image;

        if (this.marche == false){
            if (this.aDroite==true){ a="/images"/+ nom +"ArrêtDroite.png";}
            else {a="/image/"+ nom +"ArrêtGauche.png ";}
        }
        else {
            compt++;
            if (freq==0) {
                if (this.aDroite == true) {
                    a = "/images" / +nom + "ArrêtDroite.png";
                } else {
                    a = "/image/" + nom + "ArrêtGauche.png ";
                }
            } else {
                if (this.aDroite == true) {
                    a = "/images" / +nom + "MarcherDroite.png";
                } else {
                    a = "/image/" + nom + "MarcherGauche.png ";
                }
            }
            if (this.compt ==2*freq){this.compt=0;}

        }

        figure = new ImageIcon(getClass().getResource(a));
        image = figure.getImage();
        return image;
    }
    public void deplacer (){

    }
    public void

}
