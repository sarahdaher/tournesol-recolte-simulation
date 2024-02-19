
public class Oiseau extends Agent {
//constructeurs
  public Oiseau(int x, int y, Terrain t) {
    super(x, y, t);
  }

  public Oiseau(Oiseau o) {
    super((int) (Math.random() * (o.terrain).nbLignes), (int) (Math.random() * (o.terrain).nbColonnes), o.terrain);
  }

  //methodes
  public Ressource picorer() throws PasSurLeTerrain{

    if (this.terrain == null) return null;
    if (!(terrain.caseEstVide(getX(), getY()))) {
        System.out.println(this.toString() + " picore " + terrain.getCase(getX(), getY()).toString());
        (terrain.getCase(getX(), getY())).initialisePosition();
        return terrain.videCase(getX(), getY());
    } else {
        System.out
            .println("Rien à faire, l'oiseau ne picore pas car la case (" + getX() + "," + getY() + ")  est vide");
        System.out.println("L'oiseau se déplace");
        this.seDeplacer((int) (Math.random() * terrain.nbLignes), (int) (Math.random() * terrain.nbColonnes));
        return null;
      }
  }

  public Ressource defequer() throws PasSurLeTerrain{
    if (this.terrain == null) return null;
    System.out.println(this.toString() + " fait ses besoins, la terre est nourrie avec cet engrais ");
    if (!terrain.caseEstVide(getX(), getY())) {
      if (terrain.getCase(getX(), getY()) instanceof Tournesol){ ((Tournesol) terrain.getCase(getX(), getY())).evolue(); return null;}
      else if (terrain.getCase(getX(), getY()) instanceof FleurTournesol) {
          System.out.println((terrain.getCase(getX(), getY())).toString() + " est souillé ; doit etre jete");
          (terrain.getCase(getX(), getY())).initialisePosition();
          return terrain.videCase(getX(), getY());
        }
    }
    return null;
  }

  public Oiseau seReproduit() {
    if (this.terrain != null){
      System.out.println(this.toString() + " se reproduit");
      return new Oiseau(this);
    }else return null;
  }

  public String toString() {
    return "Oiseau " + super.toString();
  }
}