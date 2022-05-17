/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Excursion;
import Entity.Excursion;
import Utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.ComponentAnimation;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;


/**
 *
 * @author Oumayma
 */
public class DetailsExcursionForm extends Form {
      Form f ;
      Form current;
                              private Resources theme;

     public DetailsExcursionForm(Excursion e,Form previous){
        current=this; 
        f=this;
        
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        
       
       //  setToolbar(tb);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (ActionEvent f) -> {
            new ListExcursionForm(theme,current).show();
        });
        
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(200,100, 0xffff0000), true);
        Image i = URLImage.createToStorage(placeholder,e.getImage(),Statics.BASE_URL+"/uploads/"+e.getImage()).fill(500,250);
        Container image = new Container();
        image.setLayout(new BorderLayout());
       // image.addComponent(LEFT,TOP,tb);
        image.add(CENTER,i);
       
        Container top = new Container();
        top.setLayout(new BorderLayout());
        Container nom = new Container();
        nom.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        nom.addComponent(new Label(e.getNom()));
       
                     Button local=new Button(e.getLieu());
                     Style localStyle = new Style(local.getUnselectedStyle());
                     FontImage localImage = FontImage.createMaterial(FontImage.MATERIAL_PLACE, localStyle);
                     local.setIcon(localImage);
       
        nom.addComponent(local);
          
                     Button prix=new Button(Float. toString(e.getPrix()));
                     Style prixStyle = new Style(prix.getUnselectedStyle());
                     FontImage prixImage = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, prixStyle);
                     prix.setIcon(prixImage);
     
                     Button date=new Button(e.getDate());
                     Style dateStyle = new Style(date.getUnselectedStyle());
                     FontImage dateImage = FontImage.createMaterial(FontImage.MATERIAL_EVENT, dateStyle);
                     date.setIcon(dateImage);
         nom.add(date);
         top.add(RIGHT,prix);
         top.add(LEFT,nom);
         
         Container d = new Container();
         
         d.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         d.add(top);
         String type= "Excursion "+e.getType();
         d.add(type);
         d.add("Description");
         d.add(e.getDescription());
         
         Button reserver=new Button("Reserver Maintenant");
                     Style reserverStyle = new Style(reserver.getUnselectedStyle());
                     FontImage reserverImage = FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, reserverStyle);
                     reserver.setIcon(reserverImage);
         reserver.addActionListener((l)->{
                 new AddReservationForm(e).show();
               });
                
        f.add(BOTTOM,reserver);
        f.add(TOP,image);
        f.add(CENTER,d);
        f.show();
        
     }
    
}
