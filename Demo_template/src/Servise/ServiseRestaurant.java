/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servise;
import Entity.Restaurant;
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
public class ServiseRestaurant {
    
    
           private static ServiseRestaurant instance = null;
    public ConnectionRequest req;
    public ArrayList<Restaurant> Restaurant;

    public boolean resultOK;
    
     public static ServiseRestaurant getInstance() {
                if(instance== null)
            instance = new ServiseRestaurant();
                return instance;
    }
    
    private ServiseRestaurant() {
        req = new ConnectionRequest();
    }
      
    
    public ArrayList<Restaurant> affichageRestaurant() 
     {
        ArrayList<Restaurant> result = new ArrayList<>();
        String  url = Statics.BASE_URL +"/restaurant/AllrestaurantJSON";
         req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapRestaurant = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapRestaurant.get("root");
                      
                    for (Map<String, Object> obj : listOfMaps) {
                        Restaurant r = new Restaurant();
                        int id = (int) Float.parseFloat(obj.get("id").toString());
                        int Num_Tel_Restaurant = (int) Float.parseFloat(obj.get("Num_Tel_Restaurant").toString());
                        String Nom_Restaurant = obj.get("Nom_Restaurant").toString();
                        String Adresse_Restaurant = obj.get("Adresse_Restaurant").toString();
                        String Description_Restaurant = obj.get("Description_Restaurant").toString();
                        String Image = obj.get("Image").toString();

                        
                        r.setId((int) id);
                        r.setNum_Tel_Restaurant((int) Num_Tel_Restaurant);
                        r.setAdresse_Restaurant(Adresse_Restaurant);
                        r.setDescription_Restaurant(Description_Restaurant);
                        r.setNom_Restaurant(Nom_Restaurant);
                        r.setImage(Image);

//                        String DateConverter=obj.get("date").toString().substring(obj.get("Date").toString().indexOf("timestamp")+10 , obj.get("Date").toString().lastIndexOf("}"));      
   //             Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
   //             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //            String dateString = formatter.format(currentTime);
    //            v.setDate(dateString);
                result.add(r);
                  
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
    public boolean AddRestaurant(Restaurant restaurant)
    {
        String url = Statics.BASE_URL+"/restaurant/AddrestaurantsJSON?Nom_Restaurant="+restaurant.getNom_Restaurant()+"&Adresse_Restaurant="+restaurant.getAdresse_Restaurant()+"&Num_Tel_Restaurant="+restaurant.getNum_Tel_Restaurant()+"&Description_Restaurant="+restaurant.getDescription_Restaurant()+"&Image="+restaurant.getImage();
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
    
     public boolean Updaterestaurants(Restaurant restaurant)
    {
        String url = Statics.BASE_URL+"/restaurant/UpdaterestaurantsJSON/"+restaurant.getId()+"?Nom_Restaurant="+restaurant.getNom_Restaurant()+"&Adresse_Restaurant="+restaurant.getAdresse_Restaurant()+"&Num_Tel_Restaurant="+restaurant.getNum_Tel_Restaurant()+"&Description_Restaurant="+restaurant.getDescription_Restaurant()+"&Image="+restaurant.getImage();
        req.setUrl(url);
    req.addResponseListener((e) -> {
                        resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
        String str = new String(req.getResponseData());
        //System.out.println("data"+str);
    });
        NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
    }
    
    public ArrayList<Restaurant> parseTasks(String jsonText){
        try {
            Restaurant =new ArrayList<>();  
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Restaurant r = new Restaurant();
                float id = Float.parseFloat(obj.get("id").toString());
              /*  v.setId((int)id);
                v.setDestination(obj.get("Dest").toString());
                v.setNom_Voyage(obj.get("NomVoy").toString());
                v.setDuree_Voyage(obj.get("Duree").toString());
                v.setPrix_Voyage((int) obj.get("Prix"));
                v.setValabilite(obj.get("Valabilite").toString());
                v.setImage(obj.get("image").toString());*/
               Restaurant.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Restaurant;
    }

    
    public boolean deletedRestaurant(int id) {

        String url = Statics.BASE_URL + "/restaurant/DeleterestaurantsJSON/" + id + "";
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
