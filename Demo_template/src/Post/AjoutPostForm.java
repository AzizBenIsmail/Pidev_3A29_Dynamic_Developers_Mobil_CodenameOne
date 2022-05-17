/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Post;

import Entity.Post;
import Servise.ServicePost;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
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
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;
import java.net.URISyntaxException;
//import java.util.logging.Level;
//import java.util.logging.Logger;


/**
 *
 * @author Oumayma
 */
public class AjoutPostForm extends Form { 
    
                    private Resources theme;
    Form current;

    public AjoutPostForm(Form previous) {
        current=this;
        setTitle("Creer Publication");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        ComboBox typeCombo = new ComboBox();
        
        typeCombo.addItem("Public");
        
        typeCombo.addItem("Anonyme");
        
        if(typeCombo.equals("Public")) {
                               typeCombo.setSelectedIndex(0);
                          }
                       else if(typeCombo.equals("Anonyme")) {
                              typeCombo.setSelectedIndex(1);
                        }
        
        TextArea description = new TextArea();
        description.setRows(5);
        description.setHint("Partagez avec nous vos meilleurs moments");
         
        TextArea hashtag = new TextArea();
        hashtag.setRows(2);
        hashtag.setHint("Ajouter un hashtag");
        
        Button photoButton = new Button("Attach Photo");
        photoButton.setTextPosition(Label.BOTTOM);
        
          CheckBox multiSelect = new CheckBox("Multi-select");
   System.out.println(photoButton.getBadgeText());
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
                 if(hashtag.getText().equals("") || description.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else { 
               
                    InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    //njibo iduser men session (current user)
                    Post r = new Post();
                    
                       java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
                       
                       r.setHashtagP(String.valueOf(hashtag.getText()));
                       r.setDescriptionP( String.valueOf(description.getText()));
                       r.setDateP(format.format(new Date()));
                       if(typeCombo.getSelectedIndex() == 0 ) {
                           r.setVisibilite("Public");
                     }
                    else if(typeCombo.getSelectedIndex() == 1) {
                       r.setVisibilite("Anonyme");
                     } 
                      if(photoButton.getBadgeText().equals("")){
                       r.setImageP("0");
                      }else{
                      
                                 r.setImageP(photoButton.getBadgeText());
                                   
                    
                      }
                    
                    System.out.println("data  post == "+r);
                    
                    
                    //appelle methode ajouterPost mt3 service Post bch nzido données ta3na fi base 
                    ServicePost.getInstance().ajoutPost(r);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListREclamationForm
                  new ListPostForm(theme,0).show();
            } }catch(Exception ex) {
                 showError(ex.getMessage());
              
            }
        });
        
      /*  Button cancelButton = new Button("Cancel");
        cancelButton.addActionListener((evt)->{
            new ListPostForm(0).show();
        });*/
        
        addComponent(typeCombo);
        addComponent(description);
        addComponent(hashtag);
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
 /*private void addImage(String imgPath) {
        ConnectionRequest connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Add Image", "Image added successfully", "OK", null);
            }
        };
        connectionRequest.setUrl("http://localhost/testUploader/insert.php?path=" + imgPath);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }*/
}
