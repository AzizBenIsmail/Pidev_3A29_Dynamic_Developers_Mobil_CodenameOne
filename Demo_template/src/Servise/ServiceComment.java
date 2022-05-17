/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servise;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entity.Comment;
import Utils.Statics;
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
public class ServiceComment {
    public static ServiceComment instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceComment getInstance() {
        if(instance == null )
            instance = new ServiceComment();
        return instance ;
    }
    
    
    
    public ServiceComment() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutComment(Comment comment) {
        
        String url =Statics.BASE_URL+"/commentaire/addCommentaireJSON?description="+comment.getCommentaire()+"&objet="+comment.getObjet()+"&postid="+comment.getIdpost();//+"&user="+comment.getIduser(); // aa sorry n3adi getId lyheya mech ta3 user ta3 comment
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Comment>affichageComments() {
        
        ArrayList<Comment> result = new ArrayList<>();
        String url;
    
          url = Statics.BASE_URL+"/commentaire/displayCommentairesJSON";  
        
        
        req.setUrl(url);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                Map<String,Object>mapComments = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapComments.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    
                    Comment re = new Comment();
                    
                    //dima id fi codename one float 5outhouha
                    float id = Float.parseFloat(obj.get("id").toString());
                
                    String description = obj.get("Commentaire").toString();
                    
                     String objet = obj.get("Objet").toString();
                     
                     re.setCommentaire(description);
                     re.setId((int)id);
                     re.setObjet(objet);
                   /* re.setId((int)id);
                    re.setDescriptionP(description);
                    re.setHashtagP(hashtag);
                    re.setVisibilite(visibilite);*/
                    
                    //Date
           
                   // re.setDateP(dateString);
             
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
     
     //affichage commentaires d'une post
    
    public ArrayList<Comment>affichageCommentspost(int idp) {
        
        ArrayList<Comment> result = new ArrayList<>();
        String url;
    
          url = Statics.BASE_URL+"/commentaire/displayCommentairesPJSON?idp="+idp;  
        
        
        req.setUrl(url);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                Map<String,Object>mapComments = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapComments.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    
                    Comment re = new Comment();
                    
                    //dima id fi codename one float 5outhouha
                    float id = Float.parseFloat(obj.get("id").toString());
                
                    String description = obj.get("Commentaire").toString();
                    
                     String objet = obj.get("Objet").toString();
                     
                     re.setCommentaire(description);
                     re.setId((int)id);
                     re.setObjet(objet);
                   /* re.setId((int)id);
                    re.setDescriptionP(description);
                    re.setHashtagP(hashtag);
                    re.setVisibilite(visibilite);*/
                    
                    //Date
           
                   // re.setDateP(dateString);
             
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
    
    
    //Detail Comment bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Comment DetailComment( int id , Comment comment ) {
        
        String url = Statics.BASE_URL+"/commentaire/detailCommentaireJSON?id="+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
          
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                    
                     comment.setCommentaire(obj.get("DescriptionP").toString());
                     comment.setObjet(obj.get("Objet").toString());
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return comment;
        
        
    }
    
    
    //Delete 
    public boolean deleteComment(float id ) {
        String url = Statics.BASE_URL +"/commentaire/deleteCommentaireJSON?id="+id;
        
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
    public boolean modifierComment(Comment comment) {
        String url = Statics.BASE_URL +"/commentaire/updateCommentaireJSON?id="+comment.getId()+"&description="+comment.getCommentaire()+"&objet="+comment.getObjet();
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
}
