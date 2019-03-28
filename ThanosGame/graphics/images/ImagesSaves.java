package ThanosGame.graphics.images;


import javafx.scene.image.Image;

public class ImagesSaves {
    public static Image thanosSprites;
    public static Image projectiles[] = new Image[1];
    public static Image explosions[] = new Image[1];
    public static Image ShieldSprites;
    public static Image WakandaisSprites;

    public static void loadImages(){
        thanosSprites = new Image(ImagesSaves.class.getResource("thanosSprites.png").toExternalForm());
        projectiles[0] =  new Image(ImagesSaves.class.getResource("projectile1.png").toExternalForm());
        explosions[0]=  new Image(ImagesSaves.class.getResource("explosion1.png").toExternalForm());
       // ShieldSprites = new Image(ImagesSaves.class.getResource("ShieldSprites.png").toExternalForm());
        //WakandaisSprites = new Image(ImagesSaves.class.getResource("WakandaisSprites.png").toExternalForm());
    }
}

