/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servise;

import Entity.Post;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.io.Log.e;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.events.ActionEvent.Type.Exception;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oumayma
 */
public class ServicePost {
     //singleton 
    public static ServicePost instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServicePost getInstance() {
        if(instance == null )
            instance = new ServicePost();
        return instance ;
    }
    
    
    
    public ServicePost() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutPost(Post post) {
        
        String url =Statics.BASE_URL+"/post/addPostJSON?description="+post.getDescriptionP()+"&hashtag="+post.getHashtagP()+"&visibilite="+post.getVisibilite()+"&image="+post.getImageP();//+"&user="+post.getIduser(); // aa sorry n3adi getId lyheya mech ta3 user ta3 post
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Post>affichagePosts(int a) {
        
        ArrayList<Post> result = new ArrayList<>();
        String url;
        if (a==0){
          url = Statics.BASE_URL+"/post/displayPostsJSON";  
        }else{
           url = Statics.BASE_URL+"/post/displayPostshJSON?id="+a;
        }
        
        req.setUrl(url);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                Map<String,Object>mapPosts = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapPosts.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    
                    Post re = new Post();
                    
                    //dima id fi codename one float 5outhouha
                    float id = Float.parseFloat(obj.get("id").toString());
                    
                    String visibilite = obj.get("visibilite").toString();
                    
                    String description = obj.get("DescriptionP").toString();
                    
                     String hashtag = obj.get("HashtagP").toString();
        
                       String  image=obj.get("ImageP").toString();
                       System.out.println(image);
                   /* re.setId((int)id);
                    re.setDescriptionP(description);
                    re.setHashtagP(hashtag);
                    re.setVisibilite(visibilite);*/
                    
                    //Date
                    String DateConverter =  obj.get("DateP").toString().substring(obj.get("DateP").toString().indexOf("2") , obj.get("DateP").toString().lastIndexOf("T"));
                    
                    
                     //re.setDateP(DateConverter);
                   
                     if (image.equals("0")){
                        re =new Post((int)id,hashtag,description,visibilite,"0",DateConverter);
                     }else{
                        re = new Post((int)id,hashtag,description,visibilite,image,DateConverter); 
                     }
                     
                    
                    //insert data into ArrayList result
                    result.add(re);
                    
                    
                }
            
            }catch(Exception ex) {
                
                ex.printStackTrace();
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    //Detail Post bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Post DetailPost( int id , Post post ) {
        
        String url = Statics.BASE_URL+"/post/detailPostJSON?id="+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
          
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                    
                     post.setDescriptionP(obj.get("DescriptionP").toString());
                     post.setHashtagP(obj.get("HashtagP").toString());
                     post.setVisibilite(obj.get("visibilite").toString());
                     String image=obj.get("ImageP").toString();
                     if(image.equals("0")){
                       post.setImageP("0");
                     }else{
                        post.setImageP(image);
                     }
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return post;
        
        
    }
    
    
    //Delete 
    public boolean deletePost(float id ) {
        String url = Statics.BASE_URL +"/post/deletePostJSON?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierPost(Post post) {
        String url = Statics.BASE_URL +"/post/updatePostJSON?id="+post.getId()+"&description="+post.getDescriptionP()+"&hashtag="+post.getHashtagP()+"&visibilite="+post.getVisibilite();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
    //Likes
    public String Likes(Post post) {
        String url = Statics.BASE_URL +"/post/nblike?idpost="+post.getId();
        req.setUrl(url);
         Button b = new Button();
         String str  = new String(req.getResponseData());
         //post.setLikes(Integer.valueOf(str));
       
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha 
              return str;
    }
}
