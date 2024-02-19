public class Tournesol extends Ressource {
  public static final int MINPRET = 3;
  public static final int MAXPRET = 5;
  private int etat; // indique l'etat de vie du tournesol : 0-> graine, {1,..,MINPRET-1}-> etapes de  croissance, {MINPRET,..,MAXPRET}-> pret a etre cueilli, {MAXPRET+1,...}-> trop vieux (deperit et repasse a l'etat 0)
 

  public Tournesol(String nom) {
    super(nom, 1);
    etat = 0;
  }

  public Tournesol(int etat, String nom) {
    super(nom, 1);
    this.etat = etat;
  }

  public void evolue() throws PasSurLeTerrain {
    if (!this.surLeTerrain()) throw new PasSurLeTerrain();
    etat++;
    if (etat < MINPRET) System.out.println(super.toString() + " pousse !");
    else if (etat <=MAXPRET) System.out.println(super.toString() + " est pret a etre cueilli");
    else System.out.println("trop tard...  " + super.toString() + " redevient graine ");
  }

  public int getEtat() {
    return etat;
  }

  public void setEtat(int newEtat) {
    etat = newEtat;
  }

  public boolean estPret() {
    return (etat <= MAXPRET) && (etat>=MINPRET);
  }

  public boolean surLeTerrain() {
    return this.getX() != -1 && this.getY() != -1;
  }

  
  public void attraperMaladie(Maladie m) throws PasSurLeTerrain  {
    if (!this.surLeTerrain()) throw new PasSurLeTerrain();
    System.out.println(this.toString() + " tombe malade");
  }
}
