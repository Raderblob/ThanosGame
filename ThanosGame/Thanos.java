package ThanosGame;

import ThanosGame.graphics.AnimatedImage;
import ThanosGame.terrain.TerrainMap;
import ThanosGame.weapons.player.Gant;
import ThanosGame.weapons.player.Stone;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import resources.AudioSaves;
import resources.ImagesSaves;

public class Thanos extends PlayerClass{
    public Gant infinity ;
    private AnimatedImage animatedShield;
    public boolean secondary;
    public Thanos(int PV){
        super();
        myShield=0;
        this.PV=PV ;
        maxPv = PV;
        this.infinity= new Gant(this);
        animatedShield = new AnimatedImage(ImagesSaves.shieldSprite,new Point2D(556,556),1000);
        secondary=false;
    }

    @Override
    public void run(TerrainMap currentTerrain, World currentWorld, double currentNanoTime) {
        super.run(currentTerrain, currentWorld, currentNanoTime);



        if (destroyAt.getX() != -1) {
            if(!secondary) {
                infinity.action(currentTerrain, currentWorld, destroyAt);
            }else{
                infinity.secondaryAction(currentTerrain, currentWorld, destroyAt);
                secondary = false;
            }
            destroyAt = new Point2D(-1, -1);
        }

        if(PV <=0){
            AudioSaves.deathSound.play();
            currentWorld.myGame.switchWorlds(0);
            PV = maxPv;
        }
    }



    public void addStone(Stone nStone){
        infinity.addStones(nStone);
    }


    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.save();
        gc.setGlobalAlpha(Math.min(myShield/maxPv,1));
        animatedShield.draw(gc,new Point2D(myPosition.getX() - mySize.getX() - getCameraPosition().getX(), myPosition.getY() - mySize.getY() - getCameraPosition().getY()),mySize.multiply(2));
        gc.restore();
    }
}

 
