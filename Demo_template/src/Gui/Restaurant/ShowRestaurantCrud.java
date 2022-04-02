/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Restaurant;

import Gui.Restaurant.ModifierRestaurant;
import Servise.ServiceVoyage;
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
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ShowRestaurantCrud extends Form {

    public ShowRestaurantCrud(Form previous) {
         setTitle("Liste des Restaurant");
        setLayout(BoxLayout.y());
        ArrayList<Entity.Restaurant> restaurants = ServiseRestaurant.getInstance().affichageRestaurant();
        Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
        for (Entity.Restaurant restaurant : restaurants) {
            
              EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              Image i = URLImage.createToStorage(placeholder,restaurant.getImage(),Statics.BASE_URL+"/uploads/"+restaurant.getImage());
             MultiButton sp = new MultiButton(restaurant.getAdresse_Restaurant());
             sp.setIcon(i.fill(200, 200));
              sp.setTextLine1("Nom : "+restaurant.getNom_Restaurant());
              sp.setTextLine2("Adresse : "+restaurant.getAdresse_Restaurant());
                     list.add(sp);
                     
                     sp.addActionListener((evt) -> {
                           if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            
                                                if(ServiseRestaurant.getInstance().deletedRestaurant(restaurant.getId())){
                                                    {
                                                       Dialog.show("Success","Le Restaurant "+restaurant.getNom_Restaurant()+" a été supprimé avec succées",new Command("OK"));
                                                       previous.showBack();
                                                    }
                                        }
                                    }
                                        else{ 
                                               
                                                 new ModifierRestaurant(this,restaurant).show();
                                        }
                     });
        }
        
         //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
}
