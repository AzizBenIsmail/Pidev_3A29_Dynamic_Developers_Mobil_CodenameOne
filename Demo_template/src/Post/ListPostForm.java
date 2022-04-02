/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Post;

import Entity.Post;
import Servise.ServiceComment;
import Servise.ServicePost;
import Utils.Statics;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.L10NManager;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.ProfileForm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;


/**
 *
 * @author Oumayma
 */
public class ListPostForm extends Form {
     
     Form f;
     public ListPostForm(Resources res,int a) {
         f=this;
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ProfileForm(res,f).show();
            });
        setTitle("NewsFeed");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        //f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> );
        Container buttons = new Container();
        buttons.setLayout(new GridLayout(1, 2));
        Button addPost = new Button("Add Post");
        addPost.addActionListener((e)->{
           new AjoutPostForm(f).show();
        });
        
         buttons.addComponent(addPost);
        
        
        Container list = new Container();
        list.setScrollableY(true);
        list.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Dialog progress = new InfiniteProgress().showInifiniteBlocking();
           ArrayList<Post> feed;
        if (a==0){
            feed = ServicePost.getInstance().affichagePosts(0); 
        }else{
            feed = ServicePost.getInstance().affichagePosts(a);
        }
        
        for( Post item : feed) {
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
             itemWrapper.addComponent(b);
            
            b.addActionListener((e)->{
              new ListPostForm(res,item.getId()).show();
            });
        
   
               if(!(item.getImageP().equals("0"))){
                 EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(fullWidthImage, (int)fullWidthImage * 9 / 16), true);
              Image i = URLImage.createToStorage(placeholder,item.getImageP(),Statics.BASE_URL+"/uploads/"+item.getImageP()).fill(500,400);
                itemWrapper.addComponent(new Label(i));}
                  Container action = new Container();
                  action.setUIID("action");
                  action.setLayout(new BoxLayout(BoxLayout.X_AXIS));
                  
                   Container c = new Container();
                   c.setUIID("Comment");
                   c.setLayout(new BoxLayout(BoxLayout.X_AXIS));
                   Button comment=new Button();
                   Style commentStyle = new Style(comment.getUnselectedStyle());
                   FontImage commentImage = FontImage.createMaterial(FontImage.MATERIAL_CHAT_BUBBLE_OUTLINE, commentStyle);
                   comment.setIcon(commentImage); 
                     comment.addActionListener((l)-> {
                         new CommentForm(res,item).show();
                     });
                     
                     String C = String.valueOf(ServiceComment.getInstance().affichageCommentspost(item.getId()).size());
                     c.addComponent(comment);
                     c.addComponent(new Label(C));
                     
  
                     Button like=new Button();
                     Style likeStyle = new Style(like.getUnselectedStyle());
                     FontImage likeImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE_BORDER, likeStyle);
                     like.setIcon(likeImage);
                  
                     like.addActionListener((l)-> {
                         //new CommentForm(item).show();
                     });
                     String l;
                     if (ServicePost.getInstance().Likes(item).equals("0")){
                         l="0";
                     }else{
                       l=ServicePost.getInstance().Likes(item);  
                     }
                     
                     action.addComponent(like);
                     action.addComponent(new Label(l));
                     action.addComponent(c);
                     
                    itemWrapper.addComponent(action);
                 
           
            
            list.addComponent(itemWrapper);
        }
        progress.dispose();
        
        f.addComponent(BorderLayout.NORTH, buttons);
        f.addComponent(BorderLayout.CENTER, list);
        f.show();
      
    }
        public void showError(String msg) {
        Dialog.show("Failed", msg, "OK", null);
    }
        //Image fullWidthPlaceHolder;
        int fullWidthImage = (int)Math.round(Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight())) - 20;
        Image fullWidthPlaceHolder = Image.createImage(fullWidthImage, (int)fullWidthImage * 9 / 16);
        //if (!(fullWidthPlaceHolder instanceof EncodedImage)) {
           // fullWidthPlaceHolder = EncodedImage.createFromImage(fullWidthPlaceHolder, true);
        //}
     
}
