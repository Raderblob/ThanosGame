package ThanosGame;

import javafx.geometry.Point2D;

public class Thanos extends PlayerClass{
    public int PV ;
    Gant Infinité ;
    private Point2D MaPosition ;
    public int degats = 50 ;
    public int range = 50 ;

    public Thanos(int PV, int degats, int range, Gant Infinity){
        super();
        this.PV=PV ;
        this.degats=degats;
        this.Infinité=Infinity ;
        this.range=range ;
    }
} 

 
