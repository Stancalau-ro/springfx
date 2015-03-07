package ro.stancalau.springfx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ro.stancalau.springfx.control.LanguageController;
import ro.stancalau.springfx.gui.ScreensConfig;
import ro.stancalau.springfx.model.LanguageModel;
import ro.stancalau.springfx.model.MessageModel;


@Configuration
@Import(ScreensConfig.class)
public class AppConfig {
    @Bean
    LanguageModel languageModel() {
        return new LanguageModel();
    }

    @Bean
    LanguageController languageController() {
        return new LanguageController(languageModel());
    }

    @Bean
    MessageModel messageModel() {
        return new MessageModel();
    }
}
