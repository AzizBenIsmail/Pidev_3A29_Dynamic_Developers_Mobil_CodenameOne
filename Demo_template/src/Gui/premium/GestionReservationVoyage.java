/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.premium;

import Gui.ReserverVoyage.ShowReservationVoyage;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ASUS
 */
public class GestionReservationVoyage extends Form {
Form current;
private Resources res;

    public GestionReservationVoyage(Form previous)
    {
            current =this;
            
                    add(new Label("Welcom to Travel_Me Premium"));
        setTitle(" --Reservation Voyage-- ");
        setLayout(BoxLayout.y());
            
            Button AfficherReservationVoyage= new Button("Afficher les reservation");

AfficherReservationVoyage.addActionListener((evt) -> new GestionShowReservationVoyage(res,current).show());
addAll(AfficherReservationVoyage);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        previous.showBack();
        });   
    }
    
}
