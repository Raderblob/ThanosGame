package ThanosGame;

public class Gant{
    Pierres [] Stones ;
    public Gant(Pierres [] Stones){
        this.Stones = Stones ;
    }
    public void action(int numero){
        if(Stones[numero] != null){
            Pierres PierreUtilisée = new Pierres(numero) ;
            PierreUtilisée.action() ;
        }
    }
}
