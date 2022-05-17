/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servise;

import Entity.Excursion1;
import Entity.Restaurant;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiseExcusion {
          private static ServiseExcusion instance = null;
    public ConnectionRequest req;
    public ArrayList<Excursion1> Excursion1;

    public boolean resultOK;
    
     public static ServiseExcusion getInstance() {
                if(instance== null)
            instance = new ServiseExcusion();
                return instance;
    }
    
    private ServiseExcusion() {
        req = new ConnectionRequest();
    }
      
    
    public ArrayList<Excursion1> affichageExcursion() 
     {
        ArrayList<Excursion1> result = new ArrayList<>();
        String  url = Statics.BASE_URL +"/excursion/AllExcursionJSON";
         req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapExcursion = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapExcursion.get("root");
                      
                    for (Map<String, Object> obj : listOfMaps) {
                     Excursion1 s=new Excursion1();
                        int id = (int) Float.parseFloat(obj.get("id").toString());
                        int Prix = (int) Float.parseFloat(obj.get("prix").toString());
                        String Nom_Excursion = obj.get("Nom_Excursion").toString();
                        String Description_Excursion = obj.get("Description_Excursion").toString();
                        String Type_Excursion = obj.get("Type_Excursion").toString();
                        String Lieu = obj.get("Lieu").toString();
                        String valabilite = obj.get("valabilite").toString();
                        String Image = obj.get("Image").toString();

                        
                        s.setId((int) id);
                        s.setPrix((int) Prix);
                        s.setNom_Excursion(Nom_Excursion);
                        s.setType_Excursion(Type_Excursion);
                        s.setDescription_Excursion(Description_Excursion);
                        s.setLieu(Lieu);
                        s.setValabilite(valabilite);
                        s.setImage(Image);

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
}
