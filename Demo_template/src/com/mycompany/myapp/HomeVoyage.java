/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ASUS
 */
public class HomeVoyage extends Form{
Form current;
    public HomeVoyage(Resources res) {
                current=this; //Back 
                
        add(new Label("Welcom to Travel_Me"));
        setTitle("Voyage ");
        setLayout(BoxLayout.y());
        
    Button BUTVoyage = new Button("Voyage");
    Button BUTReclmataion = new Button("Reclamation");
    Button BUTExcursion = new Button("Excursion");
    Button BUTRestaurant= new Button("Restaurant");
    Button BUTCommentaire= new Button("Commentaire");
    Button BUTProfil= new Button("Profil");
BUTVoyage.addActionListener((evt) -> new Voyage(current).show());
BUTProfil.addActionListener((evt) -> new ProfileForm(res).show());
        addAll(BUTVoyage,BUTReclmataion,BUTExcursion,BUTRestaurant,BUTCommentaire,BUTProfil);
    
        
    }
     
}
