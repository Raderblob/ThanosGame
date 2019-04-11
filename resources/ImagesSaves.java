package resources;


import javafx.scene.image.Image;

public class ImagesSaves {
    public static Image thanosSprites;
    public static Image teleporterSprite;
    public static Image shieldSprite;
    public static final Image[] projectiles = new Image[6];
    public static final Image[] explosions = new Image[1];
    public static Image agentSprites;
    public static Image wakandaisSprites;
    public static Image thorSprite;
    public static Image healSprite;
    public static Image stoneSprite;
    public static Image desertBackground;
    public static Image spaceBackground;
    public static Image cityBackground;

    public static void loadImages() {
        thanosSprites = new Image(ImagesSaves.class.getResource("sprites/thanosSprites.png").toExternalForm());
        projectiles[0] = new Image(ImagesSaves.class.getResource("sprites/projectiles/projectile1.png").toExternalForm());
        projectiles[1] = new Image(ImagesSaves.class.getResource("sprites/projectiles/projectile2.png").toExternalForm());
        projectiles[2] = new Image(ImagesSaves.class.getResource("sprites/projectiles/projectile3.png").toExternalForm());
        projectiles[3] = new Image(ImagesSaves.class.getResource("sprites/projectiles/projectile4.png").toExternalForm());
        projectiles[4] = new Image(ImagesSaves.class.getResource("sprites/projectiles/projectile5.png").toExternalForm());
        projectiles[5] = new Image(ImagesSaves.class.getResource("sprites/projectiles/projectile6.png").toExternalForm());
        explosions[0] = new Image(ImagesSaves.class.getResource("sprites/explosion1.png").toExternalForm());
        teleporterSprite = new Image(ImagesSaves.class.getResource("sprites/teleporterSprite.png").toExternalForm());

        wakandaisSprites = new Image(ImagesSaves.class.getResource("sprites/WakandaisSprites.png").toExternalForm());
        agentSprites = new Image(ImagesSaves.class.getResource("sprites/agentSprites.png").toExternalForm());
        thorSprite = new Image(ImagesSaves.class.getResource("sprites/thorSprites.png").toExternalForm());
        shieldSprite = new Image(ImagesSaves.class.getResource("sprites/shieldSprite.png").toExternalForm());
        healSprite = new Image(ImagesSaves.class.getResource("sprites/healSprite.png").toExternalForm());
        stoneSprite = new Image(ImagesSaves.class.getResource("sprites/stones/stoneSprite.png").toExternalForm());

        desertBackground = new Image(ImagesSaves.class.getResource("buildings/backgrounds/desertWorld.png").toExternalForm());
        spaceBackground = new Image(ImagesSaves.class.getResource("buildings/backgrounds/space.png").toExternalForm());
        cityBackground = new Image(ImagesSaves.class.getResource("buildings/backgrounds/city.png").toExternalForm());
    }
}

