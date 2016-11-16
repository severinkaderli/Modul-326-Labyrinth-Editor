package editor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class NewLabyrinthController {

    @FXML private GridPane gridPane;

    /**
     * Close the application when the exit menu item is clicked.
     */
    public void handleExitMenuItem() {
        Platform.exit();
    }

    public void buttonPushed(){
        FileChooser fc = new FileChooser();
        fc.setTitle("RESOURCES");
        fc.showOpenDialog(gridPane.getScene().getWindow());
    }
}
