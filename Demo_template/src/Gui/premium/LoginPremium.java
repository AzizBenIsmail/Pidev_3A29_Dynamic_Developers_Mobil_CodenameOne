/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.premium;

import Gui.Admin.Admin;
import Servise.ServicePremuim;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.ProfileForm;

/**
 *
 * @author ASUS
 */
public class LoginPremium extends Form{
Form current;                   
    public LoginPremium(Resources res,Form previous) {
                    setLayout(BoxLayout.y());

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
                    Dialog.show("Alert", "interdit il faut devenir premium", new Command("OK"));

        }            
            }
        });
TextField Demmande = new TextField("", "Votre Cin Pour Devnir Permium");
                        Demmande.getStyle().setFgColor(154245);
        Button Pre = new Button("Demmande");
        
        Pre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
          if(Demmande.getText().length()!=0)
        {    ServicePremuim.getInstance().DemandePremuim(Integer.parseInt(Demmande.getText()));
                    Dialog.show("Welcom", "Bienvenu dans la version Premium Verifier Voter mail", new Command("OK"));
        } else {
                    Dialog.show("Alert", "Votre Cin n'existe pas", new Command("OK"));

        }            
            }
        });
        
        
        
        addAll(Nom,Passwrd,btnValider,Demmande,Pre);
        
            
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> {
        new ProfileForm(res,this).show();
                   });

    }
    
}
