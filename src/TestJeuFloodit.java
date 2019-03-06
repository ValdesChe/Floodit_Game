
import java.util.Scanner;

/**
 * Created by valde on 2019-02-16.
 */
public class TestJeuFloodit {

    public static void  main (String args[]){
        JeuFloodit jeuFloodit = new JeuFloodit();
        jeuFloodit.initFloodit();
        jeuFloodit.tracerGrille();

        Scanner scanner = new Scanner(System.in);
        int color = 0 ;
        while(jeuFloodit.nbCoups > 0 && !jeuFloodit.verifier() ){
            color = scanner.nextInt();
            if (color >=0 && color <=5){
                jeuFloodit.colorer(color);
                jeuFloodit.tracerGrille();
            }
            else{
                System.out.println("VEUILLEZ SAISIR UNE VALEUR ENTRE 0 - 5");
            }
        }

        if(jeuFloodit.verifier()){
          System.out.println("VOUS AVEZ GAGNÉ LA PARTIE !");
        }else{
          System.out.println(" *_* VOUS AVEZ PERDU  *_* ");
        }

        // System.out.println(jeuFloodit.listVoisin(2,2).toString());
        /*jeuFloodit.colorer(5);
        jeuFloodit.TracerGrille();

        jeuFloodit.colorer(2);
        jeuFloodit.TracerGrille();

        jeuFloodit.colorer(1);
        jeuFloodit.TracerGrille();

        jeuFloodit.colorer(4);
        jeuFloodit.TracerGrille();


        jeuFloodit.colorer(5);
        jeuFloodit.TracerGrille();


        jeuFloodit.colorer(3);
        jeuFloodit.TracerGrille();*/


    }

}
