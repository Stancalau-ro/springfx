package ro.stancalau.springfx.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModalDialog extends Stage {

    private static final Logger logger = LogManager.getLogger(ModalDialog.class);
    private Scene scene;

    public ModalDialog(Modal controller, URL fxml, Window owner, ResourceBundle bundle) {
        this(controller, fxml, owner, StageStyle.DECORATED, Modality.APPLICATION_MODAL, bundle);
    }

    public ModalDialog(final Modal controller, URL fxml, Window owner, StageStyle style, Modality modality, ResourceBundle bundle) {
        super(style);
        initOwner(owner);
        initModality(modality);
        FXMLLoader loader = new FXMLLoader(fxml);
        loader.setResources(bundle);
        try {
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                public Object call(Class<?> aClass) {
                    return controller;
                }
            });
            controller.setDialog(this);
            scene = new Scene((Parent) loader.load());
            setScene(scene);
        } catch (IOException e) {
            logger.error("Error loading modal class", e);
            throw new RuntimeException(e);
        }
    }

    public ObservableList<String> getStyleSheets() {
        return scene.getStylesheets();
    }
}