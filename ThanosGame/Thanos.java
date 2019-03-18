package ThanosGame;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;

public class Thanos extends PlayerClass{
    int PV ;
    Gant Infinité ;
    private Point2D MaPosition ;
    public int degats ;
    public int range ;

    public Thanos(int PV, int degats, int range, Gant Infinity){
        super();
        this.PV=PV ;
        this.degats=degats;
        this.Infinité=Infinity ;
        this.range=range ;
    }
} 

 
