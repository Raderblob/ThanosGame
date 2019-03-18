package ThanosGame.graphics.images;


import javafx.scene.image.Image;

public class ImagesSaves {
    public static Image thanosSprites;

    public static void loadImages(){
        thanosSprites = new Image(ImagesSaves.class.getResource("thanosSprites.png").toExternalForm());
    }
}

