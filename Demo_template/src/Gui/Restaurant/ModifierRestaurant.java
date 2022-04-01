/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Restaurant;

import Entity.Restaurant;
import Gui.Voyage.*;
import Entity.Voyage;
import Servise.ServiceVoyage;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Entity.Voyage;
import Servise.ServiceVoyage;
import Servise.ServiseRestaurant;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author ASUS
 */
public class ModifierRestaurant extends Form {

    public ModifierRestaurant(Form previous,Restaurant r) {
       setTitle("modifier Restaurant");
        setLayout(BoxLayout.y());
        TextField Nom_Restaurant = new TextField(r.getNom_Restaurant(), "Nom_Restaurant");
                        Nom_Restaurant.getStyle().setFgColor(154245);
        TextField Adresse_Restaurant = new TextField(r.getAdresse_Restaurant(), "Adresse_Restaurant");
                        Adresse_Restaurant.getStyle().setFgColor(154245);
        TextField Num_Tel_Restaurant = new TextField("5698547", "Num_Tel_Restaurant");
                        Num_Tel_Restaurant.getStyle().setFgColor(154245);
        TextField Description_Restaurant = new TextField(r.getDescription_Restaurant(), "Description_Restaurant");
                    Description_Restaurant.getStyle().setFgColor(154245);
                            
                             Button photoButton = new Button("Ajouter une Image");
        photoButton.setTextPosition(Label.BOTTOM);
        
        CheckBox multiSelect = new CheckBox("Multi-select");
    
   photoButton.addActionListener((ActionEvent e)->{
  if(FileChooser.isAvailable()){
    
     FileChooser.setOpenFilesInPlace(true);
   
     FileChooser.showOpenDialog(multiSelect.isSelected(),".jpg,.jpeg,.png/plain",(ActionEvent e2)->{
        if(e2==null || e2.getSource()==null){
            add("No file was selected");
            revalidate();
            return;
        }
        if(multiSelect.isSelected()){
          String[] paths=(String[]) e2.getSource();
          for (String path:paths){
            System.out.println(path);
            CN.execute(path);
          }
          return;
        }
         //menna 7atta el photoButton.setBadgeText(namePic); 9a3din ntal3ou fi esm taswira el 7a9ani 
         String file=(String) e2.getSource();
         
          System.out.println(file);
          String extension=null;
          if(file.lastIndexOf(".")>0) {
            extension = file.substring(file.lastIndexOf(".")+1);
            StringBuilder hi=new StringBuilder(file);
            
            if(file.startsWith("file://")){
            } else {
                hi.delete(0,7);
               }
            int lastIndexPeriod = hi.toString().lastIndexOf(".");
            Log.p(hi.toString());
            String ext = hi.toString().substring(lastIndexPeriod);
            String hmore = hi.toString().substring(0,lastIndexPeriod-1);
            try{
             String namePic = saveFileToDevice(file,ext);
             System.out.println(namePic);
              //cr.setFilename("file",namePic);//any unique name you want
            // photoButton.getIcon().setImageName(namePic);
              photoButton.setBadgeText(namePic); //7atit l'esm fl badge ta3 button bch najjam nesta3mlou el berra ml cha9lalla hadhi
            }catch(IOException ex){
            }
          }  
        if(file == null){
           add("No file was selected");
           revalidate();
        }else{
           Image logo;
           try{
             logo = Image.createImage(file).scaledSmallerRatio(256, 256);
             photoButton.setIcon(logo); //lenna bch tatla3lk taswira 9bal ma ta3ml submit
             //lenna nbdaw fl enregistrement ta3 taswira
             String imageFile=FileSystemStorage.getInstance().getAppHomePath()+photoButton.getBadgeText();
             try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)){
               //System.out.println(imageFile);
               ImageIO.getImageIO().save(logo,os,ImageIO.FORMAT_PNG,1);//3mlna save lel image fi wost file system storage
               System.out.println();
             }catch(IOException err){
             }
           }catch(IOException ex){
           }
             revalidate();
          
        }
     });
  }
  });
        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Nom_Restaurant.getText().length()==0)||(Adresse_Restaurant.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {  InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    try {
                        r.setId(r.getId());
                        r.setNum_Tel_Restaurant((int) Float.parseFloat(Num_Tel_Restaurant.getText()));
                        r.setNom_Restaurant(Nom_Restaurant.getText());
                        r.setAdresse_Restaurant(Adresse_Restaurant.getText());
                        r.setDescription_Restaurant(Description_Restaurant.getText());
                        r.setImage(photoButton.getBadgeText().toString());
                        if(ServiseRestaurant.getInstance().Updaterestaurants(r))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           previous.showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                            previous.showBack();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        previous.showBack();
                    }
                    
                }            }
        });
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        previous.showBack();
        });
          addAll(Nom_Restaurant,Adresse_Restaurant,Num_Tel_Restaurant,Description_Restaurant,photoButton,btnValider);
    }  
protected String saveFileToDevice(String hi,String ext) throws IOException {
     ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest();
 URI uri;
 try{
   uri= new URI(hi);
   String path=uri.getPath();
   int index = hi.lastIndexOf("/");
   hi=hi.substring(index + 1);
   return hi ;
 }catch (URISyntaxException ex){
 }
 return "null";
}
}
