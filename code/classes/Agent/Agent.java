import java.util.ArrayList;
public abstract class Agent{
  //attributs
    private int id;
    private int posx;
    private int posy;
    protected Terrain terrain;
    private static ArrayList<Point> CasesOccupees = new ArrayList<Point>();
    private static int cpt = 0;

  // constructeur
    public Agent(int x, int y, Terrain t){
        if (!t.sontValides(x,y) ){
            System.out.println("coordonnees saisies non valides");
            return;
        }
        Point newp = new Point(x,y);
        for(Point p : CasesOccupees){
            if(newp.egaux(p)){
                System.out.println("il y a deja un agent sur cette case (" +x+","+y+")");
                return;
            }
        }
        posx=x;
        posy=y;
        CasesOccupees.add(new Point(x,y));
        terrain=t;
        cpt++;
        id=cpt;

    }
  //methodes
   public double distance(int x, int y){
        return Math.sqrt((posx-x)*(posx-x) + (posy-y)*(posy-y));
    }

    public void seDeplacer(int newx, int newy){
        if(this.terrain != null){
        if (!terrain.sontValides(newx,newy) ){
            System.out.println("coordonnees saisies non valides");
            return;
        }
        Point newp = new Point(newx,newy);
        Point thisp = new Point(posx,posy);
        int indice = 0;
        for(Point p : CasesOccupees){
            if(newp.egaux(p)){
                System.out.println("il y a deja un agent sur la case" + p);
                return;
            }
            if(thisp.egaux(p)){
                indice =CasesOccupees.indexOf(p);
            }
        }
        posx = newx;
        posy = newy;
        System.out.println("se deplace en ("+ newx + ","+ newy +")");
        CasesOccupees.remove(indice);
        CasesOccupees.add(newp);
        
        } 
    }
    public String toString(){
        return "(agent " + id + " ) en position ("+ posx + ","+ posy +")";
    }
  
  // accesseurs
    public int getId(){
        return id;
    }
    public int getX(){
        return posx;
    }
    public int getY(){
        return posy;
    }
}

   