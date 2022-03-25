/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.ReserverVoyage;

import com.codename1.ui.Form;
import com.codename1.ui.TextField;

/**
 *
 * @author ASUS
 */
public class MenuResrverVoyage extends Form {

    public MenuResrverVoyage(Form previous) {
        String Admin="Admin";
        TextField Nom = new TextField("", "Nom");
                Nom.getStyle().setFgColor(154245);
        TextField Passwrd = new TextField("", "Passwrd");
                        Passwrd.getStyle().setFgColor(154245);
        if(Nom.getText()==Admin && Passwrd.getText()==Admin)
        { 
        } else {
            
        }
        

    }
    
}
