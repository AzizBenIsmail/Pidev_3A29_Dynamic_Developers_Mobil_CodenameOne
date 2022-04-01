namespace com.codename1.ext.filechooser{

using System;
using System.Windows;

public class FileChooserNativeImpl {
    public bool isSupported() {
        return false;
    }

    public bool showNativeChooser(string accept, bool multi) {
        return false;
    }

}
}
