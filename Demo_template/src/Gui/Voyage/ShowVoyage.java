/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Voyage;

import Gui.ReserverVoyage.AddResrvationVoyage;
import com.mycompany.myapp.*;
import Servise.ServiceVoyage;
import Utils.Statics;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ShowVoyage extends Form {
    Form current;
        int n=0;
    public ShowVoyage(Form previous,int n) { 
        current =this;
        setTitle("Liste des Voyage");
                setLayout(BoxLayout.y());

            Button BUTActualiser = new Button("Actualiser");
        BUTActualiser.addActionListener((evt) -> new ShowVoyage(current,0).show());
            Button BUTTrieNom = new Button("Trie selon Nom ");
         BUTTrieNom.addActionListener((evt) -> new ShowVoyage(current,1).show());
            Button BUTTriePrix = new Button("Trie selon Prix ");
        BUTTriePrix.addActionListener((evt) -> new ShowVoyage(current,2).show());
            Button BUTTrieDest = new Button("Trie selon Dest ");
        BUTTrieDest.addActionListener((evt) -> new ShowVoyage(current,3).show());
            addAll(BUTTrieNom,BUTTriePrix,BUTTrieDest,BUTActualiser);
            if(n==0)
            {
        ArrayList<Entity.Voyage> voyages = ServiceVoyage.getInstance().affichageVoyage();
        Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
        for (Entity.Voyage voyage : voyages) {
            
              EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              Image i = URLImage.createToStorage(placeholder,voyage.getImage(),Statics.BASE_URL+"/uploads/"+voyage.getImage());
             MultiButton sp = new MultiButton(voyage.getNom_Voyage());
             sp.setIcon(i.fill(200, 200));
              sp.setTextLine1("Destination : "+voyage.getDestination()+" Prix : "+voyage.getPrix_Voyage());
              sp.setTextLine2("Durèe : "+voyage.getDuree_Voyage());
                     list.add(sp);
                     sp.addActionListener((evt) -> {
                         System.out.println("reserver");
                                        new AddResrvationVoyage(current,voyage).show();
                     });
        }
        
         //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
            }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
    
    
}
