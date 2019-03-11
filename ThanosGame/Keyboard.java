package ThanosGame;

import javafx.scene.input.KeyCode;

public class Keyboard {
    public static KeyCode right;
    public static KeyCode left;
    public static KeyCode jump;
    public static boolean inQWERTY;
    public Keyboard(){
        right = KeyCode.D;
        left = KeyCode.A;
        jump = KeyCode.Q;
        inQWERTY = true;
    }
    public static void setAZERTY(){
        right = KeyCode.D;
        left = KeyCode.Q;
    }
    public static void setQWERTY(){
        right = KeyCode.D;
        left = KeyCode.A;
    }
    public static void setSpaceJump(){
        jump = KeyCode.SPACE;
    }
    public static void setUpJump(){
        if(inQWERTY){
            jump = KeyCode.Q;
        }else{
            jump = KeyCode.Z;
        }
    }
}
