/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servise;

import Utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author ASUS
 */
public class ServicePremuim {
        private static ServicePremuim instance = null;
    public ConnectionRequest req;

    public boolean resultOK;

    public static ServicePremuim getInstance() {
        if(instance== null)
            instance = new ServicePremuim();
        return instance;
    }
    
    private ServicePremuim() {
        req = new ConnectionRequest();
    }
    
    public boolean DemandePremuim(int id) {

        String url = Statics.BASE_URL + "/reservation/voyage/Devenirpemium?CIN=" + id + "";
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
