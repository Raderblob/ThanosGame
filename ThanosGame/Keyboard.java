package ThanosGame;

import javafx.scene.input.KeyCode;

public class Keyboard {
    public static KeyCode right, left, jump, down, punch;
    public static boolean isInQWERTY;
    public Keyboard(){
        right = KeyCode.D;
        left = KeyCode.A;
        jump = KeyCode.W;
        down = KeyCode.S;
        punch = KeyCode.ENTER;
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
