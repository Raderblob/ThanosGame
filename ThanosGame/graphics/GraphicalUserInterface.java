package ThanosGame.graphics;

import ThanosGame.Game;
import ThanosGame.Thanos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphicalUserInterface {
    private Thanos thanos;
    public GraphicalUserInterface(Thanos thanos){
        this.thanos = thanos;

    }

    public void draw(GraphicsContext gc){
        for(int i=0;i<thanos.infinity.stones.length;i++){
            if(thanos.infinity.selectedStone == i){
                gc.setFill(Color.RED);
            }else{
                gc.setFill(Color.BLACK);
            }
            gc.fillRect(80+i*200,Game.winParam.getY()-60,100,50);
            gc.setFill(Color.BLACK);
            gc.fillRect(80+i*200,Game.winParam.getY()-15,100,5);
            gc.setFill(Color.GREEN);
            gc.fillRect(80+i*200,Game.winParam.getY()-15,100*(thanos.infinity.stones[i].getCurrentCoolDown()),5);
            gc.setFill(Color.WHITE);
            gc.fillText(thanos.infinity.stones[i].toString() ,100+i*200, Game.winParam.getY() -30);
        }
    }
}
