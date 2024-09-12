package otto;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Otto using FXML.
 */
public class Main extends Application {

    private Otto otto = Otto.getInstance();

    /**
     * Starts the GUI for Otto.
     * @param stage The stage to display the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setOtto(otto);  // inject the Duke instance
            fxmlLoader.<MainWindow>getController().intro();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
