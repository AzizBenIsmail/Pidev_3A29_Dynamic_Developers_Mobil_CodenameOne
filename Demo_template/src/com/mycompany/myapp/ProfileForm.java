/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp;

import Gui.ReserverVoyage.AddResrvationVoyage;
import Servise.ServiceVoyage;
import Servise.ServiseExcusion;
import Servise.ServiseRestaurant;
import Utils.Statics;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 * Represents a user profile in the app, the first form we open after the walkthru
 *
 * @author Shai Almog
 */
public class ProfileForm extends SideMenuBaseForm {
       Form current;
    public ProfileForm(Resources res,Form previous) {
                super(BoxLayout.y());
        current=this;
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("2.png");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Aziz Ben Ismail", "Title"),
                                    new Label("Travel me", "SubTitle")
                                )
                            ).add(BorderLayout.WEST, profilePicLabel)
                );
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                        
        add(new Label("Today", "TodayTitle"));
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
       // addButtonBottom(arrowDown, "Finish landing page concept", 0xd997f1, true);
        
        //listVoyage
        
        addButtonBottom(arrowDown, "La Liste Des Voyage", 0x5ae29d, false);
       
        ArrayList<Entity.Voyage> voyages = ServiceVoyage.getInstance().affichageVoyage();
        Container listVoyage = new Container(BoxLayout.y());
         listVoyage.setScrollableY(true);
        for (Entity.Voyage voyage : voyages) {
            
              EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              Image i = URLImage.createToStorage(placeholder,voyage.getImage(),Statics.BASE_URL+"/uploads/"+voyage.getImage());
             MultiButton sp = new MultiButton(voyage.getNom_Voyage());
             sp.setIcon(i.fill(200, 200));
              sp.setTextLine1("Destination : "+voyage.getDestination()+" Prix : "+voyage.getPrix_Voyage());
              sp.setTextLine2("Durèe : "+voyage.getDuree_Voyage());
                     listVoyage.add(sp);
                     
                     sp.addActionListener((evt) -> {
                                        new AddResrvationVoyage(current,voyage).show();
                     });
        }
        
         //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(listVoyage);
        
        //La Liste Des Restaurant
        
        addButtonBottom(arrowDown, "La Liste Des Restaurant ", 0x4dc2ff, false);
        
        ArrayList<Entity.Restaurant> restaurants = ServiseRestaurant.getInstance().affichageRestaurant();
        Container listrestaurant = new Container(BoxLayout.y());
         listrestaurant.setScrollableY(true);
        for (Entity.Restaurant restaurant : restaurants) {
            
              EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              Image i = URLImage.createToStorage(placeholder,restaurant.getImage(),Statics.BASE_URL+"/uploads/"+restaurant.getImage());
             MultiButton sp = new MultiButton(restaurant.getAdresse_Restaurant());
             sp.setIcon(i.fill(200, 200));
              sp.setTextLine1("Nom : "+restaurant.getNom_Restaurant());
              sp.setTextLine2("Adresse : "+restaurant.getAdresse_Restaurant());
                     listrestaurant.add(sp);
                     
                     sp.addActionListener((evt) -> {
                         //affichage en details details(voyage).show(); 
                         //autre page 
                         //ajouter panier 
                     });
        }
        
         //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(listrestaurant); 
        
        //La Liste Des Excursions
       
        addButtonBottom(arrowDown, "La Liste Des Excursions", 0xffc06f, false);
        
        setupSideMenu(res);
    
        ArrayList<Entity.Excursion> Excursions = ServiseExcusion.getInstance().affichageExcursion();
        Container listExcursion = new Container(BoxLayout.y());
         listExcursion.setScrollableY(true);
        for (Entity.Excursion Excursion : Excursions) {
            
              EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              Image i = URLImage.createToStorage(placeholder,Excursion.getImage(),Statics.BASE_URL+"/uploads/"+Excursion.getImage());
             MultiButton sp = new MultiButton(Excursion.getDescription_Excursion());
             sp.setIcon(i.fill(200, 200));
              sp.setTextLine1("Nom : "+Excursion.getNom_Excursion()+" Lieu : "+Excursion.getLieu());
              sp.setTextLine2("Type : "+Excursion.getType_Excursion()+" | Prix : "+Excursion.getPrix());
                     listExcursion.add(sp);
                     
                     sp.addActionListener((evt) -> {
                         //affichage en details details(voyage).show(); 
                         //autre page 
                         //ajouter panier 
                     });
        }
        
         //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(listExcursion); 

   
    
    }
    
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}
