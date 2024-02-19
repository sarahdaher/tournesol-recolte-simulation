public class TournesolMalade extends Tournesol implements estContaminer{
  private Maladie maladie;

  public TournesolMalade(Maladie m, int n){
    super(n,"tournesol malade");
    maladie = m;
  }

  public void avenir() throws PasSurLeTerrain{
    if(!this.surLeTerrain()) throw new PasSurLeTerrain();
    if (this.maladie instanceof MaladieMortelle){
      System.out.println("Oh non ! " + this.toString() + " meurt \n");
      this.setEtat(0); 
    }
    else if (this.maladie instanceof MaladieImmortelle){
      if(this.getEtat()>0 ){
        this.setEtat(this.getEtat()-1);
        System.out.println(this.toString() + " regresse de 1 etat \n");
      } 
    }
  }
  public Maladie getMaladie(){
    return maladie;
  }

  public String toString(){
    return  super.toString() + "malade de maladie " + maladie.getNom();
  }
  
}