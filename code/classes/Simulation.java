import java.util.ArrayList;

public class Simulation {
  //attributs
  private Terrain terrain;
  private final int MAXITER = 50;
  private ArrayList<Agent> listeAgents;
  private ArrayList<Ressource> listeRessources;
  private final Usine usine = Usine.getInstance();

  //constructeur
  public Simulation(int m, int n) {
    terrain = new Terrain(6, 6);
    listeRessources = new ArrayList<Ressource>();
    listeAgents = new ArrayList<Agent>();
    for (int i = 0; i < m; i++) {
      Ressource r;
      int x, y;
      r = new Tournesol("tournesol"); // au debut les tournesols de sont pas malades 
      x = (int) (Math.random() * terrain.nbLignes);
      y = (int) (Math.random() * terrain.nbColonnes);
      listeRessources.add(r);
      r.setPosition(x, y);
      terrain.setCase(x, y, r);
    }
    for (int i = 0; i < n; i++) {
      Agent a;
      int x, y;
      x = (int) (Math.random() * terrain.nbLignes);
      y = (int) (Math.random() * terrain.nbColonnes);
      if (Math.random() < 0.3)  a = new Oiseau(x, y, terrain);
      else  a = new Agriculteur(x, y, terrain);
      if(a.getId()>0) listeAgents.add(a);
    }
  }
  //methodes
  public void action() {
    int cpt = 1;
    System.out.println();
    System.out.println("TOUR " + 0);
    terrain.affiche(30);
    while (cpt <= MAXITER) {
      System.out.println();
      System.out.println("TOUR " + cpt);

      for (int i = 0; i < listeAgents.size(); i++) {
        try{
          int alea;
          if (listeAgents.get(i) instanceof Agriculteur) {
            alea = (int) (Math.random() * 10);
            if (alea == 0) listeRessources.add(((Agriculteur) (listeAgents.get(i))).semer());
            if (alea == 6) ((Agriculteur) (listeAgents.get(i))).arroser();
            if (alea < 6) {
              Ressource r =((Agriculteur) (listeAgents.get(i))).ramasser();
              if(r!=null) listeRessources.remove(listeRessources.indexOf(r));
            }
            if ((6 <= alea) && (alea <= 10)) listeRessources.add(((Agriculteur) (listeAgents.get(i))).secher());
          
          } 
          else if (listeAgents.get(i) instanceof Oiseau) {
            alea = (int) (Math.random() * 3);
            if (alea == 0) {
              Ressource r =((Oiseau) (listeAgents.get(i))).picorer();
              if(r!=null) listeRessources.remove(listeRessources.indexOf(r));
            }
            if (alea == 1) {
              Ressource r =((Oiseau) (listeAgents.get(i))).defequer();
              if(r!=null) listeRessources.remove(listeRessources.indexOf(r));
            } 
            if (alea == 2){
              Oiseau nvo =( (Oiseau) (listeAgents.get(i))).seReproduit();
              if(nvo!=null && nvo.getId()>0) listeAgents.add(nvo);
            }
         }
        }catch (PasSurLeTerrain e){ System.out.println(e);}
        
        if (Math.random() < 0.6) {
          int x = (int) (Math.random() * terrain.nbLignes);
          int y = (int) (Math.random() * terrain.nbColonnes);

          if ((listeAgents.get(i)) != null) (listeAgents.get(i)).seDeplacer(x, y);
        }
      }
      
      
      boolean soleil = false;
      if (Math.random() < 0.6) {
        System.out.println("Il y a du soleil !");
        soleil = true;
      }
      else System.out.println("Il n'y a pas de soleil aujourd'hui");
      double p = Math.random();
      
      for (int i = 0; i < listeRessources.size(); i++) {
          Ressource r = listeRessources.get(i);
          if(r==null){listeRessources.remove(i); continue;}
          try{
          if (r instanceof Tournesol){ 
              if(soleil) ((Tournesol) r).evolue();
              int x=r.getX(), y =r.getY(), etat = ((Tournesol) r).getEtat();
            
              if ((p < 0.1)&&(!(r instanceof TournesolMalade))) {
                Maladie m;
                if(p<0.5) m = new MaladieImmortelle("Mindiou");
                else m = new MaladieMortelle("Phoma");
                ((Tournesol)r).attraperMaladie(m);
                
                r.initialisePosition();
                terrain.videCase(x,y);
                listeRessources.remove(i);
                TournesolMalade tm = new TournesolMalade(m,etat);
                tm.setPosition(x, y);
                terrain.setCase(x, y, tm);
                listeRessources.add(tm);
                
              }
              else if((r instanceof TournesolMalade)){
                if (((TournesolMalade)r).surLeTerrain()){
                  ((TournesolMalade)r).avenir();
                  if(((TournesolMalade)r).getMaladie() instanceof MaladieMortelle){
                    r.initialisePosition();
                    terrain.videCase(x,y);
                    listeRessources.remove(i);
                  }     
                }    
              }     
        }
        else if (r instanceof FleurTournesol && soleil) ((FleurTournesol) r).seche();
        }catch (PasSurLeTerrain e){ System.out.println(e);}
        if(r==null){listeRessources.remove(i);}
     }
      
      SachetDePipas s=usine.emballer();
      if(s!=null) listeRessources.add(s);
      HuileDeTournesol h = usine.fabriquerHuile((int)(Math.random()*Agriculteur.getNbFleursTot()));
      if(h!=null) listeRessources.add(h);
      terrain.affiche(30);
      cpt++;
    
    }
}
      //affichage
  public void afficherSimulation() {
    System.out.println("LA LISTE DES RESSOURCES :");
    for (Ressource r : listeRessources) {
      System.out.println(r);
    }
    System.out.println();
    System.out.println("LA LISTE DES AGENTS : ");
    for (Agent a : listeAgents) {
      System.out.println(a);
    }
  }


  //accesseurs
  public Terrain getTerrain() {
	return terrain;
  }

 

  public int getMAXITER() {
	  return MAXITER;
  }

  public ArrayList<Agent> getListeAgents() {
	  return listeAgents;
  }

 
  public ArrayList<Ressource> getListeRessources() {
	  return listeRessources;
  }

  public Usine getUsine() {
	  return usine;
  }
  //assesseurs
   public void setListeRessources(ArrayList<Ressource> listeRessources) {
	  this.listeRessources = listeRessources;
  }
  
   public void setListeAgents(ArrayList<Agent> listeAgents) {
	  this.listeAgents = listeAgents;
  }
  
   public void setTerrain(Terrain terrain) {
	  this.terrain = terrain;
  } 
}