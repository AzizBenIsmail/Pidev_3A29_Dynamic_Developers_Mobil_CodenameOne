/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Excursion;

import Entity.ReservationExcursion;
import Servise.ServiceReservationExcursion;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.table.TableLayout;

/**
 *
 * @author Oumayma
 */
public class ModifierReservationForm extends Form{
    
    Form f;
    public ModifierReservationForm(ReservationExcursion re) {
        f=this;
       setTitle(" Modidfier Reservation ");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListReservationExcursionForm().show());
        TableLayout tl = new TableLayout(3, 2);
        setLayout(tl);
       
    
       TextField email = new TextField(re.getEmail(), "Entrer votre email pour receverer les details de paiement");
       TextField Adulte = new TextField("", "Nombre Adulte");
       TextField Enfant = new TextField("", "Nombre Enfant");
    
       
       Button btnModifier = new Button("MODIFIER");
        
        btnModifier.addPointerPressedListener(l ->   { 
           int nb=Integer.valueOf(Adulte.getText().toString())+Integer.valueOf(Enfant.getText().toString());
           re.setEmail(email.getText());
           re.setNb(nb);
           
           
       
       //appel fonction modfier reclamation men service
       
       if(ServiceReservationExcursion.getInstance().modifierReservationExcursion(re)) { // if true
           new ListReservationExcursionForm().show();
       }
        });
      
        
         f.add(tl.createConstraint().horizontalSpan(2), new FloatingHint(email));
         f.add(tl.createConstraint().widthPercentage(30), new FloatingHint(Adulte));
         f.add(tl.createConstraint().widthPercentage(70), new FloatingHint(Enfant));
         f.add(btnModifier);
         f.show();
    }
     /**
     * Shows the specified error message in a modal dialog.
     * @param msg 
     */
    public void showError(String msg) {
        Dialog.show("Failed", msg, "OK", null);
    }  
    }
    

