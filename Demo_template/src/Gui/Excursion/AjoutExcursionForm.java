package Gui.Excursion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Excursion;
import Gui.Excursion.ListExcursionForm;
import Servise.ServiceExcursion;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

/**
 *
 * @author Oumayma
 */
public class AjoutExcursionForm extends Form {
    Form current;
                                  private Resources theme;

    public AjoutExcursionForm() {
            current=this;
        
        setTitle("Proposer Nous une Excursion");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListExcursionForm(theme,current).show());
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        TextArea nom = new TextArea();
        nom.setRows(2);
        nom.setHint("Donner un Nom pour votre excursion");
        
        TextArea description = new TextArea();
        description.setRows(5);
        description.setHint("Description Excursion");
        
        Picker date= new Picker();
        date.setType(Display.PICKER_TYPE_DATE);
        date.setDate(new Date());
        
        CheckBox historique = new CheckBox("Historique");
        CheckBox culturelle = new CheckBox("Culturelle");
     
        TextArea lieu = new TextArea();
        lieu.setRows(2);
        lieu.setHint("Lieu Excursion");
        
        TextArea prix = new TextArea();
        prix.setRows(2);
        prix.setHint("Prix Excursion");
        
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
        Button submitButton = new Button("Submit");
        
        submitButton.addActionListener((evt)->{
            try {
                 if(lieu.getText().equals("") || description.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }else if(historique.isSelected()&& culturelle.isSelected()){
                      Dialog.show("Veuillez selectionner un seul type ","","Annuler", "OK");
                 }
                
                else { 
               
                    InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    //njibo iduser men session (current user)
                    Excursion r = new Excursion();
                    
                       java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");  
                       String type="";
                       if(historique.isSelected()){
                          type="Historique" ; 
                       }else{
                           type="Culturelle"; 
                       }
                //  Excursion(float prix, String Nom, String Description, String Type, String Lieu, String Image, String valabilite, String date);
                      r = new Excursion(Float.parseFloat(prix.getText()),
                                  String.valueOf(nom.getText()).toString(),
                                  String.valueOf(description.getText()).toString(),
                                  type,
                                  String.valueOf(lieu.getText()).toString(),
                                  photoButton.getBadgeText().toString(),
                                  "Propositon",
                                 
                                  date.getDate().toString());
                                 //SessionManager.getId());  
                   /*  String y = String.valueOf(date.getDate().getYear());
                     String m = String.valueOf(date.getDate().getMonth());
                     String d = String.valueOf(date.getDate().getDay());
                     System.out.println(y+"/"+m+"/"+d);*/
                    
                    System.out.println("data  post == "+r);
                    
                    
                    //appelle methode ajouterPost mt3 service Post bch nzido données ta3na fi base 
                    ServiceExcursion.getInstance().ajoutExcursion(r);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListREclamationForm
                  new ListExcursionForm(theme,current).show();
            } }catch(Exception ex) {
                showError(ex.getMessage());
              
            }
        });
        
        
        Container l =new Container();
        l.setLayout(new BoxLayout(BoxLayout.X_AXIS));

        l.add(prix);
        l.add(lieu);
      
        Container t =new Container();
        t.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        t.add(culturelle); 
        t.add(historique);
        
        addComponent(nom);
        addComponent(l);
        addComponent(description);
        addComponent(t);
        addComponent(date);
        addComponent(photoButton);
        addComponent(submitButton);
       // addComponent(cancelButton);
        
        show();
    }
    private EncodedImage encodedImage(Image img) {
        if (img instanceof EncodedImage) {
            return (EncodedImage)img;
        } else {
            return EncodedImage.createFromImage(img, true);
        }
    }
    
    /**
     * Shows the specified error message in a modal dialog.
     * @param msg 
     */
    public void showError(String msg) {
        Dialog.show("Failed", msg, "OK", null);
    }
 protected String saveFileToDevice(String hi,String ext) throws IOException {
     ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest();
 URI uri;
 try{
   uri= new URI(hi);
   String path=uri.getPath();
   //connectionRequest.setUrl("http://localhost/testUploader/insert.php?path=" + path);
   int index = hi.lastIndexOf("/");
   hi=hi.substring(index + 1);
   return hi ;
 }catch (URISyntaxException ex){
 }
 return "null";
}
    }
    

