package ThanosGame;

import javafx.scene.input.KeyCode;

public class Keyboard {
    public static KeyCode right;
    public static KeyCode left;
    public static KeyCode jump;
    private static boolean isInQWERTY;
    public static double difficulty=2;
    public static double gameVolume =1;
    public Keyboard(){
        right = KeyCode.D;
        left = KeyCode.Q;
        jump = KeyCode.SPACE;
        KeyCode down = KeyCode.S;
        isInQWERTY = true;
    }
    public static void setAZERTY(){
        left = KeyCode.Q;
        isInQWERTY = false;
    }
    public static void setQWERTY(){
        left = KeyCode.A;
        isInQWERTY = true;
    }
    public static void setSpaceJump(){
        jump = KeyCode.SPACE;
    }
    public static void setUpJump(){
        if(isInQWERTY){
            jump = KeyCode.W;
        }else{
            jump = KeyCode.Z;
        }
    }
}
