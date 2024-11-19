 class Personne {
    protected int cin;
    protected String nom;
    protected String prenom;

    public Personne(int cin,String nom, String prenom){
        this.cin=cin;
        this.nom=nom;
        this.prenom=prenom;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String toString() {
        return (" ,Le Cin est: " +cin+ " ,le nom : " +nom+ " ,le prenom : " +prenom);
    }
}
abstract  class Propriete {
    protected int Id;
    protected Personne resp;
    protected String adr;
    protected double surface;

    public Propriete(Personne p,int Id,String adr, double surface){
        resp=p;
        this.Id=Id;
        this.adr=adr;
        this.surface=surface;
    }

    public String toString() {
        return ("L'id est : " +Id+ ", le nom est: " +resp+ ", l'adresse est: " +adr+ " ,la surface est : " + surface);
    }
    abstract double calculImpot();
}
 class ProprietePrivee extends Propriete{
    protected int nbPiece;

    public ProprietePrivee(Personne p, int Id, String adr, double surface, int nbPiece){
       super(p, Id, adr, surface);
       this.nbPiece=nbPiece;
    }

    public String toString() {
        return (super.toString()+" ,Le nombre de piece est: "+nbPiece);
    }

    double calculImpot() {
        return ((this.surface/100)*50+(this.nbPiece*10));
    }
}
public class ProprieteProfessionnelle extends Propriete{
    private int nbEm;
    private boolean estEtatique;

    public ProprieteProfessionnelle(Personne p, int Id, String adr, double surface, int nbEm, boolean estEtatique){
        super(p, Id, adr, surface);
        this.nbEm=nbEm;
        this.estEtatique=estEtatique;
    }

    public String toString() {
        return (super.toString()+" ,le nombre des emplyees est: "+nbEm+" ,est ce que etatique ou non: "+estEtatique);
    }

    double calculImpot() {
        if(this.estEtatique){
        return (100*super.surface+30*this.nbEm);
    }
        else {
            return 0;
        }
    }
}
     class Villa extends ProprietePrivee{
        private boolean avecPiscine;
    
        public Villa(Personne p,int Id,String adr,double surface,int nbPiece,boolean avecPiscine){
            super(p, Id, adr, surface,nbPiece);
            this.avecPiscine= avecPiscine;
        }
    
        public String toString() {
            return (super.toString()+" ,La villa est avec piscine ou non: "+avecPiscine);
        }
    
        double calculImpot() {
            if(this.avecPiscine){
                return super.calculImpot()+200;
            }
            else {
                return super.calculImpot();
            }
        }
    }
    class Appartement extends ProprietePrivee{
        public int numEtage;
    
        public Appartement(Personne p,int Id,String adr,double surface ,int nbPiece,int numEtage){
            super(p, Id, adr, surface,nbPiece);
            this.numEtage=numEtage;
        }
    
        public String toString() {
            return (super.toString()+" ,le numero d'etage est: " +numEtage);
        }
    }
     class Lotissement {
        protected Propriete[] tab;
        protected int nombre;
    
    
        public Lotissement(int capacite) {
            this.tab = new Propriete[capacite];
            this.nombre = 0;
        }
    
        public Propriete getProprieteByIndex(int i) {
            if (i >= 0 && i < nombre) {
                return tab[i];
            }
            return null;
        }
    
        public int getNbPieces() {
            int nbPieces = 0;
            for (int i = 0; i < nombre; i++) {
                if (tab[i] instanceof ProprietePrivee) {
                    nbPieces += ((ProprietePrivee) tab[i]).nbPiece;
                }
            }
            return nbPieces;
        }
    
        public void afficherProprietes() {
            for (int i = 0; i < nombre; i++) {
                System.out.println(tab[i].toString());
            }
        }
    
        public boolean ajouter(Propriete p) {
            if (nombre < tab.length) {
                tab[nombre++] = p;
                return true;
            }
            return false;
        }
    
        public boolean supprimer(Propriete p) {
            for (int i = 0; i < nombre; i++) {
                if (tab[i].equals(p)) {
    
                    for (int j = i; j < nombre - 1; j++) {
                        tab[j] = tab[j + 1];
                    }
                    tab[--nombre] = null;
                    return true;
                }
            }
            return false;
        }
    }
    interface GestionPropriete {
        int MAX_PROPRIETES = 10;
    
        void afficherProprietes();
    
        boolean ajouter(Propriete p);
    
        boolean supprimer(Propriete p);
    }
     class Fiscalite {
        public static void main(String[] args) {
            Personne p1 = new Personne(1, "kachabia", "Hazem");
            Personne p2 = new Personne(2, "founes", "aya");
            Personne p3 = new Personne(3, "cris", "sui");
    
            Lotissement lotissement = new Lotissement(10);
    
            Propriete propriete1 = new ProprietePrivee(p1, 1, "ss", 750, 5);
            Propriete propriete2 = new Villa(p2, 2, "aaa", 400, 6, true);
            Propriete propriete3 = new Appartement(p2, 3, "bbb", 1900, 8, 7);
            Propriete propriete4 = new ProprieteProfessionnelle(p3, 4, "dd", 2000, 50, true);
            Propriete propriete5 = new ProprieteProfessionnelle(p1, 5, "vv", 7500, 400, false);
    
            lotissement.ajouter(propriete1);
            lotissement.ajouter(propriete2);
            lotissement.ajouter(propriete3);
            lotissement.ajouter(propriete4);
            lotissement.ajouter(propriete5);
    
            System.out.println("Liste des propriétés :");
            lotissement.afficherProprietes();
    
            System.out.println("Nombre total de pièces : " + lotissement.getNbPieces());
        }
    }

