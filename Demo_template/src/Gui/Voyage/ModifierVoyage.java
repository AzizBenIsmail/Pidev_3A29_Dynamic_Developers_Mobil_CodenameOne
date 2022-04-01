/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Voyage;

import Entity.Voyage;
import Servise.ServiceVoyage;
import com.codename1.components.InfiniteProgress;
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
public class ModifierVoyage extends Form {

    public ModifierVoyage(Form previous,Voyage v) {
        setTitle("Add Voyage");
        setLayout(BoxLayout.y());
        TextField Destination = new TextField(v.getDestination(), "Destination");
                        Destination.getStyle().setFgColor(154245);
        TextField Nom_Voyage = new TextField(v.getNom_Voyage(), "Nom_Voyage");
                        Nom_Voyage.getStyle().setFgColor(154245);
        TextField Duree_Voyage = new TextField(v.getDuree_Voyage(), "Duree_Voyage");
                        Duree_Voyage.getStyle().setFgColor(154245);
        TextField Prix = new TextField("123","2453");
                        Prix.getStyle().setFgColor(154245);
        TextField image = new TextField(v.getImage(), "image");
                            image.getStyle().setFgColor(154245);
        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Destination.getText().length()==0)||(Nom_Voyage.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {  InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    try {
                        v.setId(v.getId());
                        v.setPrix_Voyage((int) Float.parseFloat(Prix.getText()));
                        v.setDestination(Destination.getText());
                        v.setNom_Voyage(Nom_Voyage.getText());
                        v.setDuree_Voyage(Duree_Voyage.getText());
                        v.setImage(image.getText());
                        if(ServiceVoyage.getInstance().UpdateVoyage(v))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           previous.showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                            previous.showBack();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        previous.showBack();
                    }
                    
                }            }
        });
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        previous.showBack();
        });
          addAll(Destination,Nom_Voyage,Duree_Voyage,Prix,image,btnValider);
    }  

}
