/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servise;

import Entity.ReservationExcursion;
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
public class ServiceReservationExcursion {
    public static ServiceReservationExcursion instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReservationExcursion getInstance() {
        if(instance == null )
            instance = new ServiceReservationExcursion();
        return instance ;
    }
    
    
    
    public ServiceReservationExcursion() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutReservationExcursion(ReservationExcursion Reservation) {
        
        String url =Statics.BASE_URL+"/reservation/excursion/addReservationExcursionJSON?email="+Reservation.getEmail()+"&nb="+Reservation.getNb()+"&idex="+Reservation.getIdex();
                 // aa sorry n3adi getId lyheya mech ta3 user ta3 excursion
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<ReservationExcursion>affichageReservationExcursions() {
        
        ArrayList<ReservationExcursion> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/reservation/excursion/displayReservationExcursionsJSON";
        req.setUrl(url);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                Map<String,Object>mapReservationExcursions = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReservationExcursions.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    
                    ReservationExcursion re = new ReservationExcursion();
                    
                    //dima id fi codename one float 5outhouha
                    float id = Float.parseFloat(obj.get("id").toString());
                    
                    String email = obj.get("Email").toString();
                    String idex = obj.get("ID_Excursion").toString().substring(obj.get("ID_Excursion").toString().indexOf("=")+1,obj.get("ID_Excursion").toString().lastIndexOf("."));
                    System.out.println(idex);
                    int nb = (int)Float.parseFloat(obj.get("nb").toString());
                    re.setIdex(Integer.valueOf(idex));
                    re.setEmail(email);
                    re.setId((int)id);
                    re.setNb(nb);
                     //Date
                    String DateConverter =  obj.get("Date_Reservation_Excursion").toString().substring(obj.get("Date_Reservation_Excursion").toString().indexOf("2"), obj.get("Date_Reservation_Excursion").toString().lastIndexOf("T"));
                    //System.out.println(DateConverter);
                  //  Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                    
                  //  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                  //  String dateString = formatter.format(currentTime);
                    re.setDateR(DateConverter);
                    
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
    
    
    //Detail ReservationExcursion bensba l detail n5alihoa lel5r ba3d delete+update
    
    public ReservationExcursion DetailRecalamation( int id , ReservationExcursion re) {
        
        String url = Statics.BASE_URL+"/excursion/detailReservationExcursionJSON?id="+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                    re.setId((int)id);
                     re.setEmail(obj.get("Email").toString());
                    re.setNb(Integer.valueOf(obj.get("nb").toString()));
                    re.setDateR(obj.get("Date_Reservation_Excursion").toString());
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return re;
        
        
    }
    
    
    //Delete 
    public boolean deleteReservationExcursion(String id ) {
        
        String url = Statics.BASE_URL +"/reservation/excursion/deleteReservationExcursionJSON?id="+id;
        
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
    public boolean modifierReservationExcursion(ReservationExcursion Reservation) {
        String url = Statics.BASE_URL +"/reservation/excursion/updateReservationExcursionJSON?id="+Reservation.getId()+"&email="+Reservation.getEmail()+"&nb="+Reservation.getNb();
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
