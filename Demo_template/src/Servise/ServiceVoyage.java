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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ASUS
 */
public class ServiceVoyage {
    public ConnectionRequest req;
    public ArrayList<Voyage> voyage;
    private static ServiceVoyage instance;
    public boolean resultOK;
    private ServiceVoyage() {
        req = new ConnectionRequest();
    }
    public static ServiceVoyage getInstance() {
        if(instance== null)
            instance = new ServiceVoyage();
        return instance;
    }
    public boolean AddVoyage(Voyage voyage)
    {
       // String url = Statics.BASE_URL+"Create?Dest=DZ&NomVoy=Algerie&Duree=15%20jour&Prix=125&Valabilite=Non&image=ze";
               String url = Statics.BASE_URL + "create";
        
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("Dest", voyage.getDestination());
        req.addArgument("NomVoy", voyage.getNom_Voyage()+"");
        req.addArgument("Duree", voyage.getDuree_Voyage()+"");
        req.addArgument("Prix", voyage.getPrix_Voyage()+"");
        req.addArgument("Valabilite", voyage.getValabilite()+"");
        req.addArgument("image", voyage.getImage()+"");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
        resultOK = req.getResponseCode()==200;
        req.removeResponseListener(this);            }
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
                v.setPrix_Voyage(0);
                v.setValabilite(obj.get("Valabilite").toString());
                v.setImage(obj.get("image").toString());
               voyage.add(v);
            }
            
            
        } catch (IOException ex) {
            
        }
        return voyage;
    }
    
    public ArrayList<Voyage> getAllVoyage(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"voyage/AllVoyageJSON";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                voyage = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return voyage;
    }
}
