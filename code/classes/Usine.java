import java.util.ArrayList;
public class Usine {
  private static int nbSachets = 0;
  private static int nbBouteillesHuile = 0;
  private static final Usine INSTANCE = new Usine();
 
  private Usine() { }

  public static Usine getInstance() {
    return INSTANCE;
  }
  public static int  getNbSachets(){
    return nbSachets;
  }
  public static int  getNbBouteillesHuile(){
    return nbBouteillesHuile;
  }
  public SachetDePipas emballer() {
    int nb = 0;
    int grainesDispo = Agriculteur.getNbGrainesSeches();
    while (grainesDispo >= SachetDePipas.NBGRAINESDANSUNSACHET) {
      nb++;
      grainesDispo -= SachetDePipas.NBGRAINESDANSUNSACHET;
      
    }
      Agriculteur.moinsNbGrainesSeches(SachetDePipas.NBGRAINESDANSUNSACHET*nb);
    System.out.println("On a emballé " + nb + " sachets de pipas");
    nbSachets += nb;
    if(nb>0) return new SachetDePipas(nb);
    else return null;
  }

  public HuileDeTournesol fabriquerHuile(int nbFleurs){
    int nb = 0;
    int fleursDispo = nbFleurs;
    if(fleursDispo > Agriculteur.getNbFleursTot()){
      fleursDispo = Agriculteur.getNbFleursTot();
    }
   
    while (fleursDispo >=HuileDeTournesol.NBFLEURSPOUR1LDHUILE){ 
      
      nb++;
      fleursDispo -= HuileDeTournesol.NBFLEURSPOUR1LDHUILE;
    }

    Agriculteur.moinsNbFleursTot(HuileDeTournesol.NBFLEURSPOUR1LDHUILE*nb); 
    
    System.out.println("On a fabrique " + nb + " bouteilles d'huile de tournesol");
    nbBouteillesHuile+=nb; 
    if(nb>0) return new HuileDeTournesol(nb);
    else return null;
  }
} 