/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation;

import Entity.Reclamation;
import Servise.ServiceReclamation;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author Oumayma
 */
public class ModifierReclamationForm extends Form {
    
    Form current;
    public ModifierReclamationForm(Resources res , Reclamation r, Form previous) {
         super("Ajout Reclamation",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
                getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
            });
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        //super.addSideMenu(res);
        
        TextField objet = new TextField(r.getObjet() , "Objet" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescriptionR() , "Description" , 20 , TextField.ANY);
        TextField type = new TextField(String.valueOf(r.getTypeR()) , "Type" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
        ComboBox typeCombo = new ComboBox();
        
        typeCombo.addItem("Technique");
        
        typeCombo.addItem("Au cour d'un voyage");
        
        typeCombo.addItem("Au cour d'une excursion");
        
        typeCombo.addItem("A propos un Restaurant");
        
        if(r.getTypeR() == "Technique" ) {
            typeCombo.setSelectedIndex(0);
        }
        else if(r.getTypeR() == "Au cour d'un voyage" ) {
            typeCombo.setSelectedIndex(1);
        }
        else if(r.getTypeR() == "Au cour d'une excursion" ) {
            typeCombo.setSelectedIndex(2);
        }
        else if(r.getTypeR() == "A propos un Restaurant" ) {
            typeCombo.setSelectedIndex(3);
        }
        
        
        
        
        
        objet.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        type.setUIID("NewsTopLine");
        
        objet.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        type.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setObjet(objet.getText());
           r.setDescriptionR(description.getText());
           
           if(typeCombo.getSelectedIndex() == 0 ) {
               r.setTypeR("Technique");
           }
           else if(typeCombo.getSelectedIndex() == 1) {
               r.setTypeR("Voyage");
           } 
           else if(typeCombo.getSelectedIndex() == 2 ){
               r.setTypeR("Excursion");
           }
           else if(typeCombo.getSelectedIndex() == 3){
               r.setTypeR("Restaurant");
           }
               
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceReclamation.getInstance().modifierReclamation(r)) { // if true
           new ListReclamationForm(res,current).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListReclamationForm(res,current).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(objet),
            //    createLineSeparator(),
                new FloatingHint(description),
             //   createLineSeparator(),
                typeCombo,
             //   createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
