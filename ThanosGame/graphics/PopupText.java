package ThanosGame.graphics;


import ThanosGame.Main;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class PopupText {
    private final int TRIGGERDISTANCE = (int) Math.pow(500, 2);
    public String myText;
    private Point2D myPosition;
    private long showTime;
    private long lifeSpan;
    private boolean shown;
    private boolean visible;
    private Point2D myTrigger;
    private int worldTrigger;

    public PopupText(String mText, Point2D mPosition, long lifeSpan, Point2D myTrigger, int worldTrigger) {
        myText = mText;
        myPosition = mPosition;
        shown = false;
        visible = false;
        this.lifeSpan = lifeSpan;
        this.myTrigger = myTrigger;
        this.worldTrigger = worldTrigger;
    }

    private void showMe() {
        if (!shown) {
            visible = true;
            shown = true;
            showTime = System.currentTimeMillis();
        }
    }

    public void testTrigger(Point2D playerPosition, int currentWorld) {
        if (currentWorld == worldTrigger) {
            if (Main.getMagnitudeSquared(playerPosition.add(myTrigger.multiply(-1))) < TRIGGERDISTANCE) {
                showMe();
            }
        } else {
            hideMe();
        }
    }

    private void hideMe() {
        if (visible)
            visible = false;
    }

    public void renderMe(GraphicsContext gc, double camPos) {
        if (visible) {
            gc.fillText(myText, myPosition.getX() - camPos, myPosition.getY());
            if (System.currentTimeMillis() - showTime > lifeSpan) {
                hideMe();
            }
        }
    }



    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PopupText){
            return myText.equals(((PopupText) obj).myText);
        }else{
            return false;
        }
    }
}
