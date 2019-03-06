
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
