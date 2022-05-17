#import <Foundation/Foundation.h>

@interface com_codename1_ext_filechooser_FileChooserNativeImpl : NSObject<UIDocumentMenuDelegate, UIDocumentPickerDelegate> {
}

-(BOOL)isSupported;
-(BOOL)showNativeChooser: (NSString*)accept param1:(BOOL)multi;
- (void)documentPicker:(UIDocumentPickerViewController *)controller didPickDocumentsAtURLs:(NSArray<NSURL *>*)urls;
- (void)documentPicker:(UIDocumentPickerViewController *)controller didPickDocumentAtURL:(NSURL *)url;
- (void)documentPickerWasCancelled:(UIDocumentPickerViewController *)controller;
- (void)documentMenu:(UIDocumentMenuViewController *)documentMenu didPickDocumentPicker:(UIDocumentPickerViewController *)documentPicker;
- (void)documentMenuWasCancelled:(UIDocumentMenuViewController *)documentMenu;
@end
