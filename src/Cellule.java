
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by valde on 2019-02-23.
 */
public class Cellule {
    protected int currentColor;
    protected int previousColor;

    protected Vector position;


    /***
     *  Constructeur de Cellule du jeu
     * @param currentColor
     * @param previousColor
     * @param position
     */
    public Cellule(int currentColor, int previousColor, Vector position) {
        this.currentColor = currentColor;
        this.previousColor = previousColor;
        this.position = position;
    }


    public boolean isThereRiverAround(ArrayList<Cellule> riviereGrille){
        Vector cellPosition = this.getPosition();
        for (int i = 0; i < riviereGrille.size(); i++) {
            Vector position = riviereGrille.get(i).getPosition();

            //  Actuellement à une case du carré interieur.
            if((int) cellPosition.get(0) > 0
                    && (int) cellPosition.get(0) < JeuFloodit.TAILLE_GRILLE -1
                    && (int) cellPosition.get(1) > 0
                    && (int) cellPosition.get(1) < JeuFloodit.TAILLE_GRILLE -1 ){

                return ((int) cellPosition.get(1) == (int) position.get(1)-1)
                        || ( (int) cellPosition.get(1) == (int) position.get(1)+1)
                        || ((int) cellPosition.get(0) == (int) position.get(0)+1)
                        || ((int) cellPosition.get(0) == (int) position.get(0)-1);
            }

            //  Actuellement en ligne 1 uniquement
            if((int) cellPosition.get(0) == 0 ){
                // First cell
                if((int) cellPosition.get(1) == 0 ){
                    return ((int) cellPosition.get(0) == (int) position.get(0)+1)
                            || ((int) cellPosition.get(1) == (int) position.get(1)+1);
                }

                // Last cell
                else if ( (int) cellPosition.get(1) == JeuFloodit.TAILLE_GRILLE -1) {
                    return ((int) cellPosition.get(0) == (int) position.get(0) - 1)
                            || ((int) cellPosition.get(1) == (int) position.get(1) + 1);
                }

                // Intermediate cell
                else if ( (int) cellPosition.get(1) > 0 && (int) cellPosition.get(1) < JeuFloodit.TAILLE_GRILLE-1  ){
                    return ((int) cellPosition.get(0) == (int) position.get(0)+1)
                            || ((int) cellPosition.get(1) == (int) position.get(1)+1)
                            || ((int) cellPosition.get(1) == (int) position.get(1)-1);
                }
                else {
                    continue;
                }

            }

            //  Actuellement en ligne N uniquement
            else if((int) cellPosition.get(0) == JeuFloodit.TAILLE_GRILLE-1 ){
                // First cell H-D
                if((int) cellPosition.get(1) == 0 ){
                    return ((int) cellPosition.get(0) == (int) position.get(0)-1)
                            || ((int) cellPosition.get(1) == (int) position.get(1)+1);
                }

                // Last cell H-G
                else if ( (int) cellPosition.get(1) == JeuFloodit.TAILLE_GRILLE -1) {
                    return ((int) cellPosition.get(0) == (int) position.get(0) + 1)
                            || ((int) cellPosition.get(1) == (int) position.get(1) - 1);
                }

                // Intermediate cell H-G-D
                else if ( (int) cellPosition.get(1) > 0 && (int) cellPosition.get(1) < JeuFloodit.TAILLE_GRILLE-1  ){
                    return ((int) cellPosition.get(0) == (int) position.get(0)-1)
                            || ((int) cellPosition.get(1) == (int) position.get(1)+1)
                            || ((int) cellPosition.get(1) == (int) position.get(1)-1);
                }
                else {
                    continue;
                }
            }

            //  Actuellement en lignes Intermediaires
            else if((int) cellPosition.get(0) > 0
                    && (int) cellPosition.get(0) < JeuFloodit.TAILLE_GRILLE -1){
                // Colonne 1
                if((int) cellPosition.get(1) == 0){
                    return ((int) cellPosition.get(0) == (int) position.get(0)+1)
                            || ((int) cellPosition.get(0) == (int) position.get(0)-1)
                            || ((int) cellPosition.get(1) == (int) position.get(1)+1);
                }
                // colonne N
                else if((int) cellPosition.get(1) == JeuFloodit.TAILLE_GRILLE -1){
                    return ((int) cellPosition.get(0) == (int) position.get(0)+1)
                            || ((int) cellPosition.get(0) == (int) position.get(0)-1)
                            || ((int) cellPosition.get(1) == (int) position.get(1)-1);
                }

                else{
                    continue;
                }
            }

            else{
                 continue;
            }
        }
        return false;
    }

    /***
     *  Colorie la cellule avec la nouvelle couleur
     * @param newColor
     */
    public void colorer(int newColor){
        this.previousColor = currentColor;
        this.currentColor = newColor;
    }


    public Vector getPosition() {
        return position;
    }

    public int getCurrentColor() {
        return currentColor;
    }

    public int getColorBefore() {
        return previousColor;
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    public void setPreviousColor(int previousColor) {
        this.previousColor = previousColor;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public String toString(){
        return Integer.toString(this.currentColor);
    }


}
