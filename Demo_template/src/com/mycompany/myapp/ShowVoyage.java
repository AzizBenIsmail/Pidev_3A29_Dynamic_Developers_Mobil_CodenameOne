/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Servise.ServiceVoyage;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ASUS
 */
public class ShowVoyage extends Form {

    public ShowVoyage(Form previous) {
        setTitle("Liste des Voyage");
        setLayout(BoxLayout.y());
         SpanLabel sp = new SpanLabel();
        sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
}
