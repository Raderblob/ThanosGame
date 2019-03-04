import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Game extends Application {
    private PlayerClass thanos;
    private final Point2D winParam = new Point2D(800,600);
    private ArrayList<World> gameWorlds = new ArrayList<World>();
    private int selectedWorld;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, winParam.getX() , winParam.getY());
        stage.setTitle("Thanos rules the world");
        stage.setScene(scene);

        Canvas canvas = new Canvas( winParam.getX(), winParam.getY() );
        root.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();

        selectedWorld = 0;
        gameWorlds.add(new World(0,thanos));
        thanos= new PlayerClass();


        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.ESCAPE) {
                System.exit(0);
            }else if(key.getCode()==KeyCode.A){

            }else if(key.getCode() == KeyCode.D){

            }else if(key.getCode()==KeyCode.W){

            }else if(key.getCode() == KeyCode.S){

            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, (mouse) -> {

        });


        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                gameLoop(t,gc);
            }
        }.start();
        //Displaying the contents of the stage
        stage.show();
    }

    public void gameLoop(double currentNanoTime,GraphicsContext gc )
    {
        gameWorlds.get(selectedWorld).runWorld();

        gc.fillRect(0,0,1000,1000);

    }

    public static void launchGame(){
        launch();
    }
}   