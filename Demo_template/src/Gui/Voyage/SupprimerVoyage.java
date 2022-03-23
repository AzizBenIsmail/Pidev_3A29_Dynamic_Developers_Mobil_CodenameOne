    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package Gui.Voyage;

    import Servise.ServiceVoyage;
    import static com.codename1.push.PushContent.setTitle;
    import com.codename1.ui.Button;
    import com.codename1.ui.Command;
    import com.codename1.ui.Dialog;
    import com.codename1.ui.FontImage;
    import com.codename1.ui.Form;
    import com.codename1.ui.TextField;
    import com.codename1.ui.events.ActionEvent;
    import com.codename1.ui.events.ActionListener;
    import com.codename1.ui.layouts.BoxLayout;

    /**
     *
     * @author ASUS
     */
    public class SupprimerVoyage extends Form {

        public SupprimerVoyage(Form previous) {
            setTitle("Add Voyage");
            setLayout(BoxLayout.y());
            TextField ID = new TextField("", "ID");
            Button btnValider = new Button("Valider");
            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if ((ID.getText().length()==0))
                        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    else
                    {
                        try {
                            Entity.Voyage Voyage = new Entity.Voyage(Integer.parseInt(ID.getText()));
                            if( ServiceVoyage.getInstance().deletedVoyage(Integer.parseInt(ID.getText())))
                            {
                               Dialog.show("Success","Connection accepted",new Command("OK"));
                            }else
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                        } catch (NumberFormatException e) {
                            Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        }

                    }            }
            });
            getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
            });
              addAll(ID,btnValider);
        }  


    }
