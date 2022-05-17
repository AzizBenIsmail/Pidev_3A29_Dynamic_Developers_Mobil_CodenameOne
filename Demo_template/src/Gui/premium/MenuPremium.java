/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.premium;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ASUS
 */
public class MenuPremium extends Form {
Form current;
    public MenuPremium(Form previous) {
       current =this;
        add(new Label("Welcom to Travel_Me Premium"));
        setTitle("Welcom to Travel_Me Premium");
        setLayout(BoxLayout.y());
        
            Button GestionVoyage= new Button("GestionVoyage");
            Button GestionReservationVoyage= new Button("GestionReservationVoyage");

GestionVoyage.addActionListener((evt) -> new GestionVoyage(current).show());
GestionReservationVoyage.addActionListener((evt) -> new GestionReservationVoyage(current).show());
 addAll(GestionVoyage,GestionReservationVoyage);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_EXIT_TO_APP, e -> previous.showBack());
    }
    
}
