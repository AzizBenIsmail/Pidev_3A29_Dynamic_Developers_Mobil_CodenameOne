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
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ShowVoyage extends Form {
    Form current;
        int n=0;
                private Resources theme;

    public ShowVoyage(Form previous,int n,String c) { 
        current =this;
        setTitle("Liste des Voyage");
                setLayout(BoxLayout.y());

            Button BUTActualiser = new Button("Actualiser");
        BUTActualiser.addActionListener((evt) -> new ShowVoyage(current,0,c).show());
            Button BUTTrieNom = new Button("Trie selon Nom ");
         BUTTrieNom.addActionListener((evt) -> new ShowVoyage(current,1,c).show());
            Button BUTTriePrix = new Button("Trie selon Prix ");
        BUTTriePrix.addActionListener((evt) -> new ShowVoyage(current,2,c).show());
            Button BUTTrieDest = new Button("Trie selon Dest ");
        BUTTrieDest.addActionListener((evt) -> new ShowVoyage(current,3,c).show());
            addAll(BUTTrieNom,BUTTriePrix,BUTTrieDest,BUTActualiser);
                    TextField Recherche = new TextField("", "Voyagesearch");
                        Recherche.getStyle().setFgColor(154245);
                        Button Voyagesearch = new Button("Voyagesearch");
        Voyagesearch.addActionListener((evt) -> new ShowVoyage(current,4,Recherche.getText()).show());
        
                add(Recherche);
                add(Voyagesearch);
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
              sp.setTextLine1("Destination : "+voyage.getDestination()+" | Nom : "+voyage.getNom_Voyage());
              sp.setTextLine2("Durèe : "+voyage.getDuree_Voyage());
              sp.setTextLine3(" Prix : "+voyage.getPrix_Voyage());

                     list.add(sp);
                     sp.addActionListener((evt) -> {
                         System.out.println("reserver");
                         if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            
                                                if( ServiceVoyage.getInstance().deletedVoyage(voyage.getId())){
                                                    {
                                                       Dialog.show("Success","Le Voyage "+voyage.getNom_Voyage()+" a été supprimé avec succées",new Command("OK"));
                                                       previous.showBack();
                                                    }
                                        }
                                    }
                                        else{ 
                                               
                                                 new ModifierVoyage(current,voyage).show();

                                        }
                                      //  new AddResrvationVoyage(current,voyage).show();
                     });
        }
        
         //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
            }
            
               if(n==1)
            {
        ArrayList<Entity.Voyage> voyages = ServiceVoyage.getInstance().order_By_NomJSON();
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
               
                  if(n==2)
            {
        ArrayList<Entity.Voyage> voyages = ServiceVoyage.getInstance().order_By_PrixJSON();
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
                  
                     if(n==3)
            {
        ArrayList<Entity.Voyage> voyages = ServiceVoyage.getInstance().order_By_DestJSON();
        Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
        for (Entity.Voyage voyage : voyages) {
            
              EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              Image i = URLImage.createToStorage(placeholder,voyage.getImage(),Statics.BASE_URL+"/uploads/"+voyage.getImage());
             MultiButton sp = new MultiButton(voyage.getNom_Voyage());
             sp.setIcon(i.fill(200, 200));
              sp.setTextLine1("Destination : "+voyage.getDestination()+" Prix : "+voyage.getPrix_Voyage());
              sp.setTextLine2("Durèe : "+voyage.getDuree_Voyage()+" Valabilite : "+voyage.getValabilite());
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
                     
           if(n==4)
            {
        ArrayList<Entity.Voyage> voyages = ServiceVoyage.getInstance().Voyagesearch(c);
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
           
            getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
            });
    }
    
    
    
}
