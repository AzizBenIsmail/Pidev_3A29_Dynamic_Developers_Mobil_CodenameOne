#import "com_codename1_ext_filechooser_FileChooserNativeImpl.h"
#import "CodenameOne_GLViewController.h"
#import <MobileCoreServices/MobileCoreServices.h>
#import "com_codename1_ext_filechooser_FileChooser.h"

extern int isIPad();
extern int CN1lastTouchX;
extern int CN1lastTouchY;
static BOOL multiSelect = NO;

static UIPopoverController* popoverController;
static int popoverSupported()
{
    return ( NSClassFromString(@"UIPopoverController") != nil) &&  (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad);
}

@implementation com_codename1_ext_filechooser_FileChooserNativeImpl

-(BOOL)isSupported{
    return YES;
}

//public boolean showNativeChooser(String accept)
//public boolean showNativeChooser(String accept)
-(BOOL)showNativeChooser: (NSString*)accept param1:(BOOL)multi {
    //accept = @"pdf,jpg,png,txt,rtf";
    multiSelect = multi;
    id me = self;
    dispatch_async(dispatch_get_main_queue(), ^{
        POOL_BEGIN();
        /*
        @try {
            UIDocumentPickerViewController *documentPicker = [[UIDocumentPickerViewController alloc] initWithDocumentTypes:[self preferredUTIsForExtensions:accept]
                                                                                                                inMode:UIDocumentPickerModeImport];
            if (@available(iOS 11, *)) {
                [documentPicker setAllowsMultipleSelection:multi];
            }
            
            documentPicker.delegate = me;
            documentPicker.modalPresentationStyle = UIModalPresentationFormSheet;
           
            [[CodenameOne_GLViewController instance] presentViewController:documentPicker animated:YES completion:^{
                NSLog(@"Here we are");
            }];
        } @catch (NSException *e) {
            NSLog(@"%@", e);
        }
         */
        
        @try {
            UIDocumentMenuViewController *documentProviderMenu =
            [[UIDocumentMenuViewController alloc] initWithDocumentTypes:[self preferredUTIsForExtensions:accept]
                                                                 inMode:UIDocumentPickerModeImport];
            documentProviderMenu.delegate = me;
            if ([accept containsString:@"image"] || [accept containsString:@"jpg"] || [accept containsString:@"png"] || [accept containsString:@"tif"] || [accept containsString:@"gif"]) {
                [documentProviderMenu addOptionWithTitle:@"Images" image:nil order:UIDocumentMenuOrderLast handler:^{
                    [me openGallery:0];
                }];
                
            }
            if ([accept containsString:@"video"] || [accept containsString:@"avi"] || [accept containsString:@"mpg"] || [accept containsString:@"mp4"] || [accept containsString:@"mpeg"] || [accept containsString:@"mov"]) {
                [documentProviderMenu addOptionWithTitle:@"Videos" image:nil order:UIDocumentMenuOrderLast handler:^{
                    [me openGallery:1];
                }];
                
            }
            if (isIPad()) {
                documentProviderMenu.popoverPresentationController.sourceView = [[CodenameOne_GLViewController instance] view];
                documentProviderMenu.popoverPresentationController.sourceRect = CGRectMake(CN1lastTouchX, CN1lastTouchY, 1, 1);
            }
            [[CodenameOne_GLViewController instance] presentViewController:documentProviderMenu animated:YES completion:nil];
        } @catch (NSException *e) {
            NSString * reason = e.reason;
        }
        
        
        POOL_END();
    });
    return YES;
}

- (void)documentPicker:(UIDocumentPickerViewController *)controller didPickDocumentsAtURLs:(NSArray<NSURL *> *)urls {
    NSMutableString *urlString=[[NSMutableString alloc] init];
    BOOL first = YES;
    for (NSURL* url in urls) {
        if (first) {
            first = NO;
        } else {
            [urlString appendString:@"\n"];
        }
        [urlString appendString:[url path]];
    }
    if (controller.documentPickerMode == UIDocumentPickerModeImport) {
        [controller dismissViewControllerAnimated:YES completion:nil];
        com_codename1_ext_filechooser_FileChooser_fireNativeOnComplete___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG fromNSString(CN1_THREAD_GET_STATE_PASS_ARG urlString));
    }
}

- (void)documentPicker:(UIDocumentPickerViewController *)controller didPickDocumentAtURL:(NSURL*)url {
    
    if (controller.documentPickerMode == UIDocumentPickerModeImport) {
        [controller dismissViewControllerAnimated:YES completion:nil];
        com_codename1_ext_filechooser_FileChooser_fireNativeOnComplete___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG fromNSString(CN1_THREAD_GET_STATE_PASS_ARG [url path]));
    }
}

- (void)documentMenu:(UIDocumentMenuViewController *)documentMenu didPickDocumentPicker:(UIDocumentPickerViewController *)documentPicker {
    //UIDocumentPickerViewController *documentPicker = [[UIDocumentPickerViewController alloc] initWithDocumentTypes:[self preferredUTIsForExtensions:accept]
    //                                                                                                        inMode:UIDocumentPickerModeImport];
    documentPicker.delegate = self;
    if (@available(iOS 11, *)) {
        [documentPicker setAllowsMultipleSelection:multiSelect];
    }
    
    documentPicker.modalPresentationStyle = UIModalPresentationFormSheet;
    [[CodenameOne_GLViewController instance] presentViewController:documentPicker animated:YES completion:nil];
    
}

- (void)documentPickerWasCancelled:(UIDocumentPickerViewController *)controller {
    [controller dismissViewControllerAnimated:YES completion:nil];
    com_codename1_ext_filechooser_FileChooser_fireNativeOnComplete___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG JAVA_NULL);
}

- (void)documentMenuWasCancelled:(UIDocumentMenuViewController *)documentMenu {
    com_codename1_ext_filechooser_FileChooser_fireNativeOnComplete___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG JAVA_NULL);

}

-(NSString *) preferredUTIForExtension:(NSString *)ext
{
    // Request the UTI for the file extension
    CFStringRef str = (__bridge CFStringRef)ext;
    
    CFStringRef theUTI = /*(__bridge_transfer NSString *)*/
        UTTypeCreatePreferredIdentifierForTag(
            kUTTagClassFilenameExtension,
           str, nil);
    NSString *uti = (__bridge NSString*)theUTI;
    return uti;
}

-(NSArray *) preferredUTIsForExtensions:(NSString *)exts {
    NSArray *arr = [exts componentsSeparatedByString:@","];
    NSMutableArray *out = [NSMutableArray arrayWithCapacity:[arr count]];
    NSMutableString *string = [NSMutableString stringWithString:@""];
    for (NSString *ext in arr) {
        [out addObject:[self preferredUTIForExtension:ext]];

    }
    //[arr autorelease];
    //[string autorelease];
    //[out autorelease];
    return out;
}


-(void) openGallery:(int)type {
    id me = self;
    dispatch_async(dispatch_get_main_queue(), ^{
        POOL_BEGIN();
        UIImagePickerControllerSourceType sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
        if(![UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypePhotoLibrary]) {
            if(![UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeSavedPhotosAlbum]) {
                return;
            }
            sourceType = UIImagePickerControllerSourceTypeSavedPhotosAlbum;
        }
        popoverController = nil;
        
#ifndef CN1_USE_ARC
        UIImagePickerController* pickerController = [[[UIImagePickerController alloc] init] autorelease];
#else
        UIImagePickerController* pickerController = [[UIImagePickerController alloc] init];
#endif
        
        pickerController.delegate = me;
        pickerController.sourceType = sourceType;
        if (type==0){
            pickerController.mediaTypes = [[NSArray alloc] initWithObjects:(NSString*)kUTTypeImage, nil];
        } else if (type==1){
            pickerController.mediaTypes = [[NSArray alloc] initWithObjects:(NSString*)kUTTypeMovie, nil];
        } else if (type==2){
            pickerController.mediaTypes = [[NSArray alloc] initWithObjects:(NSString*)kUTTypeMovie, (NSString*)kUTTypeImage,  nil];
        }
        
        if(popoverSupported()) {
#ifndef CN1_USE_ARC
            popoverController = [[[NSClassFromString(@"UIPopoverController") alloc]
                                  initWithContentViewController:pickerController] autorelease];
#else
            popoverController = [[NSClassFromString(@"UIPopoverController") alloc]
                                 initWithContentViewController:pickerController];
#endif
            popoverController.delegate = me;
            [popoverController presentPopoverFromRect:CGRectMake(0,32,320,480)
                                               inView:[[CodenameOne_GLViewController instance] view]
                             permittedArrowDirections:UIPopoverArrowDirectionAny
                                             animated:YES];
#ifndef CN1_USE_ARC
            [popoverController retain];
#endif
        } else {
            [[CodenameOne_GLViewController instance] presentModalViewController:pickerController animated:YES];
        }
        POOL_END();
    });
}

- (void)popoverControllerDidDismissPopover:(id)popoverController {
    com_codename1_ext_filechooser_FileChooser_fireNativeOnComplete___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG nil);
}

-(void)imagePickerControllerDidCancel:(UIImagePickerController *)picker {
    //[self dismissModalViewControllerAnimated:YES];
    //com_codename1_impl_ios_IOSImplementation_capturePictureResult___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG nil);
    com_codename1_ext_filechooser_FileChooser_fireNativeOnComplete___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG nil);
    [picker dismissModalViewControllerAnimated:YES];
}

- (void)imagePickerController:(UIImagePickerController*)picker didFinishPickingMediaWithInfo:(NSDictionary*)info {
    POOL_BEGIN();
    NSString* mediaType = [info objectForKey:UIImagePickerControllerMediaType];
    if ([mediaType isEqualToString:@"public.image"]) {
        // get the image
        UIImage* originalImage = [info objectForKey:UIImagePickerControllerOriginalImage];
#ifndef CN1_USE_ARC
        [originalImage retain];
#endif
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
            POOL_BEGIN();
            UIImage* image = originalImage;
            BOOL releaseImage = YES;
#ifndef LOW_MEM_CAMERA
            if (image.imageOrientation != UIImageOrientationUp) {
                UIGraphicsBeginImageContextWithOptions(image.size, NO, image.scale);
                [image drawInRect:(CGRect){0, 0, image.size}];
                releaseImage = NO;
#ifndef CN1_USE_ARC
                [originalImage release];
#endif
                image = UIGraphicsGetImageFromCurrentImageContext();
                UIGraphicsEndImageContext();
            }
#endif
            
            NSData* data = UIImageJPEGRepresentation(image, 90 / 100.0f);
            
            NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
            NSString *documentsDirectory = [paths objectAtIndex:0];
            NSString *path = [documentsDirectory stringByAppendingPathComponent:@"temp_image.jpg"];
            [data writeToFile:path atomically:YES];
            if(releaseImage) {
#ifndef CN1_USE_ARC
                [originalImage release];
#endif
            }
            path = [NSString stringWithFormat: @"file://%@", path];
            com_codename1_ext_filechooser_FileChooser_fireNativeOnComplete___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG fromNSString(CN1_THREAD_GET_STATE_PASS_ARG path));
            //com_codename1_impl_ios_IOSImplementation_capturePictureResult___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG fromNSString(CN1_THREAD_GET_STATE_PASS_ARG path));
            POOL_END();
        });
        
    } else {
        // was movie type
        NSString *moviePath = [[info objectForKey: UIImagePickerControllerMediaURL] absoluteString];
        moviePath = [NSString stringWithFormat: @"file://%@", moviePath];
        //com_codename1_impl_ios_IOSImplementation_captureMovieResult___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG fromNSString(CN1_THREAD_GET_STATE_PASS_ARG moviePath));
        com_codename1_ext_filechooser_FileChooser_fireNativeOnComplete___java_lang_String(CN1_THREAD_GET_STATE_PASS_ARG fromNSString(CN1_THREAD_GET_STATE_PASS_ARG moviePath));
    }
    
    if(popoverSupported() && popoverController != nil) {
        [popoverController dismissPopoverAnimated:YES];
        popoverController.delegate = nil;
        popoverController = nil;
    } else {
#ifdef LOW_MEM_CAMERA
        [picker dismissModalViewControllerAnimated:NO];
#else
        [picker dismissModalViewControllerAnimated:YES];
#endif
    }
    
    //picker.delegate = nil;
    //picker = nil;
    POOL_END();
}



@end
