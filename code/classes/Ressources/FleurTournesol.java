public class FleurTournesol extends Ressource{
    public static final int PRET=5;
    private int etat; //etat correspondant a l'avancee de l'assechement de la fleur

  //constructeur
    public FleurTournesol(){
        super("fleur de tournesol",1);
        etat=0;
    }

  // methodes 
    public void seche() throws PasSurLeTerrain{
      if (!this.surLeTerrain()) throw new PasSurLeTerrain();
      etat++;
      System.out.println(super.toString()+" seche");
    }
    public boolean estSeche(){
        return (etat >=PRET);
    }
    public boolean surLeTerrain() {
      return this.getX() != -1 && this.getY() != -1;
    }
}