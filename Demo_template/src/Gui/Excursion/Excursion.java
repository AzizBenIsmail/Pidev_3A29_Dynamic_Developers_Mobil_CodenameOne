/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Excursion;

import Gui.Restaurant.ShowRestaurant;
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
public class Excursion extends Form{
    Form current;
    public Excursion(Resources res, Form previous) {
         current=this; //Back 
        add(new Label("Excursion"));
        setTitle(" --Excursion-- ");
        setLayout(BoxLayout.y());
        
    Button BUTAdd = new Button("Add Excursion");
    Button BUTShow = new Button("Show Excursion");
    //Button BUTCam = new Button("Camera ");
    BUTShow.addActionListener((evt) -> new ShowExcursion(current).show());
    //BUTCam.addActionListener((evt) -> new Camera().show());
  //  BUTShow.addActionListener((evt) -> new ShowVoyage(current).show());
        addAll(BUTAdd,BUTShow);
    
    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
  new ProfileForm(res,this).show();
    });}
}
