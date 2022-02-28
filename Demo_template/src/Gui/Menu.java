/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.mycompany.myapp.*;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ASUS
 */
public class Menu extends Form {
Form current;
    public Menu() {
          current=this; //Back 
                
        add(new Label("Choose an option"));
        setTitle("homme Page ");
        setLayout(BoxLayout.y());
        
    Button BUTAdd = new Button("Add Voyage");
    Button BUTShow = new Button("Show Voyage");
//BUTAdd.addActionListener((evt) -> AddVoyage(current).show());
//BUTShow.addActionListener((evt) -> new ShowVoyage(current).show());
        add(BUTAdd);
        add(BUTShow);
    }

    private void setLayout(BoxLayout y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void add(Label label) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
