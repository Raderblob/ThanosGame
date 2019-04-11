package ThanosGame.graphics;

import ThanosGame.Game;
import ThanosGame.Thanos;
import ThanosGame.enemies.Boss;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class GraphicalUserInterface {
    private final Thanos thanos;
    private final LinkedList<PopupText> tutorialMessages;
    private long countDownStart=-1;

    public GraphicalUserInterface(Thanos thanos) {
        this.thanos = thanos;
        tutorialMessages = new LinkedList<>();
        addTutorial("Use the portals to go to the different world, for the moment you only have one unlocked", new Point2D(600, 100), 0);
        addTutorial("To move simply use either ad/qd and to jump use space/q/w\nand i to open the inventory", new Point2D(800, 200), 0);
    }

    public void addTutorial(String msg, Point2D messagePosition, int worldType) {
        PopupText nMsg = new PopupText(msg, messagePosition, 20000, messagePosition, worldType);
        if(!tutorialMessages.contains(nMsg)){
            tutorialMessages.add(nMsg);
        }

    }

    public void draw(GraphicsContext gc, Boss levelBoss, int selectedWorld) {
        for (int i = 0; i < thanos.infinity.stones.length; i++) {
            if (thanos.infinity.selectedStone == i) {
                gc.setFill(Color.RED);
            } else {
                gc.setFill(Color.BLACK);
            }
            gc.fillRect(80 + i * 200, Game.winParam.getY() - 60, 100, 50);
            gc.setFill(Color.BLACK);
            gc.fillRect(80 + i * 200, Game.winParam.getY() - 15, 100, 5);
            gc.setFill(Color.GREEN);
            gc.fillRect(80 + i * 200, Game.winParam.getY() - 15, 100 * (thanos.infinity.stones[i].getCurrentCoolDown()), 5);
            gc.setFill(Color.WHITE);
            gc.fillText(thanos.infinity.stones[i].toString(), 100 + i * 200, Game.winParam.getY() - 30);
        }
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 2, 100, 25);
        gc.setFill(Color.RED);
        gc.fillRect(0, 2, 100 * thanos.getHp(), 5);
        gc.fillText(thanos.infinity.nbAme + "", 50, 23);

        for (PopupText tM : tutorialMessages) {
            tM.testTrigger(thanos.myPosition, selectedWorld);
            tM.renderMe(gc, thanos.getCameraPosition().getX());
        }
        if(levelBoss!=null){
            if(levelBoss.pointOnScreen(levelBoss.myPosition)) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 30, (Game.winParam.getX()), 25);
                gc.setFill(Color.RED);
                gc.fillRect(0, 30, (Game.winParam.getX()) * levelBoss.getHp(), 25);
                gc.setFill(Color.YELLOW);
                gc.fillText("Boss", Game.winParam.getX() / 2, 42);
            }
        }

        if(thanos.finishedGame){
            gc.setFill(Color.PURPLE);
            gc.fillRect(10,10,Game.winParam.getX()-10,Game.winParam.getY()-10);
            gc.setFill(Color.WHITE);
            if(countDownStart==-1){
                countDownStart=System.currentTimeMillis();
            }
            long COUNTDOWNLENGTH = 5000;
            long timeLeft = (COUNTDOWNLENGTH -(System.currentTimeMillis()-countDownStart));
            gc.fillText("You Have Won the Game well Done\nSnapping fingures in : " + timeLeft,50,50);
            thanos.addHp(thanos.getMaxPv());
            if(timeLeft<0){
                System.exit(0);
            }
        }
    }
}
