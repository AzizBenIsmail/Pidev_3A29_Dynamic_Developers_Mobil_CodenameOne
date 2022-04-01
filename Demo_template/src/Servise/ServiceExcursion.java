/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servise;

import Entity.Excursion;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
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
public class ServiceExcursion {
    //singleton 
    public static ServiceExcursion instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceExcursion getInstance() {
        if(instance == null )
            instance = new ServiceExcursion();
        return instance ;
    }
    
    
    
    public ServiceExcursion() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutExcursion(Excursion excursion) {
        
        String url =Statics.BASE_URL+"/excursion/addExcursionJSON?nom="+excursion.getNom()+"&description="+excursion.getDescription()+"&type="+excursion.getType()
                +"&lieu="+excursion.getLieu()+"&date="+excursion.getDate()+"&valabilite="+excursion.getValabilite()+"&image="+excursion.getImage()+"&prix="+excursion.getPrix();//+"&user="+excursion.getIduser(); // aa sorry n3adi getId lyheya mech ta3 user ta3 excursion
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Excursion>affichageExcursions() {
        
        ArrayList<Excursion> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/excursion/displayExcursionsJSON";
        req.setUrl(url);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                Map<String,Object>mapExcursions = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapExcursions.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    
                    Excursion re = new Excursion();
                    
                    //dima id fi codename one float 5outhouha
                    float id = Float.parseFloat(obj.get("id").toString());
                    
                    String nom = obj.get("Nom_Excursion").toString();
                    
                    String description = obj.get("Description_Excursion").toString();
                    
                    String type = obj.get("Type_Excursion").toString();
                    
                    String lieu = obj.get("Lieu").toString();
                    
                    String image = obj.get("Image").toString();
                    
                    String valabilite = obj.get("valabilite").toString();
                    
                    float prix = Float.parseFloat(obj.get("prix").toString());
                    
                    re.setId((int)id);
                    re.setNom(nom);
                    re.setDescription(description);
                    re.setType(type);
                    re.setLieu(lieu);
                    re.setImage(image);
                    re.setImage(image);
                    re.setValabilite(valabilite);
                    re.setPrix(prix);
                    
                    //Date
                    String DateConverter =  obj.get("Date").toString().substring(obj.get("Date").toString().indexOf("2")+9, obj.get("Date").toString().lastIndexOf("T"));
                    
                    Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                    
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = formatter.format(currentTime);
                    re.setDate(dateString);
                    
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
    
    
    
    //Detail Excursion bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Excursion DetailExcursion( int id ) {
         Excursion re = new Excursion();
        String url = Statics.BASE_URL+"/excursion/detailExcursionJSON?id="+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                     String DateConverter =  obj.get("Date").toString().substring(obj.get("Date").toString().indexOf("2"), obj.get("Date").toString().lastIndexOf("T"));
                    
               
                    
                    re.setId((int)id);
                    re.setNom(obj.get("Nom_Excursion").toString());
                    re.setDescription(obj.get("Description_Excursion").toString());
                    re.setType(obj.get("Type_Excursion").toString());
                    re.setLieu(obj.get("Lieu").toString());
                    re.setImage(obj.get("Image").toString());
                    re.setValabilite(obj.get("valabilite").toString());
                    re.setPrix(Float.parseFloat(obj.get("prix").toString()));
                    re.setDate(DateConverter);
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return re;
        
        
    }
    
    
    //Delete 
    public boolean deleteExcursion(float id ) {
        String url = Statics.BASE_URL +"/excursion/deleteExcursionJSON?id="+id;
        
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
    public boolean modifierExcursion(Excursion excursion) {
        String url = Statics.BASE_URL +"/excursion/updateExcursionJSON?id="+excursion.getId()+"&nom="+excursion.getNom()+"&description="+excursion.getDescription()+"&type="+excursion.getType()
                +"&lieu="+excursion.getLieu()+"&date="+excursion.getDate()+"&valabilite="+excursion.getValabilite()+"&image="+excursion.getImage()+"&prix="+excursion.getPrix();
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
