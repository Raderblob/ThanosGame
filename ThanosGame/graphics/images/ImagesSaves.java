package ThanosGame.graphics.images;


import javafx.scene.image.Image;

public class ImagesSaves {
    public static Image thanosSprites;
    public static Image teleporterSprite;
    public static Image projectiles[] = new Image[3];
    public static Image explosions[] = new Image[1];
    public static Image shieldSprites;
    public static Image wakandaisSprites;

    public static void loadImages(){
        thanosSprites = new Image(ImagesSaves.class.getResource("thanosSprites.png").toExternalForm());
        projectiles[0] =  new Image(ImagesSaves.class.getResource("projectile1.png").toExternalForm());
        projectiles[1] =  new Image(ImagesSaves.class.getResource("projectile2.png").toExternalForm());
        projectiles[2] =  new Image(ImagesSaves.class.getResource("projectile3.png").toExternalForm());
        explosions[0]=  new Image(ImagesSaves.class.getResource("explosion1.png").toExternalForm());
        teleporterSprite =  new Image(ImagesSaves.class.getResource("teleporterSprite.png").toExternalForm());
        shieldSprites = new Image(ImagesSaves.class.getResource("ShieldSprites.png").toExternalForm());
        wakandaisSprites = new Image(ImagesSaves.class.getResource("WakandaisSprites.png").toExternalForm());
    }
}

