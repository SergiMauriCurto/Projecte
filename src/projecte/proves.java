/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import javax.swing.JOptionPane;

/**
 *
 * @author alumne
 */
public class proves {
    
    //Boto Modificar
    
    if(!dadesCorrectes()){
        JOptionPane.showMessageDialog(this, "Dades de les caselles incorrectes!!");
        return;
    }
    int iArray=(int)taula.getValueAt(filaSel, 0);
    Pilot[] array=Projecte.getArray();
    
    array[iArray].setNom(casellaNom.getText().trim());
    array[iArray].setDorsal()Integer.valueOf(casellaDorsal.getText()));
    array[iArray].setDinersGuanyats(Double.valueOf(casellaGuanys.getText()));
    array[iArray].setHome(opcioHome.isSelected());
    
    GUI_UF3.carregaTaula(new String[]{"Fila", "Nom", "Dorsal", "Diners", "Home"},
            transformaDades(Projecte.getArray())
            , taula);
    
    //dadesCorrectes
    
    try {
        casellaNom.getText().trim().charAt(9);
        if(casellaNom.getText().trim().equals("")) throw mew StringIndexOutOfBoundsExcept
   } catch (StringIndexOutOfBoundsException e) {
}
    return true;
    }

    //Boto Sortir

    ProjecteJava.finalitzar();
    System.exit(1);
    
    //Tancar Finestra amb creueta

    ProjecteJava.finalitzar();