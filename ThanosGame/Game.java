package ThanosGame;

import ThanosGame.graphics.GraphicalUserInterface;
import ThanosGame.graphics.images.ImagesSaves;
import ThanosGame.menus.MenuInventaire;
import ThanosGame.menus.MenuPrincipal;
import ThanosGame.weapons.player.PowerStone;
import ThanosGame.weapons.player.RealityStone;
import ThanosGame.weapons.player.SpaceStone;
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
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;



public class Game extends Application {
    private Thanos thanos;
    public static final Point2D winParam = new Point2D(800, 500);
    private World gameWorld;
    private int selectedWorld;
    private long lastLength;
    private Group root;
    private boolean playing;
    private int toShow =0;
    private Stage stage;
    private Scene scene;
    private GraphicalUserInterface gui;
    private MenuInventaire inventaire;

    @Override
    public void start(Stage stage) {

        this.stage=stage;
        ImagesSaves.loadImages();
        System.out.println("Loading Game...");
        loadGame();
        inventaire = new MenuInventaire(thanos);
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
            }else if(key.getCode() == KeyCode.DIGIT1){
                thanos.infinity.selectStone(0);
            }else if(key.getCode()==KeyCode.DIGIT2){
                thanos.infinity.selectStone(1);
            }else if(key.getCode()==KeyCode.DIGIT3){
                thanos.infinity.selectStone(2);
            }else if(key.getCode()==KeyCode.DIGIT4){
                thanos.infinity.selectStone(3);
            }else if (key.getCode()== KeyCode.I && selectedWorld==0){
                inventaire.setVisible(true);
                inventaire.uptade();
            }

        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, key -> {
            if (key.getCode() == Keyboard.left) {
                thanos.movingState = 0;
            } else if (key.getCode() == Keyboard.right) {
                thanos.movingState = 0;
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, mouse -> {
            if (mouse.isPrimaryButtonDown()) {
                thanos.fireAt(mouse.getSceneX(), mouse.getSceneY());
            } else {
                thanos.secondary=true;
                thanos.fireAt(mouse.getSceneX(), mouse.getSceneY());
            }

        });
        scene.addEventHandler(ScrollEvent.SCROLL,mouse->{
            if(mouse.getDeltaY()>0){
                thanos.infinity.selectNextStone();
            }else{
                thanos.infinity.selectPreviousStone();
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED,mouse->{
            if(!mouse.isPrimaryButtonDown()){
                thanos.secondary=true;
                thanos.fireAt(mouse.getSceneX(), mouse.getSceneY());
            }
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

        thanos = new Thanos(100);
        thanos.addStone(new RealityStone(thanos));
        thanos.addStone(new SpaceStone(thanos));
        thanos.addStone(new PowerStone(thanos));

        gui = new GraphicalUserInterface(thanos);


        switchWorlds(1);

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

        gameWorld.runWorld(Math.min(lastLength * 0.0000001, 3));//run logic for the selected world

        gc.clearRect(0, 0, 1000, 1000);//clear the game screen


        gameWorld.renderWorld(gc, root);//render the selected world
        gui.draw(gc);

        lastLength = ((System.nanoTime() - lastTime));
        System.out.println("Fps :" + 1 / (lastLength * 0.000000001));
        do {
            lastLength = ((System.nanoTime() - lastTime));//do fps and capping calculations
        } while (lastLength < 10000000);

    }

    public void switchWorlds(int newWorld){
        if(gameWorld != null){
            gameWorld.dispose(root);
        }

        selectedWorld = newWorld;
        gameWorld = new World(selectedWorld,thanos,this);
        thanos.myPosition =gameWorld.getStarterPos();
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