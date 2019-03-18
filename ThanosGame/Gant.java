package ThanosGame;

public class Gant{
    Pierres [] Stones ;
    int nbPierres ;
    public Gant(Pierres [] Stones){
        this.Stones = Stones ;
    }
    public void action(int numero, Thanos Thanos1){
        if(Stones[numero] != null){
            Pierres PierreUtilisée = new Pierres(numero) ;
            PierreUtilisée.action(numero, Thanos1) ;
        }
    }
}
