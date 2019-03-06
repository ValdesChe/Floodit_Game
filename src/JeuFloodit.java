
import java.util.*;

/**
 * Created by valde on 2019-02-16.
 */
public class JeuFloodit {

    public static final int MAX_TOURS = 30;
    public int nbCoups = MAX_TOURS;
    public static final int TAILLE_GRILLE = 12;

    private Cellule grille [][];
    private HashSet<Cellule> riviereGrille;

    private int currentRiverColor;
    private int previousRiverColor;

    /**
     *  Remplie la grille avec des couleurs aléatoires
     *  @param
     */

    public void initFloodit(){
        grille  = new Cellule[TAILLE_GRILLE][TAILLE_GRILLE];
        riviereGrille = new HashSet<>();
        Random random = new Random();
        int color ;
        for (int i = 0; i < TAILLE_GRILLE ; i++){
            for (int j = 0 ; j < TAILLE_GRILLE ; j++ ){
                // grille[i][j] = 1 + (int)(Math.random() * (5));
                color = random.nextInt(6);
                Vector coord =  new Vector();
                coord.addElement(i);
                coord.addElement(j);
                grille[i][j] = new Cellule(color , color , coord);
                // System.out.print(grille[i][j] + "  ");
            }
            // System.out.println("");
        }
        currentRiverColor = grille[0][0].currentColor;
        previousRiverColor = currentRiverColor;
        riviereGrille.add(grille[0][0]);
        colorer(grille[0][0].getCurrentColor());
        nbCoups=MAX_TOURS;

    }

    /**
     *  Reinitialise le jeu
     *
     *  @param
     */
    public void JouerNouvellePartie(){
        // Reinitialisation du jeu
        initFloodit();
        nbCoups = MAX_TOURS;
    }

    /**
     *  Colorie une case passée en paramètre
     *
     *  @param  color int
     */
    public void colorer(int color){
        previousRiverColor = currentRiverColor;
        currentRiverColor =color;
        colorRiviereGriller(color);
        Iterator iterator = riviereGrille.iterator();
        Cellule cellule;
        HashSet<Cellule> nouveauxElts = new HashSet<>();
        while (iterator.hasNext()){
             cellule = (Cellule) iterator.next();
            //System.out.println(cellule.toString());
            ArrayList<Cellule> voisins = listVoisin((int) cellule.getPosition().get(0), (int) cellule.getPosition().get(1));
            //System.out.println(voisins.toString());
            Iterator iteratorVoisins = voisins.iterator();
            while (iteratorVoisins.hasNext()){
                Cellule voisin = (Cellule) iteratorVoisins.next();

                // Si la
                if(voisin.getCurrentColor() == currentRiverColor && !riviereGrille.contains(voisin)){
                    voisin.colorer(color);
                    nouveauxElts.add(voisin);
                    // riviereGrille.add(voisin);
                    // grille[(int) voisin.getPosition().get(0)][(int) voisin.getPosition().get(1)] = voisin;
                }
            }
        }

        Iterator iteratorNew = nouveauxElts.iterator();
        while (iteratorNew.hasNext()){
            Cellule cellule1 = (Cellule) iteratorNew.next();
            riviereGrille.add(cellule1);
            grille[(int) cellule1.getPosition().get(0)][(int) cellule1.getPosition().get(1)] = cellule1;
        }

        if(nouveauxElts.size() > 0){
            colorer(color);
        }else{
            nbCoups--;
        }

        // nbCoups--;
    }


    public  ArrayList<Cellule>  listVoisin(int xTab, int yTab) {
        ArrayList<Cellule> voisinage = new ArrayList<>();
        for(int y=yTab-1; y<=yTab+1; y++){
            for(int x=xTab-1; x<=xTab+1; x++) {
                if ( (x != xTab) || (y != yTab)){
                    if( (x== xTab-1 && y == yTab-1 )
                            ||  (x== xTab-1 && y == yTab+1 )
                            ||  (x== xTab+1 && y == yTab-1 )
                            ||  (x== xTab+1 && y == yTab+1 )
                            ){
                        continue;
                    }
                    if(valideDonnee(x, y)){
                        voisinage.add(grille[x][y]);
                        // afficheDonnee(x,y);
                    }
                }
            }
        }
        //System.out.println("Debut VOisinage");
        //System.out.println(voisinage);
       // System.out.println("En VOisinage");
        return voisinage;
    }

    private void afficheDonnee(int xTab, int yTab) {
        if(valideDonnee(xTab, yTab))
            System.out.println("("+ xTab +", " +yTab+ ") = " + grille[xTab][yTab]);
    }

    // Verifier qu'on ne depasse pas le tableau (a ne pas oublier).
    private boolean valideDonnee(int xTab, int yTab) {
        if(xTab<0 || xTab>=grille.length) return false;
        return !(yTab<0 || yTab>=grille.length);
    }

    private void colorRiviereGriller(int color) {
        //System.out.println(riviereGrille.toString());
        for (Cellule cell : riviereGrille) {
            cell.colorer(color);
            grille[ (int) cell.getPosition().get(0) ][(int) cell.getPosition().get(1)] = cell;
        }

       // System.out.println(riviereGrille.toString());
    }


    /**
     *  Vérifie si la grille est totalement coloriée ou pas .
     *  Elle retourne Vrai si toutes lescellules de la grille sont de mˆeme couleur et Faux autrement.
     *
     *  @return boolean
     */
    public boolean verifier(){

        return riviereGrille.size() == Math.pow(TAILLE_GRILLE,2);
    }

    /**
     *  Cette fonction permet de tracer la grille  en console .
     *
     */
    public void tracerGrille(){
        System.out.println(" +——+——+——+——+——+ RESTANTS : "+ nbCoups+ "/"+ MAX_TOURS +" ——+——+——+——+——+——+");
        for (int i = 0; i < TAILLE_GRILLE ; i++){
            //  System.out.println(" +——+——+——+——+——+——+——+——+——+——+——+——+——+——+——+");
            for (int j = 0 ; j < TAILLE_GRILLE ; j++ ){

                 // System.out.print(" - " + grille[i][j] + " ");
                 System.out.print( grille[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println(" +——+——+——+——+——+——+——+——+——+——+——+——+——+——+——+");
    }

     /**
     *  Cette fonction permet de tracer la grille  en console .
     *
     */
    public void ordreOptimal(){

        for (int i = 0; i < TAILLE_GRILLE ; i++){
            System.out.println("+——+——+——+——+——+——+——+——+——+——+——+——+——+——+——+");
            for (int j = 0 ; j < TAILLE_GRILLE ; j++ ){
                System.out.print("  " + grille[i][j] + " - ");
            }
            System.out.println(" ");
        }

        System.out.println("+——+——+——+——+——+——+——+——+——+——+——+——+——+——+——+");
    }

}
