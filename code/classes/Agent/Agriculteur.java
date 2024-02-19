import java.util.ArrayList;

public class Agriculteur extends Agent {
  //attributs
  private static int nbFleursTot = 0;
  private static int nbGrainesSeches = 0;
  
//constructeur
  public Agriculteur(int x, int y, Terrain t) {
    super(x, y, t);
   
  }

  //methodes
  public Tournesol semer() {
    if(this.terrain== null) return null;
    System.out.println(this.toString() + " seme graines de tournesol");
    Tournesol t = new Tournesol("tournesol");
    t.setPosition(getX(), getY());
    terrain.setCase(getX(), getY(), t);
    return t;
  }

  public void arroser() throws PasSurLeTerrain {
    if(this.terrain == null) return ;
    if (terrain.caseEstVide(getX(), getY())) {System.out.println(this.toString() + " n'a rien a arroser"); return;}
    Ressource r = terrain.getCase(getX(),getY());
    if(r instanceof Tournesol){
        ((Tournesol)r).evolue();
        System.out.println(this.toString() + " arrose " + r.toString());
    }    
  }

  public Ressource ramasser() throws PasSurLeTerrain {
    if(this.terrain == null) return null;
    if (terrain.caseEstVide(getX(), getY())){ System.out.println(this.toString() + " n'a rien a recuperer"); return null;}
    Ressource r = terrain.getCase(getX(), getY());
      if (r instanceof Tournesol) {
        if (((Tournesol) r).estPret()) {
          System.out.println(this.toString() + " cueille fleur de tournesol");
          
          r.initialisePosition();
          nbFleursTot++;
          return terrain.videCase(getX(), getY());
        } 
        else {
          System.out.println(this.toString() + " ne cueille rien car " + r.toString() + " n'est pas pret");
          return null;
        }
      } else if (r instanceof FleurTournesol) {
          if (((FleurTournesol) r).estSeche()) {
            System.out.println(this.toString() + " recupere les graines seches de tournesol");
            r.initialisePosition();
            nbGrainesSeches += 100;
            return terrain.videCase(getX(), getY());
          } else {
              System.out.println(this.toString() + " ne recupere rien car " + r.toString() + " n'est pas seche");
              return null;
            }
        }
    return null;
  }

  public FleurTournesol secher() throws PasSurLeTerrain {
    if (this.terrain==null) return null;
    if (terrain.caseEstVide(getX(), getY())  && nbFleursTot>0 ) {
      System.out.println(this.toString() + " fait secher fleur de tournesol");
      FleurTournesol f =new FleurTournesol();
      f.setPosition(getX(), getY());
      terrain.setCase(getX(), getY(), f);
      nbFleursTot--;
      return f;
    }
    return null;
  }
  
  public static void moinsNbFleursTot(int nb) {
    nbFleursTot -= nb;
  }

  public static void moinsNbGrainesSeches(int n) {
    nbGrainesSeches -= n;
  }

  public String toString() {
    return "Agriculteur " + super.toString();
  }

  //accesseurs
  public static int getNbGrainesSeches() {
    return nbGrainesSeches;
  }

  public static int getNbFleursTot() {
    return nbFleursTot;
  }
}