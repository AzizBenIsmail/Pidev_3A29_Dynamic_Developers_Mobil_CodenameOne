/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.ReserverVoyage;

import Entity.ReserverVoyage;
import Gui.Admin.Admin;
import Gui.Voyage.AddVoyage;
import Servise.ServiseResrverVoyage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author ASUS
 */
public class MenuResrverVoyage extends Form {
Form current;
    public MenuResrverVoyage(Form previous) {
                        current =this;
        String Admin="Admin";
        TextField Nom = new TextField("", "Nom");
                Nom.getStyle().setFgColor(154245);
        TextField Passwrd = new TextField("", "Passwrd");
                        Passwrd.getStyle().setFgColor(154245);
        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
          if(Nom.getText().equals(Admin) && Passwrd.getText().equals(Admin))
        {    
                 new Admin(current).show();
                 System.out.println("hello");
        } else {
            
        }            
            }
        });

        addAll(Nom,Passwrd,btnValider);

    }
    
}
