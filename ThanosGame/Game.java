package ThanosGame;

import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.weapons.Projectile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Game extends Application {
    private PlayerClass thanos;
    public static final Point2D winParam = new Point2D(800, 500);
    private ArrayList<World> gameWorlds = new ArrayList<World>();
    private int selectedWorld;
    private long lastLength;
    private Group root;

    @Override
    public void start(Stage stage) {
        ImagesSaves.loadImages();
        root = new Group();
        Scene scene = new Scene(root, winParam.getX(), winParam.getY());
        stage.setTitle("ThanosGame.Thanos rules the world");
        stage.setScene(scene);

        Canvas canvas = new Canvas(winParam.getX(), winParam.getY());
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        selectedWorld = 0;
        thanos = new PlayerClass();
        gameWorlds.add(new World(0, thanos));


        scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            } else if (key.getCode() == Keyboard.left) {
                thanos.movingState = -1;
            } else if (key.getCode() == Keyboard.right) {
                thanos.movingState = 1;
            } else if (key.getCode() == Keyboard.jump) {
                thanos.jump();
            } else if (key.getCode() == Keyboard.punch) {
                //Frapper
            } else if (key.getCode() == Keyboard.down) {
                //PEUT SERVIR...
            }
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, key -> {
            if (key.getCode() == Keyboard.left) {
                thanos.movingState = 0;
            } else if (key.getCode() == Keyboard.right) {
                thanos.movingState = 0;
            } else if (key.getCode() == Keyboard.jump) {

            } else if (key.getCode() == Keyboard.punch) {
                //thanos.fireAt(X,Y);
            } else if (key.getCode() == Keyboard.down) {
                //PEUT SERVIR...
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, mouse -> {
            if (mouse.isPrimaryButtonDown()) {
                thanos.fireAt(mouse.getSceneX(), mouse.getSceneY());
            } else {
                System.out.println("Right Click");
                gameWorlds.get(selectedWorld).worldProjectiles.add(new Projectile(new Point2D(mouse.getSceneX(), mouse.getSceneY()).add(thanos.getCameraPosition()), new Point2D(4, 3), 12, new Point2D(5, 0)));
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouse -> {

        });


        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gameLoop(gc);
            }
        }.start();
        //Displaying the contents of the stage
        stage.setResizable(false);
        stage.show();


    }

    public void gameLoop(GraphicsContext gc) { //the game loop
        long lastTime = System.nanoTime();

        gameWorlds.get(selectedWorld).runWorld(Math.min(lastLength * 0.0000001, 3));//run logic for the seletced world

        gc.clearRect(0, 0, 1000, 1000);//clear the game screen


        gameWorlds.get(selectedWorld).renderWorld(gc, root);//render the selected world

        lastLength = ((System.nanoTime() - lastTime));
        do {
            lastLength = ((System.nanoTime() - lastTime));//do fps and capping calculations
        } while (lastLength < 10000000);
        System.out.println("Fps :" + 1 / (lastLength * 0.000000001));
    }

    @Override
    public void stop() {
        System.out.println("Stage is closing");
        System.exit(0);
    }

    public static void launchGame() {
        launch();
    }
}   