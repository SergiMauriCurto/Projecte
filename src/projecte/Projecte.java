/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import java.util.Scanner;
import java.lang.RuntimeException;
/**
 *
 * @author alumne
 */
public class Projecte {

    Jugador[] array=new Jugador[MAX_JUGADOR];
    int i=0;
    String s=new String('');
    Jugador sergi=new Jugador();
    array(0)
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada=new Scanner(System.in);
        
        String nom =null;
        int dorsal =0;
        String posicio =null;
        double sou =0;
        boolean sexe =false;
        char esHome=' ';
        int menu=0;
        boolean omplit = true;
        char opcio = ' ';
        
        while (menu != 1) {
        
            System.out.println("Escull una opció del menu seguent:");
    
            System.out.println("1) Sortir");
            System.out.println("2) Afegir Jugador");
            System.out.println("3) Borrar Jugador");
            System.out.println("4) Modificar Jugador");
            System.out.println("5) Llistar Jugadors");
            System.out.println("Opció:");
        
            menu=entrada.nextLine().charAt(0);
            
            if (menu == 1) {
                break;
            }
        
            switch (menu) {
                case '1':
                    System.out.println("Has escollit sortir del programa");
                    break;
                case '2':
                    System.out.println("Has escollit la opció de afegir un jugador:");
                    System.out.println("Introduccio de dades del Jugador:");
                    System.out.println("Nom:");
                    nom=entrada.nextLine();
                        if(omplit == false) {
                            System.out.println("");
                        } else {
                            System.out.println("La llista ja esta plena, borra el jugador per poder introduir-ne un de nou");
                            break;
                        }
                    
                    System.out.println("Dorsal:");
                    dorsal=entrada.skip("[\r\n]*").nextInt();
                    System.out.println("Posició:");
                    posicio=entrada.skip("[\r\n]*").nextLine();
                    System.out.println("Sou:");
                    sou=entrada.nextInt();
                    System.out.println("És home o dona?(H/D):");
                        do{
                            esHome = entrada.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);        
                                                                                           
                        }while(esHome != 'H' && esHome != 'D');
                            sexe = (esHome == 'H');
                    System.out.println("Dades introduides correctament!");
                    break;
                case '3':
                    System.out.println("Has escollit la opció de borrar un jugador:");
                    System.out.println("Esta segur que vol esborrar el jugador?(S/N)");
                    do{
                            esHome = entrada.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);        
                                                                                           
                        }while(esHome != 'S' && esHome != 'N');
                            sexe = (esHome == 'S');
                            
                    System.out.println("El jugador s'ha esborrat correctament");
                    break;
                case '4':
                    System.out.println("Has escollit la opció de modificar un jugador:");
                    break;
                case '5':
                    System.out.println("Has escollit la opció de llistar un jugador:");
                    if (omplit == true) {
                        System.out.println("Vols veure les dades del jugador? S/N");
                        opcio = entrada.next().charAt(0);
                    }
                    if (omplit == false) {
                        System.out.println("No hi ha ningun jugador a mostrar");
                                }
                     do {
                            switch (opcio) {
                                case 'S':
                                    System.out.println("Nom:" + nom);
                                    System.out.println("Dorsal:" + dorsal);
                                    System.out.println("Posició:" + posicio);
                                    System.out.println("Sou:" + sou);
                                    System.out.println("Sexe:" + sexe);
                                   
                                    opcio = 'N';
                                    break;
                                case 'N':
                                    System.out.println("");
                                    break;

                                default:
                                    System.out.println("L'opcio no es valida");
                                    System.out.println("Vols veure les dades del jugador? S/N");
                                    opcio = entrada.next().charAt(0);
                                    break;
                            }
             
                            default:
                    System.out.println("No és una opció del menu!");
                    break;
                    
                }
            }
        }
    }
}

                    

                    
        
    
