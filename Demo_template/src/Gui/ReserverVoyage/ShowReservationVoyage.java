/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.ReserverVoyage;

import Servise.ServiseResrverVoyage;
import Servise.ServiseRestaurant;
import Utils.Statics;
import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ShowReservationVoyage extends Form {
    Form current;
    public ShowReservationVoyage(Form previous) {
        
           setTitle("Liste des Reservation des Voyages");
        setLayout(BoxLayout.y());
        ArrayList<Entity.ReserverVoyage> ReserverVoyages = ServiseResrverVoyage.getInstance().affichageReserverVoyage();
        Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
        for (Entity.ReserverVoyage ReserverVoyage : ReserverVoyages) {
            
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              Image i = URLImage.createToStorage(placeholder,ReserverVoyage.getVoyage().getImage(),Statics.BASE_URL+"/uploads/"+ReserverVoyage.getVoyage().getImage());
             MultiButton sp = new MultiButton(ReserverVoyage.getTravel_Class());
                          sp.setIcon(i.fill(200, 200));
              sp.setTextLine1("Travel_Class : "+ReserverVoyage.getTravel_Class());
              sp.setTextLine2("Age : "+ReserverVoyage.getAge());
                     list.add(sp);
                     
                     sp.addActionListener((evt) -> {
                         
                         // details
                         //affichage en details details(voyage).show(); 
                         //autre page 
                         //ajouter panier 
                     });
        }
        
         //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        
    }
    
}
