/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.premium;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author ASUS
 */
public class GestionReservationVoyage extends Form {
Form current;
    public GestionReservationVoyage(Form previous)
    {
            current =this;
            
            
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        previous.showBack();
        });   
    }
    
}
