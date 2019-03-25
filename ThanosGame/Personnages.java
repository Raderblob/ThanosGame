/*package ThanosGame;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Personnages {
    private int largeur, taille; // Taille perso
    private int x, y; // position
    private boolean marche;
    private boolean aDroite;
    public int compt; // nb de pas du personnage

    // CONSTRUCTEUR
    public Personnages(int x, int y, int largeur, int taille) {

    this.x=x; this.y=y;
    this.largeur=largeur;this.taille=taille;
    this.marche=false; this.aDroite=true;
    this.compt=0;
}
    //GETTERS

    public int getLargeur() {return largeur; }
    public int getTaille() {return taille; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isMarche() { return marche; }
    public boolean isaDroite() { return aDroite; }

    //SETTERS

    public void setLargeur(int largeur) {this.largeur = largeur;}
    public void setTaille(int taille) { this.taille = taille; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setMarche(boolean marche) { this.marche = marche; }
    public void setaDroite(boolean aDroite) { this.aDroite = aDroite; }
    public void setCompt(int compt) { this.compt = compt; }

    // METHODES UTILISABLES
    // Deplacement du personnage

    public Image marcher(int freq, String nom){
        String a;
        ImageIcon figure;
        Image image;

        if (this.marche == false){
            if (this.aDroite==true){ a="/images"/+ nom +"ArrêtDroite.png";}
            else {a="/image/"+ nom +"ArrêtGauche.png ";}
        }
        else {
            compt++;
        }


    }

}
*/