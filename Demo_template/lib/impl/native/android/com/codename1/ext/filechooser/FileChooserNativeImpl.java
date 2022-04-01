package com.codename1.ext.filechooser;

public class FileChooserNativeImpl {
    public boolean isSupported() {
        return true;
    }
    
    public boolean showNativeChooser(String accept, boolean multi) {
        return false;
    }

}
