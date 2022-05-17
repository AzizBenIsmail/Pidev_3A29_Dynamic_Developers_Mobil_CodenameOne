/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Post;

import Entity.Comment;
import Entity.Post;
import Servise.ServiceComment;
import Utils.Statics;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
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
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.NORTH;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;


/**
 *
 * @author Oumayma
 */
public class CommentForm extends Form {
    Form f ;
                    private Resources theme;

    public CommentForm(Resources res,Post item) {
       f=this; 
      
     //  Toolbar tb = new Toolbar(true);
      //  setToolbar(tb);
        setTitle("Publication de fleen");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setLayout(new BorderLayout()); 
        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListPostForm(theme,0) );
        /* tb.setBackCommand(new Command("Back") {
            public void actionPerformed(ActionEvent evt) {
                new ListPostForm(0).show();
            }
        });*/
        
        Container list = new Container();
        list.setScrollableY(true);
        list.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Dialog progress = new InfiniteProgress().showInifiniteBlocking();
        
           // Post item = ServicePost.getInstance().DetailPost(a,p);
        
      
            Container itemWrapper = new Container();
            itemWrapper.setUIID("FeedItem");
   
            itemWrapper.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            Container topRow = new Container();
            topRow.setUIID("FeedItemTopRow");
            topRow.setLayout(new BoxLayout(BoxLayout.X_AXIS));
            //String avatarUrl =  "Voyageur";//(String)item.get("avatar");
            String nom = "";
            URLImage img = null;
            if (item.getVisibilite().equals("Public")){
             nom="Voyageur";
             img = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true), Statics.BASE_URL+"/uploads/Not found.jpg", Statics.BASE_URL+"/uploads/Not found.jpg", URLImage.RESIZE_SCALE_TO_FILL);
      
            }else{
                nom="Anonyme";
                img = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true), Statics.BASE_URL+"/uploads/anonymous-user.png", Statics.BASE_URL+"/uploads/anonymous-user.png", URLImage.RESIZE_SCALE_TO_FILL);
            }
             
            topRow.addComponent(new Label(img));
            
            Container postDetails = new Container();
            postDetails.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            postDetails.addComponent(new Label(nom/*(String)item.get("screen_name")*/));
            Label posted = new Label("Posted "+item.getDateP());
            posted.setUIID("FeedDateLabel");
            postDetails.addComponent(posted);
            topRow.addComponent(postDetails);
            itemWrapper.addComponent(topRow);
            
            itemWrapper.addComponent(new SpanLabel((String)item.getDescriptionP()));
            
            Button b = new Button((String)item.getHashtagP()); 
            b.getAllStyles().setBorder(Border.createEmpty());
            b.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            itemWrapper.addComponent( b);
            
            b.addActionListener((e)->{
              new ListPostForm(res,item.getId()).show();
            });
               
                URLImage photo;
                if(!(item.getImageP().equals("null")))
                  try{
                      photo = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(fullWidthImage, (int)fullWidthImage * 9 / 16),true), FileSystemStorage.getInstance().getAppHomePath()+item.getImageP(),
                     FileSystemStorage.getInstance().getAppHomePath()+item.getImageP() , URLImage.RESIZE_SCALE_TO_FILL);
                      itemWrapper.addComponent(new Label(photo));
                  }catch(Exception err){
                       System.out.print("hello");
                        photo = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(fullWidthImage, (int)fullWidthImage * 9 / 16),true), Statics.BASE_URL+"uploads/"+item.getImageP(),
                        Statics.BASE_URL+"uploads/"+item.getImageP(), URLImage.RESIZE_SCALE_TO_FILL);
                      itemWrapper.addComponent(new Label(photo));
                  }
                
                TextArea comment = new TextArea();
                comment.setRows(5);
                comment.setHint("Partagez avec nous vos meilleurs moments");
                Button ok = new Button();
           
              ok.setUIID("NewsTopLine");
              Style commentStyle = new Style(ok.getUnselectedStyle());
              //commentStyle.setFgColor(0xf7ad02);
               FontImage commentImage = FontImage.createMaterial(FontImage.MATERIAL_SEND, commentStyle);
               ok.setIcon(commentImage);
                Container c = BoxLayout.encloseX(comment,ok);
                ok.addActionListener((e)->{
                    try{
                        if(comment.getText().equals("")) {
                           Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                       }else{
                            InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                            final Dialog iDialog = ip.showInfiniteBlocking();
                            Comment c1 = new Comment();
                            c1.setObjet("0");
                            c1.setCommentaire(comment.getText().toString());
                            c1.setIdpost(item.getId());
                             ServiceComment.getInstance().ajoutComment(c1);
                             iDialog.dispose();
                             new CommentForm(res,item);
                        }  
                    }catch(Exception ex){
                        showError(ex.getMessage());
                    }
                });
                //itemWrapper.addComponent(comment);
              
              list.addComponent(itemWrapper);
              ArrayList<Comment> feed = ServiceComment.getInstance().affichageCommentspost(item.getId()) ;
                for (Comment commentaire : feed){
                 Container com = new Container();
                 com.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                 System.out.println(commentaire.getId());
                 Container top = new Container();
                 top.setUIID("FeedItemTopRow");
                top.setLayout(new BoxLayout(BoxLayout.X_AXIS));
          
                 String n = "";
                 URLImage i = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true), Statics.BASE_URL+"/uploads/Not found.jpg", Statics.BASE_URL+"/uploads/Not found.jpg", URLImage.RESIZE_SCALE_TO_FILL);  
             
             top.addComponent(new Label(i));
            
            Container Details = new Container();
            Details.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            Details.addComponent(new Label("Voyageur"));
            SpanLabel desc= new SpanLabel((String)commentaire.getCommentaire());
            posted.setUIID("FeedDateLabel");
            Details.addComponent(desc);
            top.addComponent(Details);
            com.addComponent(top);
            
           // itemWrapper.addComponent(new Label("Posted "+item.getDateP()));
           
                 list.addComponent(com);
           
                }
            list.addComponent(c);
        
        progress.dispose();
       
        f.addComponent(BorderLayout.CENTER,list);
        f.show();
      
    }
        public void showError(String msg) {
        Dialog.show("Failed", msg, "OK", null);
    }
        //Image fullWidthPlaceHolder;
        int fullWidthImage = (int)Math.round(Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight())) - 20;
        Image fullWidthPlaceHolder = Image.createImage(fullWidthImage, (int)fullWidthImage * 9 / 16);
        
    }
      
