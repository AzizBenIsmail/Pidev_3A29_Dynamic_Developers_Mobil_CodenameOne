/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Gui.Excursion.Excursion;
import Gui.MapForm;
import Gui.ReserverVoyage.ShowReservationVoyage;
import Gui.Restaurant.Restaurant;
import Gui.Restaurant.ShowRestaurantCrud;
import Gui.Voyage.VoyageHome;
import Post.ListPostForm;
import Reclamation.ListReclamationForm;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Commentaire;
import com.mycompany.myapp.ProfileForm;
import com.mycompany.myapp.Reclamation;

/**
 *
 * @author ASUS
 */
public class HomeVoyage extends Form{
Form current;
private Resources theme;
    public HomeVoyage(Resources res) {
                current=this; //Back 
                
        add(new Label("Welcom Admin to Travel_Me"));
        setTitle("Welcom Admin to Travel_Me");
        setLayout(BoxLayout.y());
    Button BUTProfil= new Button("Profil");
    Button BUTReclmataion = new Button("Gestion Reclamation");
    Button BUTExcursion = new Button("Gestion Excursion");
    Button BUTRestaurant= new Button("Gestion Restaurant");
    Button BUTVoyage= new Button("Gestion Voyage");
    Button BUTReservationVoyage= new Button("Gestion ReservationVoyage");
    Button BUTReservationExcursion= new Button("Gestion ReservationExcursion");
    Button BUTReservationRestaurant= new Button("Gestion ReservationRestaurant");
    Button Post= new Button("Gestion des Post");

Post.addActionListener((evt) -> new ListPostForm(res,0).show());
BUTVoyage.addActionListener((evt) -> new VoyageHome(res,current).show());
BUTProfil.addActionListener((evt) -> new ProfileForm(res,current).show());
BUTRestaurant.addActionListener((evt) -> new ShowRestaurantCrud(current).show());
BUTExcursion.addActionListener((evt) -> new Excursion(res,current).show());
BUTReclmataion.addActionListener((evt) -> new ListReclamationForm(res,current).show());
BUTReservationVoyage.addActionListener((evt) -> new ShowReservationVoyage(res,current).show());



        addAll(BUTProfil,BUTExcursion,BUTRestaurant,BUTReclmataion,BUTVoyage,BUTReservationVoyage,BUTReservationRestaurant,BUTReservationExcursion,Post);
    
        
    }
     
}
