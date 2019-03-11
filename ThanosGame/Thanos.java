package ThanosGame;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;

public class Thanos{
    private Image imgThanos;
    private ImageIcon icoThanos;
    int PV ; 
    Gant Infinité ;
    private Point2D MaPosition ;

    public Thanos(int PV, Gant Infinity){
        this.PV = PV ;
        this.Infinité = Infinity ;
        this.icoThanos = new ImageIcon("CourseDroite1");
        this.imgThanos = this.icoThanos.getImage();


    }
    public void draw(GraphicsContext gc){


    }

    public Image getImgThanos() {
        return imgThanos;
    }
}
 
