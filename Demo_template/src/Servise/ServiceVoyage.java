/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servise;

import Entity.Voyage;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.properties.UiBinding.DateConverter;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ASUS
 */
public class ServiceVoyage {
    
        private static ServiceVoyage instance = null;
    public ConnectionRequest req;
    public ArrayList<Voyage> voyage;

    public boolean resultOK;

    public static ServiceVoyage getInstance() {
        if(instance== null)
            instance = new ServiceVoyage();
        return instance;
    }
    
    private ServiceVoyage() {
        req = new ConnectionRequest();
    }
        
    public boolean AddVoyage(Voyage voyage)
    {
        String url = Statics.BASE_URL+"/voyage/AddVoyageJSON?Destination="+voyage.getDestination()+"&NomVoyage="+voyage.getNom_Voyage()+"&DureeVoyage="+voyage.getDuree_Voyage()+"&PrixVoyage="+voyage.getPrix_Voyage()+"&Valabilite="+voyage.getValabilite()+"&Image="+voyage.getImage();
             //  String url = Statics.BASE_URL + "create";
        req.setUrl(url);
    req.addResponseListener((e) -> {
                        resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
        String str = new String(req.getResponseData());
        System.out.println("data"+str);
    });
        NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
    }
    
     public boolean UpdateVoyage(Voyage voyage,int id)
    {
        String url = Statics.BASE_URL+"/voyage/UpdateVoyageJSON/"+id+"?Destination="+voyage.getDestination()+"&NomVoyage="+voyage.getNom_Voyage()+"&DureeVoyage="+voyage.getDuree_Voyage()+"&PrixVoyage="+voyage.getPrix_Voyage()+"&Valabilite="+voyage.getValabilite()+"&Image="+voyage.getImage();
             //  String url = Statics.BASE_URL + "create";
        req.setUrl(url);
    req.addResponseListener((e) -> {
                        resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
        String str = new String(req.getResponseData());
        System.out.println("data"+str);
    });
        NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
    }
    
    public ArrayList<Voyage> parseTasks(String jsonText){
        try {
            voyage =new ArrayList<>();  
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Voyage v = new Voyage();
                float id = Float.parseFloat(obj.get("id").toString());
                v.setId((int)id);
                v.setDestination(obj.get("Dest").toString());
                v.setNom_Voyage(obj.get("NomVoy").toString());
                v.setDuree_Voyage(obj.get("Duree").toString());
                v.setPrix_Voyage((int) obj.get("Prix"));
                v.setValabilite(obj.get("Valabilite").toString());
                v.setImage(obj.get("image").toString());
               voyage.add(v);
            }
            
            
        } catch (IOException ex) {
            
        }
        return voyage;
    }

    
    public boolean deletedVoyage(int id) {

        String url = Statics.BASE_URL + "/voyage/DeleteVoyageJSON/" + id + "";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public ArrayList<Voyage> affichageVoyage() 
     {
        ArrayList<Voyage> result = new ArrayList<>();
        String  url = Statics.BASE_URL +"/voyage/AllVoyageJSON";
         req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapVoyage = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapVoyage.get("root");
                      
                    for (Map<String, Object> obj : listOfMaps) {
                        Voyage v = new Voyage();
                        int id = (int) Float.parseFloat(obj.get("id").toString());
                        String Destination = obj.get("Destination").toString();
                        String Nom_Voyage = obj.get("Nom_Voyage").toString();
                        String Duree_Voyage = obj.get("Duree_Voyage").toString();
                        int Prix_Voyage=(int)Float.parseFloat(obj.get("Prix_Voyage").toString());                          
                          String valabilite = obj.get("valabilite").toString();
                          String Image = obj.get("Image").toString();

                        
                        v.setId((int) id);
                        v.setDestination(Destination);
                        v.setNom_Voyage(Nom_Voyage);
                        v.setPrix_Voyage((int) Prix_Voyage);
                        v.setDuree_Voyage(Duree_Voyage);
                        v.setImage(Image);
                        v.setValabilite(valabilite);

//                        String DateConverter=obj.get("date").toString().substring(obj.get("Date").toString().indexOf("timestamp")+10 , obj.get("Date").toString().lastIndexOf("}"));      
   //             Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
   //             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //            String dateString = formatter.format(currentTime);
    //            v.setDate(dateString);
                result.add(v);
                  
                    }
                } 

       catch(Exception e ){
                       e.printStackTrace();
                   }
            }           
                });
        
         NetworkManager.getInstance().addToQueueAndWait(req);
                             
           return result;
    }
}
