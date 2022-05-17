package Gui.Excursion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Excursion;
import Entity.ReservationExcursion;
import Gui.Excursion.ListExcursionForm;
import Gui.Excursion.ModifierReservationForm;
import Servise.ServiceExcursion;
import Servise.ServiceReservationExcursion;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Oumayma
 */
public class ListReservationExcursionForm extends Form{
    Form f ;
        Form current;
                        private Resources theme;

    public ListReservationExcursionForm() {
        f=this;
        f.setTitle("Mes Reservation");
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListExcursionForm(theme,current).show());
        ArrayList<ReservationExcursion>list = ServiceReservationExcursion.getInstance().affichageReservationExcursions();
        
        for(ReservationExcursion rec : list ) {
            
            ArrayList<Excursion> ex = ServiceExcursion.getInstance().affichageExcursions();
            
            Excursion l=new Excursion();
            for(Excursion i:ex){
               if(i.getId()==rec.getIdex()){
                  l=new Excursion(i.getId(), i.getPrix(),i.getNom(), i.getDescription(),i.getType(),i.getLieu(),i.getImage(),i.getValabilite(),i.getDate());
               }    
            }
                       
                
            
             String urlImage = l.getImage();//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120,170);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
             
                addButton(urlim,rec,l);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
    }
     private void addButton(Image img,ReservationExcursion rec ,Excursion l) {
        
       //// int height = Display.getInstance().convertToPixels(11.5f);
       // int width = Display.getInstance().convertToPixels(14f);
        
           Button image = new Button(img.fill(200,150));
          image.setUIID("Label");
          Container cnt = BorderLayout.west(image);
        
        
        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
        Label objetTxt = new Label("Date Reservation: "+rec.getDateR(),"NewsTopLine2");
        Label dateTxt = new Label("Nom Excursion : "+l.getNom(),"NewsTopLine2");
         Label desTxt = new Label("Lieu : "+l.getLieu(),"NewsTopLine2" );
        Label etatTxt = new Label("prix Totale : "+String.valueOf(l.getPrix()*rec.getNb()),"NewsTopLine2" );
        Label nb = new Label("Nombre du personness: "+String.valueOf(rec.getNb()),"NewsTopLine2" );
     
      
        
        //supprimer button
         Button lSupprimer= new Button();
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
         //click delete icon
        lSupprimer.addPointerPressedListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent l) {
             Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer cet Reservation ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServiceReservationExcursion.getInstance().deleteReservationExcursion(String.valueOf(rec.getId()))) {
                    new ListReservationExcursionForm().show();
                } 
            } });
       
        
        //Update icon 
        Button lModifier= new Button();
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        lModifier.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                //System.out.println("hello update");
                new ModifierReservationForm(rec).show();
            }
        });
        
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(objetTxt),
                BoxLayout.encloseX(dateTxt),
                BoxLayout.encloseX(desTxt),
                BoxLayout.encloseX(nb),
                BoxLayout.encloseX(etatTxt,lModifier,lSupprimer)));
        
       
        
        add(cnt);
     }


    
}
