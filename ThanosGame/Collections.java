package ThanosGame;

import java.util.ArrayList;

public class Collections {
    public Shield shield1;  public Shield shield2;  public Shield shield3;  public Shield shield4; public Shield shield6; public Shield shield7; public Shield shield8; public Shield shield9; public Shield shield10;
    public Shield shield11;public Shield shield12;  public Shield shield13; public Shield shield14; public Shield shield16; public Shield shield17; public Shield shield18; public Shield shield19; public Shield shield20;
    public Shield shield15;

    public ArrayList<Shield> tabShield;
    private tabShield = new ArrayList<Shield>();
    this.tabShield.add(this.shield1); this.tabShield.add(this.shield2); this.tabShield.add(this.shield3); this.tabShield.add(this.shield4);
    this.tabShield.add(this.shield5); this.tabShield.add(this.shield6); this.tabShield.add(this.shield7); this.tabShield.add(this.shield8);
    this.tabShield.add(this.shield9); this.tabShield.add(this.shield10); this.tabShield.add(this.shield11); this.tabShield.add(this.shield12);
    this.tabShield.add(this.shield13); this.tabShield.add(this.shield14); this.tabShield.add(this.shield15); this.tabShield.add(this.shield16);
     this.tabShield.add(this.shield7); this.tabShield.add(this.shield18); this.tabShield.add(this.shield19); this.tabShield.add(this.shield20);

    shield1 = new Shield (5,6);
    shield2 = new Shield (x,y);shield3 = new Shield (x,y);shield4 = new Shield (x,y);
    shield5 = new Shield (x,y);shield6 = new Shield (x,y);shield7 = new Shield (x,y);shield8 = new Shield (x,y);
    shield9 = new Shield (x,y);shield10 = new Shield (x,y);shield10 = new Shield (x,y);shield12 = new Shield (x,y);
    shield13 = new Shield (x,y);shield14 = new Shield (x,y);shield15 = new Shield (x,y);shield16 = new Shield (x,y);
    shield17 = new Shield (x,y);shield18 = new Shield (x,y);shield19 = new Shield (x,y);shield20 = new Shield (x,y);

}
    // Deplacement dans méthode déplacement fond
    for (int i=0; i<this.tabShield.size();i++){ this.tabShield.get(i).deplacement();}

    // Image Shield

    for (int i=0; i<this.tabShield.size(); i++){
        if (this.tabShield.get(i).isAlive()==true){
            g.drawImage(this.tabShield.get(i).marche(,), this.tabShield.get(i).getX(), this.tabShield.get(i).getY(), null);
        }else{
        g.drawImage(this.tabShield.get(i).die(), this.tabShield.get(i).getX(), this.tabShield.get(i).getY()+15, null);
        }
        }
