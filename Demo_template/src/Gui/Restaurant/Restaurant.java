/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Restaurant;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.ProfileForm;

/**
 *
 * @author ASUS
 */
public class Restaurant extends Form{
Form current;
    public Restaurant(Resources res, Form previous) {
         current=this; //Back 
        add(new Label("Restaurant"));
        setTitle(" --Restaurant-- ");
        setLayout(BoxLayout.y());
        
    Button BUTAdd = new Button("Add Restaurant");
    Button BUTShow = new Button("Show Restaurant");
    //Button BUTCam = new Button("Camera ");
    BUTShow.addActionListener((evt) -> new ShowRestaurantCrud(current).show());
    BUTAdd.addActionListener((evt) -> new Ajouterrestaurant(current).show());
        addAll(BUTAdd,BUTShow);
    
    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
  new ProfileForm(res,this).show();        });}

    }
   
