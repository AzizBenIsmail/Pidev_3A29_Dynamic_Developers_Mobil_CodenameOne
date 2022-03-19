/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Voyage;

import Entity.Voyage;
import Servise.ServiceVoyage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ASUS
 */
public class AddVoyage extends Form {

    public AddVoyage(Form previous) {
        setTitle("Add Voyage");
        setLayout(BoxLayout.y());
        TextField Destination = new TextField("", "Destination");
        TextField Nom_Voyage = new TextField("", "Nom_Voyage");
        TextField Duree_Voyage = new TextField("", "Duree_Voyage");
        TextField Prix = new TextField("", "Prix");
        TextField Valabilite = new TextField("", "Valabilite");
        TextField image = new TextField("", "image");
        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Destination.getText().length()==0)||(Nom_Voyage.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Voyage Voyage = new Voyage(Integer.parseInt(Prix.getText()),Destination.getText(),Nom_Voyage.getText(),Duree_Voyage.getText(),Valabilite.getText(),image.getText());
                        if( ServiceVoyage.getInstance().AddVoyage(Voyage))
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
          addAll(Destination,Nom_Voyage,Duree_Voyage,Prix,Valabilite,image,btnValider);
    }  

}
