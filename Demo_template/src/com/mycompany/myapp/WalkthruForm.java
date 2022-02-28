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

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;

/**
 * A swipe tutorial for the application
 *
 * @author Shai Almog
 */
public class WalkthruForm extends Form {
    public WalkthruForm(Resources res) {
        super(new LayeredLayout());
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");
        
        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));
        
        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();
        
        Image travel = res.getImage("travel.png");
        
        
        Label notesPlaceholder = new Label("","ProfilePic");
        Label notesLabel = new Label(travel, "ProfilePic");
        Component.setSameHeight(notesLabel, notesPlaceholder);
        Component.setSameWidth(notesLabel, notesPlaceholder);
        Label bottomSpace = new Label();
        
        Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                notesPlaceholder,
                new Label("Travel me", "WalkthruWhite"),
                new SpanLabel("c'est une application pour faciliter la tache " +
                                            "de tous ce qui souhaite voyager" +
                                            "Voyager pour Vivre.",  "WalkthruBody"),
                bottomSpace
        ));
        tab1.setUIID("WalkthruTab1");
        
        walkthruTabs.addTab("", tab1);
        
        Image Aziz = res.getImage("Aziz1.png");
        
        Label bottomSpaceTab2 = new Label();
        
        Container tab2 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                new Label(Aziz, "ProfilePic"),
                new Label("Aziz Ben Ismail", "WalkthruWhite"),
                new SpanLabel("MohamedAziz.benismail@esprit.tn" +
                                            "ingenieur en informatique" +
                                            "Diplômé de l'Université Esprit!",  "WalkthruBody"),
                bottomSpaceTab2
        ));
        
        tab2.setUIID("WalkthruTab2");

        walkthruTabs.addTab("", tab2);
        Image Aymen = res.getImage("Aymen1.png");
        Label bottomSpaceTab3 = new Label();
        
        Container tab3 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                new Label(Aymen, "ProfilePic"),
                new Label("Aymen Abid", "WalkthruWhite"),
                new SpanLabel("Aymen.Abid@esprit.tn" +
                                            "ingenieur en informatique " +
                                            "Diplômé de l'Université Esprit!",  "WalkthruBody"),
                bottomSpaceTab3
        ));
        
        tab3.setUIID("WalkthruTab2");

        walkthruTabs.addTab("", tab3);
        
        Image Farouk = res.getImage("Farouk.png");
        Label bottomSpaceTab4 = new Label();
        
        Container tab4 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                new Label(Farouk, "ProfilePic"),
                new Label("Farouk daboussi", "WalkthruWhite"),
                new SpanLabel("Farouk.Daboussi@esprit.tn" +
                                            "ingenieur en informatique" +
                                            "Diplômé de l'Université Esprit!",  "WalkthruBody"),
                bottomSpaceTab4
        ));
        
        tab4.setUIID("WalkthruTab2");

        walkthruTabs.addTab("", tab4);
        
        Image oumaima1 = res.getImage("oumaima1.png");
        Label bottomSpaceTab5 = new Label();
        
        Container tab5 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                new Label(oumaima1, "ProfilePic"),
                new Label("oumaima touil", "WalkthruWhite"),
                new SpanLabel("oumaima.touil@esprit.tn" +
                                            "ingenieur en informatique" +
                                            "Diplômé de l'Université Esprit!",  "WalkthruBody"),
                bottomSpaceTab5
        ));
        
        tab5.setUIID("WalkthruTab2");

        walkthruTabs.addTab("", tab5);
        
        Image Ghofrane1 = res.getImage("Ghofrane1.png");
        Label bottomSpaceTab6 = new Label();
        
        Container tab6 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                new Label(Ghofrane1, "ProfilePic"),
                new Label("Ghofrane bengezia", "WalkthruWhite"),
                new SpanLabel("Ghofrane.bengezia@esprit.tn" +
                                            "ingenieur en informatique" +
                                            "Diplômé de l'Université Esprit!",  "WalkthruBody"),
                bottomSpaceTab6
        ));
        
        tab6.setUIID("WalkthruTab2");

        walkthruTabs.addTab("", tab6);
        
        add(walkthruTabs);
        
        ButtonGroup bg = new ButtonGroup();
        Image unselectedWalkthru = res.getImage("unselected-walkthru.png");
        Image selectedWalkthru = res.getImage("selected-walkthru.png");
        RadioButton[] rbs = new RadioButton[walkthruTabs.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        walkthruTabs.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Button skip = new Button("Next");
        skip.setUIID("SkipButton");
        skip.addActionListener(e -> new HomeVoyage(res).show());
        
        Container southLayout = BoxLayout.encloseY(
                        radioContainer,
                        skip
                );
        add(BorderLayout.south(
                southLayout
        ));
        
        Component.setSameWidth(bottomSpace, bottomSpaceTab2, southLayout);
        Component.setSameHeight(bottomSpace, bottomSpaceTab2, southLayout);
        
        // visual effects in the first show
        addShowListener(e -> {
            notesPlaceholder.getParent().replace(notesPlaceholder, notesLabel, CommonTransitions.createFade(1500));
        });
    }    
}
