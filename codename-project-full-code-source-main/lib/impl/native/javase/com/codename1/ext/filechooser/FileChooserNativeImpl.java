package com.codename1.ext.filechooser;

public class FileChooserNativeImpl implements com.codename1.ext.filechooser.FileChooserNative{
    public boolean isSupported() {
        return true;
    }
    
    public boolean showNativeChooser(String accept, boolean multi) {
        return false;
    }

}
