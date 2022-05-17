(function(exports){

var o = {};

    o.isSupported_ = function(callback) {
        callback.complete(true);
    };
    //public boolean showNativeChooser(String accept)
    o.showNativeChooser__java_lang_String_boolean = function(accept, multi, callback) {
        callback.complete(false);
    }

exports.com_codename1_ext_filechooser_FileChooserNative= o;

})(cn1_get_native_interfaces());
