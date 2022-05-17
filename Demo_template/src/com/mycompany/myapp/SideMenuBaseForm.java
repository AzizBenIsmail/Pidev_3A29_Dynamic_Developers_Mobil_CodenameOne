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


import Gui.Excursion.Excursion;
import Gui.Excursion.ListExcursionForm;
import Gui.MapForm;
import Gui.ReserverVoyage.ShowReservationVoyage;
import Gui.Restaurant.Restaurant;
import Gui.Voyage.VoyageHome;
import Gui.premium.LoginPremium;
import Post.ListPostForm;
import Reclamation.ListReclamationForm;
import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {
        Form current;
        private Resources theme;
    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        Image profilePic = res.getImage("2.png");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("Aziz Ben Ismail", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
                 

        getToolbar().addMaterialCommandToSideMenu("  Reservation Voyage", FontImage.MATERIAL_FLIGHT_TAKEOFF,  e -> new ShowReservationVoyage(res,current).show());
        getToolbar().addMaterialCommandToSideMenu("  Restaurant", FontImage.MATERIAL_DINING,  e -> new Restaurant(res,current).show());
        getToolbar().addMaterialCommandToSideMenu("  Excursion", FontImage.MATERIAL_GRASS,  e -> new ListExcursionForm(res,current).show());
        getToolbar().addMaterialCommandToSideMenu("  Reclamation", FontImage.MATERIAL_REPORT_PROBLEM,  e -> new ListReclamationForm(res,current).show());
        getToolbar().addMaterialCommandToSideMenu("  Poste", FontImage.MATERIAL_RECEIPT,  e -> new ListPostForm(res,0).show());
        getToolbar().addMaterialCommandToSideMenu("  Map", FontImage.MATERIAL_MAP,  e -> new MapForm(res,current));
        getToolbar().addMaterialCommandToSideMenu("  Menu", FontImage.MATERIAL_MENU,  e -> new HomeVoyage(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Premium" ,FontImage.MATERIAL_VERIFIED,  e -> new LoginPremium(res,current).show());
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());

    }
    
    protected abstract void showOtherForm(Resources res);
}
