package ThanosGame;

import ThanosGame.graphics.GraphicalUserInterface;
import ThanosGame.menus.MenuInventaire;
import ThanosGame.menus.MenuPrincipal;
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
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import resources.ImagesSaves;

import java.awt.*;


public class Game extends Application {
    public AudioManager myAudio = new AudioManager();
    private Thanos thanos;
    public static final Point2D winParam = new Point2D(800, 500);
    public double windowScale = 1; //a window scale parameter
    private World gameWorld;
    private int selectedWorld;
    private long lastLength;
    private Group root;
    private boolean playing;
    private int toShow = 0;
    private Stage stage;
    private Scene scene;
    public GraphicalUserInterface gui;
    private MenuInventaire inventaire;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private Canvas backgroundImage;
    private long lastTime = System.nanoTime();

    @Override
    public void start(Stage stage) {

        try {
            this.stage = stage;
            ImagesSaves.loadImages();
            System.out.println("Loading Game...");
            loadGame();
            inventaire = new MenuInventaire(thanos);
            Main.mainMenu = new MenuPrincipal(this);
            loadEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }

        myAudio.restartAll();//run Music
    }

    private void loadEvents() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                hideGame();
                Main.mainMenu.setVisible(true);
            } else if (key.getCode() == Keyboard.left) {
                leftPressed = true;
            } else if (key.getCode() == Keyboard.right) {
                rightPressed = true;
            } else if (key.getCode() == Keyboard.jump) {
                thanos.jump();
            } else if (key.getCode() == KeyCode.DIGIT1) {
                thanos.infinity.selectStone(0);
            } else if (key.getCode() == KeyCode.DIGIT2) {
                thanos.infinity.selectStone(1);
            } else if (key.getCode() == KeyCode.DIGIT3) {
                thanos.infinity.selectStone(2);
            } else if (key.getCode() == KeyCode.DIGIT4) {
                thanos.infinity.selectStone(3);
            } else if (key.getCode() == KeyCode.I && selectedWorld == 0) {
                inventaire.setVisible(true);
                inventaire.update();
            }

        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, key -> {
            if (key.getCode() == Keyboard.left) {
                leftPressed = false;
            } else if (key.getCode() == Keyboard.right) {
                rightPressed = false;
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, mouse -> {
            Point2D realMouse = new Point2D(mouse.getSceneX(), mouse.getSceneY()).multiply(1 / windowScale);
            if (mouse.isPrimaryButtonDown()) {
                thanos.fireAt(realMouse.getX(), realMouse.getY());
            } else {
                thanos.overrideSecondary(realMouse);
            }

        });
        scene.addEventHandler(MouseEvent.MOUSE_RELEASED, mouse -> {
            thanos.desactivateSecondary();
        });
        scene.addEventHandler(ScrollEvent.SCROLL, mouse -> {
            if (mouse.getDeltaY() < 0) {
                thanos.infinity.selectNextStone();
            } else {
                thanos.infinity.selectPreviousStone();
            }
        });
        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouse -> {
            Point2D realMouse = new Point2D(mouse.getSceneX(), mouse.getSceneY()).multiply(1 / windowScale);
            if (!mouse.isPrimaryButtonDown()) {
                thanos.overrideSecondary(realMouse);
            }
        });
    }

    private void loadGame() {
        root = new Group();
        windowScale = Math.min((Toolkit.getDefaultToolkit().getScreenSize().height - 100) / winParam.getY(), (Toolkit.getDefaultToolkit().getScreenSize().width - 100) / winParam.getX());
        Scale tScale = new Scale(windowScale, windowScale);//autoscale to screen size
        root.getTransforms().add(tScale);
        scene = new Scene(root, winParam.getX() * windowScale, winParam.getY() * windowScale);
        stage.setTitle("ThanosGame.Thanos rules the world v1.2");
        stage.setScene(scene);

        Canvas canvas = new Canvas(winParam.getX(), winParam.getY());
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        backgroundImage = new Canvas(winParam.getX() * 2, winParam.getY());
        root.getChildren().add(backgroundImage);

        thanos = new Thanos(100);


        gui = new GraphicalUserInterface(thanos);

        switchWorlds(0);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {//this is the main game loop
                if (playing) {
                    gameLoop(gc);
                }
                if (toShow == 1) {
                    stage.show();
                    toShow = 0;
                } else if (toShow == -1) {
                    toShow = 0;
                    // stage.hide();
                }
            }
        }.start();

        stage.setResizable(false);//Make sure they don't change something they should not
    }

    public void gameLoop(GraphicsContext gc) { //the game loop
        lastLength=System.nanoTime()-lastTime;
        if ( lastLength> 10000000) {
          //  System.out.println("Fps :" + 1 / (lastLength * 0.000000001));//showfps

            if (leftPressed) {//Player basic control
                thanos.movingState = -1;
            } else if (rightPressed) {
                thanos.movingState = 1;
            } else {
                thanos.movingState = 0;
            }

            gameWorld.runWorld(Math.min(lastLength * 0.000000065, 3));//run logic for the selected world

            gc.clearRect(0, 0, winParam.getX() * 4, winParam.getY() * 4);//clear the game screen


            gameWorld.renderWorld(gc, root);//render the selected world
            gui.draw(gc, selectedWorld);
            renderBackground();


            myAudio.runMusic();
            lastTime=System.nanoTime();
        }
    }

    private void renderBackground() {//render the slow moving background at 20%speed
        backgroundImage.toBack();
        GraphicsContext gcb = backgroundImage.getGraphicsContext2D();
        double backgroundOffset;
        backgroundOffset = -(thanos.getCameraPosition().getX() * 0.2) % backgroundImage.getWidth();

        gcb.drawImage(gameWorld.myBackground, backgroundOffset, 0, backgroundImage.getWidth(), backgroundImage.getHeight());
        gcb.drawImage(gameWorld.myBackground, backgroundImage.getWidth() + backgroundOffset, 0, backgroundImage.getWidth(), backgroundImage.getHeight());
    }

    public void switchWorlds(int newWorld) {//disposes of old world and loads new one
        if (gameWorld != null) {
            gameWorld.dispose(root);
        }

        selectedWorld = newWorld;
        gameWorld = new World(selectedWorld, thanos, this);
        thanos.myPosition = gameWorld.getStarterPos();
    }

    public void showGame() {
        playing = true;
        toShow = 1;
    }

    public void hideGame() {
        playing = false;
        toShow = -1;
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