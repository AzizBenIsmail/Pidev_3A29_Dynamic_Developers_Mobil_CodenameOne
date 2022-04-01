/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servise;

import Entity.Excursion;
import Entity.ReserverVoyage;
import Entity.Voyage;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiseResrverVoyage {
    
       private static ServiseResrverVoyage instance = null;
    public ConnectionRequest req;
    public ArrayList<Voyage> voyage;

    public boolean resultOK;

    public static ServiseResrverVoyage getInstance() {
        if(instance== null)
            instance = new ServiseResrverVoyage();
        return instance;
    }
    
        private ServiseResrverVoyage() {
        req = new ConnectionRequest();
    }
        
     public ArrayList<ReserverVoyage> affichageReserverVoyage() 
     {
        ArrayList<ReserverVoyage> result = new ArrayList<>();
        String  url = Statics.BASE_URL +"/reservation/voyage/AllResrVoyageJSON";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapReserverVoyage = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReserverVoyage.get("root");
                      
                    for (Map<String, Object> obj : listOfMaps) {
                        ReserverVoyage s = new ReserverVoyage();
                        
                        Map<String,Object> v = (Map<String,Object>)obj.get("Voyage");
                        Voyage voy = new Voyage((int) Float.parseFloat(v.get("id").toString()), (int) Float.parseFloat(v.get("Prix").toString()), v.get("Destination").toString(), v.get("Nom_Voyage").toString(), v.get("Duree_Voyage").toString(), v.get("valabilite").toString(), v.get("Image").toString());
                        
                        Map<String,Object> c = (Map<String,Object>)obj.get("Client");
                        
                        
                        System.out.println(voy);
                        


                        //System.out.println(v);
                        int id = (int) Float.parseFloat(obj.get("id").toString());
                        int Age = (int) Float.parseFloat(obj.get("Age").toString());
                        String Travel_Class = obj.get("Travel_Class").toString();
                        s.setId((int) id);                        
                        s.setAge((int) Age);
                        s.setTravel_Class(Travel_Class);
                        s.setVoyage(voy);
                        s.setClient((int) Float.parseFloat(c.get("id").toString()));
//                        String DateConverter=obj.get("date").toString().substring(obj.get("Date").toString().indexOf("timestamp")+10 , obj.get("Date").toString().lastIndexOf("}"));      
   //             Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
   //             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //            String dateString = formatter.format(currentTime);
    //            v.setDate(dateString);
                result.add(s);
                  
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
     
     public boolean AddReservationVoyage(ReserverVoyage ReserverVoyage)
    {
        String url = Statics.BASE_URL+"/reservation/voyage/AddReserVoyageJSON?Client="+ReserverVoyage.getClient()+"&Voyage="+ReserverVoyage.getVoyage().getId()+"&TravelClass="+ReserverVoyage.getTravel_Class()+"&Age="+ReserverVoyage.getAge();
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
     
     public boolean deletedReservationVoyage(int id) {

        String url = Statics.BASE_URL + "/reservation/voyage/deletedReservationVoyage/" + id + "";
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
}
