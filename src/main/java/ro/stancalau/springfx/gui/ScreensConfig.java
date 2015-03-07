package ro.stancalau.springfx.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import ro.stancalau.springfx.model.LanguageModel;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

@Configuration
@Lazy
public class ScreensConfig implements Observer {
    private static final Logger logger = LogManager.getLogger(ScreensConfig.class);

    public static final int WIDTH = 480;
    public static final int HEIGHT = 320;
    public static final String STYLE_FILE = "main.css";

    private Stage stage;
    private Scene scene;
    private LanguageModel lang;
    private StackPane root;

    public void setPrimaryStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void setLangModel(LanguageModel lang) {
        if (this.lang != null) {
            this.lang.deleteObserver(this);
        }
        lang.addObserver(this);
        this.lang = lang;
    }

    public ResourceBundle getBundle() {
        return lang.getBundle();
    }

    public void showMainScreen() {
        root = new StackPane();
        root.getStylesheets().add(STYLE_FILE);
        root.getStyleClass().add("main-window");
        stage.setTitle("SpringFX");
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.setOnHiding(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                System.exit(0);
                // TODO you could add code to open an "are you sure you want to exit?" dialog
            }
        });

        stage.show();
    }

    private void setNode(Node node) {
        root.getChildren().setAll(node);
    }

    private void setNodeOnTop(Node node) {
        root.getChildren().add(node);
    }

    public void removeNode(Node node) {
        root.getChildren().remove(node);
    }

    void loadFirst() {
        setNode(getNode(firstPresentation(), getClass().getResource("First.fxml")));
    }

    void loadSecond() {
        setNode(getNode(secondPresentation(), getClass().getResource("Second.fxml")));
    }

    void loadPopup() {
        ModalDialog modal = new ModalDialog(popupPresentation(), getClass().getResource("Popup.fxml"), stage.getOwner(), lang.getBundle());
        modal.setTitle(lang.getBundle().getString("popup.title"));
        modal.getStyleSheets().add(STYLE_FILE);
        modal.show();
    }

    @Bean
    @Scope("prototype")
    FirstPresentation firstPresentation() {
        return new FirstPresentation(this);
    }

    @Bean
    @Scope("prototype")
    SecondPresentation secondPresentation() {
        return new SecondPresentation(this);
    }

    @Bean
    @Scope("prototype")
    PopupPresentation popupPresentation() {
        return new PopupPresentation(this);
    }

    private Node getNode(final Presentation control, URL location) {
        FXMLLoader loader = new FXMLLoader(location, lang.getBundle());
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            public Object call(Class<?> aClass) {
                return control;
            }
        });

        try {
            return (Node) loader.load();
        } catch (Exception e) {
            logger.error("Error casting node", e);
            return null;
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void update(Observable o, Object arg) {
        loadFirst();
    }
}
