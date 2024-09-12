package otto;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Otto otto;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image ottoImage = new Image(this.getClass().getResourceAsStream("/images/owl.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Otto instance */
    public void setOtto(Otto o) {
        otto = o;
    }

    /**
     * Displays the introduction message from Otto when the program starts.
     */
    @FXML
    public void intro() {
        String response = OttoResponses.intro;
        dialogContainer.getChildren().addAll(
                DialogBox.getOttoDialog(response, ottoImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * If the user inputs "bye", the program will exit.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
            return;
        }
        String response = otto.handleInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOttoDialog(response, ottoImage)
        );
        userInput.clear();
    }
}
