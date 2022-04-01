/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Restaurant;

import Gui.ReserverVoyage.*;
import Entity.ReserverVoyage;
import Entity.Voyage;
import Servise.ServiceVoyage;
import Servise.ServiseResrverVoyage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author ASUS
 */
public class AddResrvationRestaurant extends Form  {

   Entity.Restaurant r;
    public AddResrvationRestaurant(Form previous,Entity.Restaurant r) {
        this.r=r;
     setTitle("Add ResrvationVoyage");
        setLayout(BoxLayout.y());
        TextField Date_Reservation = new TextField("", "Date_Reservation");
                 Date_Reservation.getStyle().setFgColor(154245);
        TextField Nbr_Personne = new TextField("", "Nbr_Personne");
                Nbr_Personne.getStyle().setFgColor(154245);
        TextField Age = new TextField("", "Age");
                Age.getStyle().setFgColor(154245);
        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Nbr_Personne.getText().length()<9))
                    Dialog.show("Alert", "Svp il Faut Max 9 personne", new Command("OK"));
                else
                {

                        //ReserverVoyage reserverVoyage = new ReserverVoyage(Travel_Class.getText(),Integer.parseInt(Age.getText()),v,Integer.parseInt(Client.getText()));
                        if( true == true)
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Votre Cin n'existe pas", new Command("OK"));
                    
                }       }
        });
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        previous.showBack();
        });
          addAll(Date_Reservation,Nbr_Personne,Age,btnValider);
    }  
    
}
