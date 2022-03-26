/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.premium;

import Gui.Admin.Admin;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.ProfileForm;

/**
 *
 * @author ASUS
 */
public class LoginPremium extends Form{
Form current;                   
    public LoginPremium(Resources res,Form previous) {
                   current =this;
        String Premium="Premium";
        TextField Nom = new TextField("", "Nom");
                Nom.getStyle().setFgColor(154245);
        TextField Passwrd = new TextField("", "Passwrd");
                        Passwrd.getStyle().setFgColor(154245);
        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
          if(Nom.getText().equals(Premium) && Passwrd.getText().equals(Premium))
        {    
                 new MenuPremium(current).show();
                    Dialog.show("Welcom", "Welcom to Premium", new Command("OK"));
        } else {
                    Dialog.show("Alert", "Login or passord failed", new Command("OK"));

        }            
            }
        });

        addAll(Nom,Passwrd,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> {
        new ProfileForm(res,this).show();
                   });

    }
    
}
