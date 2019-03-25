package ThanosGame;

import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.menus.MenuInventaire;
import ThanosGame.menus.MenuPrincipal;
import ThanosGame.weapons.ReboundProjectile;
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
    private boolean playing;
    private int toShow =0;
    private Stage stage;
    private Scene scene;
    private MenuInventaire inventaire = new MenuInventaire();

    @Override
    public void start(Stage stage) {
        this.stage=stage;
        ImagesSaves.loadImages();
        System.out.println("Loading Game...");
        loadGame();
        Main.mainMenu = new MenuPrincipal(this);
        loadEvents();

    }

    private void loadEvents(){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                hideGame();
                Main.mainMenu.setVisible(true);
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
            }else if (key.getCode()== KeyCode.I){
                inventaire.setVisible(true);
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
            }else if (key.getCode()== KeyCode.I){
                //?
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, mouse -> {
            if (mouse.isPrimaryButtonDown()) {
                thanos.fireAt(mouse.getSceneX(), mouse.getSceneY());
            } else {
                System.out.println("Right Click for test projectile");
                gameWorlds.get(selectedWorld).worldProjectiles.add(new ReboundProjectile(new Point2D(mouse.getSceneX(), mouse.getSceneY()).add(thanos.getCameraPosition()), new Point2D(4, 3), 12, 5, Math.random() * 360));
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouse -> {

        });
    }

    private void loadGame(){
        root = new Group();
        scene = new Scene(root, winParam.getX(), winParam.getY());
        stage.setTitle("ThanosGame.Thanos rules the world");
        stage.setScene(scene);

        Canvas canvas = new Canvas(winParam.getX(), winParam.getY());
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        selectedWorld = 0;
        thanos = new PlayerClass();
        gameWorlds.add(new World(0, thanos));


        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (playing) {
                    gameLoop(gc);
                }
                if(toShow ==1){
                    stage.show();
                    toShow=0;
                }else if(toShow ==-1){


                    toShow=0;
                }
            }
        }.start();
        //Displaying the contents of the stage
        stage.setResizable(false);
    }

    public void gameLoop(GraphicsContext gc) { //the game loop
        long lastTime = System.nanoTime();

        gameWorlds.get(selectedWorld).runWorld(Math.min(lastLength * 0.0000001, 3));//run logic for the seletced world

        gc.clearRect(0, 0, 1000, 1000);//clear the game screen


        gameWorlds.get(selectedWorld).renderWorld(gc, root);//render the selected world


        do {
            lastLength = ((System.nanoTime() - lastTime));//do fps and capping calculations
        } while (lastLength < 10000000);
       // System.out.println("Fps :" + 1 / (lastLength * 0.000000001));
    }

    public void showGame(){
        playing=true;
        toShow=1;
    }
    public void hideGame(){
        playing=false;
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