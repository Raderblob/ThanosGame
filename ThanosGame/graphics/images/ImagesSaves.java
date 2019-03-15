package ThanosGame.graphics.images;


import javafx.scene.image.Image;

public class ImagesSaves {
    public static Image thanosWalkingRight;

    public static void loadImages(){
        thanosWalkingRight = new Image(ImagesSaves.class.getResource("thanosRight.png").toExternalForm());
    }
}
