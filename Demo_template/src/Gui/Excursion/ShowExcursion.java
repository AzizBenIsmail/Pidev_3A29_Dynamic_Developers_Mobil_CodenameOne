/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Excursion;

import Servise.ServiseExcusion;
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
public class ShowExcursion  extends Form {

    public ShowExcursion(Form previous) {
         setTitle("Liste des Excursion");
        setLayout(BoxLayout.y());
        ArrayList<Entity.Excursion1> Excursions = ServiseExcusion.getInstance().affichageExcursion();
        Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
        for (Entity.Excursion1 Excursion : Excursions) {
            
              EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              Image i = URLImage.createToStorage(placeholder,Excursion.getImage(),Statics.BASE_URL+"/uploads/"+Excursion.getImage());
             MultiButton sp = new MultiButton(Excursion.getDescription_Excursion());
             sp.setIcon(i.fill(200, 200));
              sp.setTextLine1("Nom : "+Excursion.getNom_Excursion()+" Lieu : "+Excursion.getLieu());
              sp.setTextLine2("Type : "+Excursion.getType_Excursion()+" | Prix : "+Excursion.getPrix());
                     list.add(sp);
                     
                     sp.addActionListener((evt) -> {
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
