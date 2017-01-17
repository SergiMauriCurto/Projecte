/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

/**
 *
 * @author alumne
 */
public class Jugador {
   
    
        private String nom =null;
        private int dorsal =0;
        private String posicio =null;
        private double sou =0;
        private boolean sexe =false;
        private char esHome=' ';
        private int menu=0;
        private boolean omplit = true;
        private char opcio = ' ';

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicio() {
        return posicio;
    }

    public void setPosicio(String posicio) {
        this.posicio = posicio;
    }

    public double getSou() {
        return sou;
    }

    public void setSou(double sou) {
        this.sou = sou;
    }

    public boolean isSexe() {
        return sexe;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public char getEsHome() {
        return esHome;
    }

    public void setEsHome(char esHome) {
        this.esHome = esHome;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public boolean isOmplit() {
        return omplit;
    }

    public void setOmplit(boolean omplit) {
        this.omplit = omplit;
    }

    public char getOpcio() {
        return opcio;
    }

    public void setOpcio(char opcio) {
        this.opcio = opcio;
    }
        
}
