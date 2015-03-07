package ro.stancalau.springfx.control;


import ro.stancalau.springfx.model.LanguageModel;
import ro.stancalau.springfx.model.LanguageModel.Language;

public class LanguageController {

    private LanguageModel model;

    public LanguageController(LanguageModel model) {
        this.model = model;
        toEnglish();
    }

    public void toEnglish() {
        model.setBundle(Language.EN);
    }

    public void toRomanian() {
        model.setBundle(Language.RO);
    }

    public Language getLanguage() {
        return model.getLanguage();
    }
}
