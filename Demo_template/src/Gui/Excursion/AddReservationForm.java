/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Excursion;
import Entity.Excursion;

import Entity.ReservationExcursion;
import Servise.ServiceReservationExcursion;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;

import java.util.Date;

/**
 *
 * @author Oumayma
 */
public class AddReservationForm extends Form {
     Form f;
    public AddReservationForm(Excursion ex) {
       f=this;
        setTitle("Reserver votre place pour "+ex.getNom());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new DetailsExcursionForm(ex,f).show());
        TableLayout tl = new TableLayout(3, 2);
        setLayout(tl);
       
    
       TextField email = new TextField("", "Entrer votre email pour receverer les details de paiement");
       TextField Adulte = new TextField("", "Nombre Adulte");
       TextField Enfant = new TextField("", "Nombre Enfant");
    
       
       Button submitButton = new Button("RESERVER");
        
        submitButton.addActionListener((ActionEvent evt)->{
            try {
                 if(email.getText().equals("") || Adulte.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                
                 }
                else { 
               
                    InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    //njibo iduser men session (current user)
                    ReservationExcursion r = new ReservationExcursion();
                    
                       java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");  
                       String type="";
                       System.out.println(ex.getId());
                      int nb=Integer.valueOf(Adulte.getText().toString())+Integer.valueOf(Enfant.getText().toString());
                      r = new ReservationExcursion(ex.getId(),
                                              nb,
                                    String.valueOf(email.getText()).toString()
                                    );
                                 //SessionManager.getId());  
                    
                    
                    
                    System.out.println("data  post == "+r);
                    
                    
                    //appelle methode ajouterPost mt3 service Post bch nzido données ta3na fi base 
                    ServiceReservationExcursion.getInstance().ajoutReservationExcursion(r);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                  new ListReservationExcursionForm().show();
            } }catch(Exception err) {
               showError(err.getMessage());
              
            }
        });
        
         f.add(tl.createConstraint().horizontalSpan(2), new FloatingHint(email));
         f.add(tl.createConstraint().widthPercentage(30), new FloatingHint(Adulte));
         f.add(tl.createConstraint().widthPercentage(70), new FloatingHint(Enfant));
         f.add(submitButton);
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
