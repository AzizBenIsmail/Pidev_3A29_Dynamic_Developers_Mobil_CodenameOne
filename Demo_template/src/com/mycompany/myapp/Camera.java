/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
/**
 *
 * @author ASUS
 */
public class Camera {

    public Camera() {
          Form f = new Form("Camera", new BorderLayout());
        ImageViewer l = new ImageViewer();
                
        f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_CAMERA_ALT, 4, (ev) -> {
            String path = Capture.capturePhoto();
            if(path == null) {
                showToast("User canceled Camera");
                return;
            }
            setImage(path, l);
        });

        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_PHOTO, 4, (ev) -> {
            Display.getInstance().openGallery(e -> {
                if(e == null || e.getSource() == null) {
                    showToast("User canceled Gallery");
                    return;
                }
                String filePath = (String)e.getSource();
                setImage(filePath, l);
            }, Display.GALLERY_IMAGE);
        });
        
        f.add(BorderLayout.CENTER, l);
        
        f.show();
    }

    private void setImage(String filePath, ImageViewer iv) {
            try {
                Image i1 = Image.createImage(filePath);
                iv.setImage(i1);
                iv.getParent().revalidate();
            } catch (Exception ex) {
                Log.e(ex);
                Dialog.show("Error", "Error during image loading: " + ex, "OK", null);
            }
    }
    
    private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }
    }
