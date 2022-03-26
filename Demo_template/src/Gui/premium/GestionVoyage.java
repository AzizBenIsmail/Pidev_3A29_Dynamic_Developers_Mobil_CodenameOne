package Gui.premium;

import Gui.Voyage.AddVoyage;
import Gui.Voyage.ModifierVoyage;
import Gui.Voyage.ShowVoyage;
import Gui.Voyage.SupprimerVoyage;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.ProfileForm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class GestionVoyage extends Form {
Form current;
    public GestionVoyage(Form previous) {
     current =this;
                
        add(new Label("Welcom to Travel_Me"));
        setTitle(" --Voyage-- ");
        setLayout(BoxLayout.y());
        
    Button BUTAdd = new Button("Add Voyage");
    Button BUTMod = new Button("Modifier Voyage");
    Button BUTSup = new Button("Supprimer Voyage");
    Button BUTShow = new Button("Show Voyage");
    Button BUTStat = new Button("Show Stat");
    BUTStat.addActionListener((evt) -> new statForm(current).show());
    BUTAdd.addActionListener((evt) -> new AddVoyage(current).show());
    BUTSup.addActionListener((evt) -> new SupprimerVoyage(current).show());
  //  BUTMod.addActionListener((evt) -> new ModifierVoyage(current).show());
    BUTShow.addActionListener((evt) -> new ShowVoyage(current,0).show());
        addAll(BUTShow,BUTAdd,BUTMod,BUTSup,BUTStat);        
    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        previous.showBack();
        });     
    }
    
}
