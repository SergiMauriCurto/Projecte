/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author profe
 */
public class Projecte {

    //Número de caselles màxim de l'array
    private static final int MAX_JUGADORS = 2000;
    //Array on guardarem la informació dels jugadors
    private static Jugador[] array = new Jugador[MAX_JUGADORS];
    //Opció triada per l'usuari
    private static int opcio;
    //Fitxer usat per persistir la informació
    private static File fitxer=new File("jugadors.db");

    public static Jugador[] getArray() {
        return array;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        inicialitzarVariables();
        do {
            demanarOpcio();
            tractarOpcio();
        } while (!opcioFinal());

    }

    public static void inicialitzarVariables() {
        
        //Índex per recòrrer l'array
        int i=0;
        
        //Busquem el fitxer, i si existix el tractem
        if(fitxer.exists()){
            //L'usem per si no caben els objectes del fitxer a l'array poder finalitzar l'execució
            boolean acabar=false;
            
            //Obrim el fitxer per lectura
            ObjectInputStream lectura=null;
            try{
                //Obrim el fitxer per lectura
                lectura=new ObjectInputStream(new BufferedInputStream(new FileInputStream(fitxer)));
                
                while(true){
                    array[i]=(Jugador) lectura.readObject();
                    //Incrementar la i per separat ja que sinó dixem una casella a null
                    i++;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                //Si entrem aquí és per que al fitxer hi ha més jugadors que els que caben a l'array. 
                //Podríem avisar a l'usuari i dixar que tanque l'aplicació ja que sinó pot perdre dades...
                System.err.println("Atenció, no caben tots els objectes. Si continues pots perdre dades. Vols continuar?(S/N):");
                Scanner ent = new Scanner(System.in);
                char siNo=' ';
                do {                    
                    siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                    //per tant només haurem de tractar les lletres majúscules
                } while (siNo != 'S' && siNo != 'N');
                if(siNo=='N') acabar=true;
                
            } catch (IOException ex) {
                //Aquí no cal fer res ja que significa que hem arribat al final del fitxer
            } catch (ClassNotFoundException ex) {
                //Aquí tampoc cal fer res ja que significa que el fitxer llegit no conté objectes de la classe Jugador
            }finally{
                try {
                    //Molt important tancar el fitxer de lectura
                    if(lectura!=null) lectura.close();
                } catch (IOException ex) {
                    //No cal mostrar res
                }
                //Si hem decidit acabar parem l'execucuió
                if(acabar) System.exit(0);
            }
        
        }
        //Acabem d'omplir l'array en nous jugadors sense dades
        for (; i < array.length; i++) {
            array[i] = new Jugador();
            array[i].setOmplit(false);
        }
    }

    public static void demanarOpcio() {
        Scanner ent = new Scanner(System.in);

        do{
            System.out.println("Escull una opció del menu seguent:");
    
            System.out.println("0) Sortir");
            System.out.println("1) Afegir Jugador");
            System.out.println("2) Modificar Jugador");
            System.out.println("3) Borrar Jugador");
            System.out.println("4) Llistar Jugadors");
            System.out.println("5) Recuperar jugador borrat:");
            
            try{
                opcio = ent.nextInt();
                break;
            }catch(java.util.InputMismatchException e){
                System.out.println("Opció incorrecta!!");
                //Molt important posar el next per evitar bucle infinit
                ent.next();
                continue;
            }
        }while(true);

    }

    public static void tractarOpcio() {

        switch (opcio) {
            case 0:                             //0. Sortir
                finalitzar();
                break;
            case 1:                             //1. Introduïr jugador
                introduirJugador();
                break;
            case 2:                             //2. Modificar jugador
                modificarJugador();
                break;
            case 3:                                     //3. Borrar jugador
                borrarJugador();
                break;
            case 4:                                     //4. Llistar jugador
                llistarJugador();
                break;
            default:
                System.out.println("\nOpció incorrecta!!");
        }

    }

    public static boolean opcioFinal() {
        return opcio == 0;
    }
    
    public static void finalitzar(){
        //Obrim el fitxer per escriptura
        ObjectOutputStream escriptura=null;
        try{
            //Obrim el fitxer
            escriptura=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fitxer)));

            //Recorrem l'array guardant els objectes vàlids al fitxer
            for(int i=0;i<array.length;i++){
                //Guardem al fitxer els jugadors omplits
                if(array[i].isOmplit())escriptura.writeObject(array[i]);
            }
        } catch (IOException ex) {
            //Aquí podem avisar a l'usuari de que no s'han guardat les dades
            System.err.println("Error en guardar les dades!!");
        } finally{
            try {
                //Molt important tancar el fitxer d'escriptura
                if(escriptura!=null) escriptura.close();
            } catch (IOException ex) {
                //No cal mostrar res
            }

        }

        //Missatge de comiat
        System.out.println("Adéu!!");

    }

    public static void introduirJugador() {
        Scanner ent = new Scanner(System.in);

        //Primer recorrem l'array fins trobar una casella no omplida o arribar al seu final
        int i;
        for (i = 0; i < array.length && array[i].isOmplit(); i++);
        //Si no hem arribat al final és per que hem trobat una casella buida (no omplida)
        if (i < array.length) {
            System.out.println("\nNom:");
            array[i].setNom(ent.skip("[\r\n]*").nextLine());
            do{
                try{
                    System.out.println("Dorsal:");
                    array[i].setDorsal(ent.nextInt());
                    break;
                }catch(java.util.InputMismatchException e){
                    System.out.println("Dorsal incorrecte!!");
                    ent.next();
                }
            }while(true);
            do{
                try{
                    System.out.println("Sou:");                
                    array[i].setSou(ent.nextDouble());
                    break;
                }catch(java.util.InputMismatchException e){
                    System.out.println("No és un número correcte!!");
                    ent.next();
                }
            }while(true);
            char esHome;
            do {
                System.out.println("És home o dona?(H/D):");
                esHome = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                //per tant només haurem de tractar les lletres majúscules
            } while (esHome != 'H' && esHome != 'D');
            array[i].setSexe(esHome == 'H');     //si esHome conté la 'H' home serà true i sinó false. Fa el mateix que un if_else però és molt més curt
            array[i].setOmplit(true);
        } else {
            System.out.println("\nNo hi caben més jugadors, si vols, primer borra'n.");
        }
    }

    public static void modificarJugador() {
        Scanner ent = new Scanner(System.in);
        //Primer recorrem l'array buscant caselles omplides i preguntant quina volem modificar
        char siNo = 'N';
        int cont = 1, i;
        for (i = 0; i < array.length && siNo != 'S' && siNo != 'F'; i++) {
            if (array[i].isOmplit()) {
                System.out.format("\nJugador %d:\n", cont++);
                System.out.println(array[i].toString());
                do {
                    System.out.println("\nVols modificar el jugador(S/N) o finalitzar la cerca (F)?:");
                    siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                    //per tant només haurem de tractar les lletres majúscules
                } while (siNo != 'S' && siNo != 'N' && siNo != 'F');
            }
            if (siNo == 'S') {
                break;
            }
        }
        //Si l'usuari ha contestat que sí és que ha triat un jugador per modificar    
        if (siNo == 'S') {

            System.out.println("\nNom: " + array[i].getNom());
            do {
                System.out.println("\nVols modificar el nom?(S/N):");
                siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
            } while (siNo != 'S' && siNo != 'N');
            if (siNo == 'S') {
                System.out.print("Nou nom: ");
                array[i].setNom(ent.skip("[\r\n]*").nextLine());
            }

            System.out.println("\nDorsal: " + array[i].getDorsal());
            do {
                System.out.println("\nVols modificar el dorsal?(S/N):");
                siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
            } while (siNo != 'S' && siNo != 'N');
            if (siNo == 'S') {
                System.out.print("Nou dorsal: ");
                array[i].setDorsal(ent.skip("[\r\n]*").nextInt());
            }

            System.out.println("\nSou: " + array[i].getSou());
            do {
                System.out.println("\nVols modificar el sou?(S/N):");
                siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
            } while (siNo != 'S' && siNo != 'N');
            if (siNo == 'S') {
                System.out.print("Nou Sou: ");
                array[i].setSou(ent.skip("[\r\n]*").nextDouble());
            }

            if (array[i].isHome()) {
                System.out.println("\nÉs home");
            } else {
                System.out.println("\nÉs dona");
            }
            do {
                System.out.println("\nVols modificar el gènere?(S/N):");
                siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
            } while (siNo != 'S' && siNo != 'N');
            if (siNo == 'S') {
                char esHome;
                do {
                    System.out.println("És home o dona?(H/D):");
                    esHome = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
                } while (esHome != 'H' && esHome != 'D');
                array[i].setHome(esHome == 'H');     //si esHome conté la 'H' home serà true i sinó false. Fa el mateix que un if_else però és molt més curt
                System.out.print("Nou gènere: ");
                if (array[i].isHome()) {
                    System.out.println("home");
                } else {
                    System.out.println("dona");
                }
            }

            System.out.println("Jugador modificat correctament.");

        } else {
            System.out.println("\nNo hi ha jugadors per modificar, o no n'has triat cap per modificar.");
        }

    }

    public static void borrarJugador() {
        //Variables locals
        Jugador p = null;   //l'utilizo per apuntar al Jugador de les caselles de l'array
        Scanner ent = new Scanner(System.in);
        char siNo = 'N';
        int i;
        for (i = 0; i < array.length && siNo != 'F'; i++) {
            p = array[i];
            if (p.isOmplit()) {
                System.out.println(p);
                do {
                    System.out.println("\nVols borrar el jugador(S/N) o finalitzar la cerca (F)?:");
                    siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                    //per tant només haurem de tractar les lletres majúscules
                } while (siNo != 'S' && siNo != 'N' && siNo != 'F');
            }
            if (siNo == 'S') {
                break;
            }
        }

        if (siNo == 'S') {
            p.setOmplit(false);
            System.out.println("Jugador borrat correctament.");

        } else {
            System.out.println("\nNo s'ha borrat cap jugador.");
        }
    }

    public static void llistarJugador() {
        Scanner ent = new Scanner(System.in);

        boolean algun = false;
        char siNo = 'S';
        int i;
        for (i = 0; i < array.length; i++) {
            Jugador p = array[i];
            if (p.isOmplit()) {
                algun = true;
                System.out.println(p);
                do {
                    System.out.println("\nVols vore més jugadors(S/N)?:");
                    siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                    //per tant només haurem de tractar les lletres majúscules
                } while (siNo != 'S' && siNo != 'N');
            }
            if (siNo == 'N') {
                break;
            }
        }
        if (!algun) {
            System.out.println("\nNo hi ha jugadors per mostrar, si vols, primer crea'n.");
        }
    }

    public static void recuperarJugador(){
        Scanner ent = new Scanner(System.in);
        //Primer recorrem l'array buscant caselles buides i preguntant quina volem recuperar
        char siNo = 'N';
        int cont = 0, i;
        for (i = 0; i < array.length && siNo != 'S' && siNo != 'F'; i++) {
            if (!array[i].isOmplit()) {
                System.out.format("\nJugador %d:\n", ++cont);
                System.out.println(array[i].toString());
                do {
                    System.out.println("\nVols recuperar el jugador(S/N) o finalitzar la cerca (F)?:");
                    siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                    //per tant només haurem de tractar les lletres majúscules
                } while (siNo != 'S' && siNo != 'N' && siNo != 'F');
            }
            if (siNo == 'S') {
                break;
            }
        }
        //Si l'usuari ha contestat que sí és que ha triat un jugador per modificar    
        if (siNo == 'S') {
            array[i].setOmplit(true);
            System.out.println("Jugador recuperat correctament.");
        } else {
            if(cont==0) System.out.println("No hi ha jugadors per recuperat.");
            else System.out.println("Jugador no recuperat.");
        }
    
    }
}