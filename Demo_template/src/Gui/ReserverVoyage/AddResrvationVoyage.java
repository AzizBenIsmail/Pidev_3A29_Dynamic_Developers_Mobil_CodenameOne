/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.ReserverVoyage;

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
public class AddResrvationVoyage extends Form  {

    Voyage v;
    public AddResrvationVoyage(Form previous,Voyage v) {
        this.v=v;
     setTitle("Add ResrvationVoyage");
        setLayout(BoxLayout.y());
        TextField Client = new TextField("", "Client");
                 Client.getStyle().setFgColor(154245);
        TextField Travel_Class = new TextField("", "Travel_Class");
                Travel_Class.getStyle().setFgColor(154245);
        TextField Age = new TextField("", "Age");
                Travel_Class.getStyle().setFgColor(154245);
        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Client.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        ReserverVoyage reserverVoyage = new ReserverVoyage(Travel_Class.getText(),Integer.parseInt(Age.getText()),v,Integer.parseInt(Client.getText()));
                        if( ServiseResrverVoyage.getInstance().AddReservationVoyage(reserverVoyage))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }            }
        });
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        previous.showBack();
        });
          addAll(Client,Travel_Class,Age,btnValider);
    }  
    
}
