public class Point{
  //attributs
    private int x;
    private int y;

  //constructeur
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public boolean egaux(Point p){
        return x==p.x && y==p.y;
    }
  
    public String toString(){
      return "(" + x + "," + y + ")";
    }
  //accesseurs
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
  //assesseurs
    public void setXY(int newx, int newy){
        x = newx;
        y = newy;
    }
}