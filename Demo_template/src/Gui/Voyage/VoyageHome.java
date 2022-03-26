/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Voyage;


import Gui.premium.statForm;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.HomeVoyage;
import com.mycompany.myapp.ProfileForm;


/**
 *
 * @author ASUS
 */
public class VoyageHome extends Form{
    Form current;

    public VoyageHome(Resources res, Form previous) {
                current=this; //Back 
                
        add(new Label("Welcom to Travel_Me"));
        setTitle(" --Voyage-- ");
        setLayout(BoxLayout.y());
        
    Button BUTAdd = new Button("Add Voyage");
    Button BUTMod = new Button("Modifier Voyage");
    Button BUTSup = new Button("Supprimer Voyage");
    Button BUTShow = new Button("Show Voyage");
        Button BUTStat = new Button("Show Stat");
    BUTAdd.addActionListener((evt) -> new statForm(current).show());
        BUTAdd.addActionListener((evt) -> new AddVoyage(current).show());
    BUTSup.addActionListener((evt) -> new SupprimerVoyage(current).show());
   // BUTMod.addActionListener((evt) -> new ModifierVoyage(current).show());
    BUTShow.addActionListener((evt) -> new ShowVoyage(current,0).show());
        addAll(BUTShow,BUTAdd,BUTMod,BUTSup,BUTStat);
    
    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        new ProfileForm(res,this).show();
        });}
     
}
