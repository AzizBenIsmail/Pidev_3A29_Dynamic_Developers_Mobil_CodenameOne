/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.premium;

import Gui.ReserverVoyage.*;
import Entity.ReserverVoyage;
import Gui.Voyage.ModifierVoyage;
import Servise.ServiceVoyage;
import Servise.ServiseResrverVoyage;
import Servise.ServiseRestaurant;
import Utils.Statics;
import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.ProfileForm;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class GestionShowReservationVoyage extends Form {
    Form current;
    public GestionShowReservationVoyage(Resources res,Form previous) {
        current=this;
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
              sp.setTextLine1("Vous aver reserver le Voyage : "+ReserverVoyage.getVoyage().getNom_Voyage());
              sp.setTextLine2("Travel_Class : "+ReserverVoyage.getTravel_Class());
              sp.setTextLine3("Age : "+ReserverVoyage.getAge());    
                     list.add(sp);
                     
                     sp.addActionListener((evt) -> {
                         System.out.println("reserver");
                         if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            
                                                if(ServiseResrverVoyage.getInstance().deletedReservationVoyage(ReserverVoyage.getId())){
                                                    {
                                                       Dialog.show("Success","La reservation du Voyage "+ReserverVoyage.getVoyage().getNom_Voyage()+" a été supprimé avec succées",new Command("OK"));
                                                       previous.showBack();
                                                    }
                                        }
                                    }
                                        else{ 
                                               
                                             //    new ModifierVoyage(current,voyage).show();
                                        }
                                      //  new AddResrvationVoyage(current,voyage).show();
                     });
        }
        
         //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
        previous.showBack();
        });

        
    }
    
}
