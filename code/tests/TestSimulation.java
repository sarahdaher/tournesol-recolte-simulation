public class TestSimulation{
  public static void main(String[] args){
   
     System.out.println("Bienvenue dans notre champ de tournesol");
    System.out.println("Commençons par placer nos ressources et nos agents et par les présenter");
     System.out.println();
       
    Simulation s = new Simulation(15, 20);

    System.out.println();
    s.afficherSimulation();
    
     System.out.println("Que la simulation commence !");
    s.action();
    
    System.out.println("FIN DE LA SIMULATION ! ");
    
   System.out.println("Nombre de sachets de pipas créés durant cette simulation " + Usine.getNbSachets());
    System.out.println("Nombre de bouteilles d'huiles créées durant cette simulation " + Usine.getNbBouteillesHuile());
    
    System.out.println("Nombre graines restantes: " + Agriculteur.getNbGrainesSeches());
     System.out.println("Nombre fleurs restantes :" + Agriculteur.getNbFleursTot());
  }
}