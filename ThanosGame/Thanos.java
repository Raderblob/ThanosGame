package ThanosGame;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;

    public class Thanos extends PlayerClass{
        private Image imgThanos;
        private ImageIcon icoThanos;
        int PV ;
        Gant Infinité ;
        private Point2D MaPosition ;

        public Thanos(int PV, Gant Infinity){
            super();
            this.PV = PV ;
            this.Infinité = Infinity ;
            this.icoThanos = new ImageIcon("CourseDroite1");
            this.imgThanos = this.icoThanos.getImage();
    }



} 

 
