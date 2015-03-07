package ro.stancalau.springfx.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import org.springframework.beans.factory.annotation.Autowired;
import ro.stancalau.springfx.control.LanguageController;
import ro.stancalau.springfx.model.LanguageModel.Language;

public class FirstPresentation extends Presentation {

    public FirstPresentation(ScreensConfig config) {
        super(config);
    }

    @FXML
    RadioButton engRadio, romRadio;
    @FXML
    ToggleGroup langGroup;

    @Autowired
    private LanguageController langCtr;

    @FXML
    void nextView(ActionEvent event) {
        config.loadSecond();
    }

    @FXML
    void initialize() {
        if (Language.RO.equals(langCtr.getLanguage())) {
            engRadio.setSelected(false);
            romRadio.setSelected(true);
        }
        langGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                changeLanguage();
            }
        });
    }

    private void changeLanguage() {
        if (engRadio.isSelected())
            langCtr.toEnglish();
        else
            langCtr.toRomanian();
    }
}
