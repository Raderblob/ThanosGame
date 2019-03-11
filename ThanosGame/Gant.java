package ThanosGame;

public class Gant{
    Pierres [] Stones ;
    int nbPierres;
    public Gant(Pierres [] Stones){
        this.Stones = Stones ;
        this.nbPierres = 0 ;
    }
    public void action(int numero){
        if(Stones[numero] != null){
            Pierres PierreUtilisée = new Pierres(numero) ;
            PierreUtilisée.action();
        }
    }
}
