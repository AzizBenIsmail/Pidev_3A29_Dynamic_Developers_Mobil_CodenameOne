/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Excursion;
import Entity.Excursion;
import Servise.ServiceExcursion;
import Utils.Statics;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.ProfileForm;
import java.util.ArrayList;

/**
 *
 * @author Oumayma
 */
public class ListExcursionForm extends Form {
    Form f;
                            private Resources theme;
    Form current;

    public ListExcursionForm(Resources theme,Form previous) {
        f=this;
        previous=this;
        f.setTitle("Liste des Excursion");
        f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        f.setLayout(new BorderLayout());
       
        
         FloatingActionButton.setIconDefaultSize(5);
         FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
         fab.addActionListener(e -> new AjoutExcursionForm().show());
         fab.bindFabToContainer(f.getContentPane());
         
         FloatingActionButton.setIconDefaultSize(5);
         FloatingActionButton fa = FloatingActionButton.createFAB(FontImage.MATERIAL_STORE);
         fa.addActionListener(e -> new ListReservationExcursionForm().show());
         fa.bindFabToContainer(f.getContentPane());
         
        Container list = new Container();
        list.setScrollableY(true);
        list.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Dialog progress = new InfiniteProgress().showInifiniteBlocking();
         ArrayList<Excursion> Excursions = ServiceExcursion.getInstance().affichageExcursions();
        for (Excursion Excursion : Excursions) {
            
              System.out.println(Excursion.getId());
              EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50,50, 0xffff0000), true);
              Image i = URLImage.createToStorage(placeholder,Excursion.getImage(),Statics.BASE_URL+"/uploads/"+Excursion.getImage()).fill(200,150);
              Container ex = new Container();
              ex.setUIID("Excursiondetail");
              ex.setLayout(new BoxLayout(BoxLayout.X_AXIS));
              ex.add(i);
              
              Container d = new Container();
              d.setUIID("detail");
              d.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
              
              String nom = Excursion.getNom();
              String lieu= " Lieu : "+ Excursion.getLieu();
              String date = "Date : "+ Excursion.getDate();
              String pt= "Type : "+Excursion.getType()+" | Prix : "+Excursion.getPrix();
              Button b = new Button("Voir Plus");
              b.getAllStyles().setAlignment(RIGHT);
               b.addActionListener((e)->{
                 new DetailsExcursionForm(Excursion,f).show();
               });
              d.add(new Label(nom));
              d.add(new Label(lieu));
              d.add(new Label(date));
              d.add(new Label(pt));
              d.add(b);
              ex.add(d);
              list.add(ex);
        }
         progress.dispose();
     
         f.add(CENTER,list); 
         f.show();
        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ProfileForm(theme,current).show());

    }
    
 
}
