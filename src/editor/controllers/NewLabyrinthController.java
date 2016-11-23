package editor.controllers;

import editor.model.Labyrinth;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class NewLabyrinthController {

    @FXML private GridPane gridPane;
    @FXML private TextField mazeXSize;
    @FXML private TextField mazeYSize;
    @FXML private TextField mazeName;

    /**
     * Close the application when the exit menu item is clicked.
     */
    public void handleExitMenuItem() {
        Platform.exit();
    }

    public void buttonPushed(){
        //TODO write to file
        Labyrinth maze = new Labyrinth();
        maze.setName(mazeName.getText());
        maze.setHeight(Integer.valueOf(mazeYSize.getText()));
        maze.setWidth(Integer.valueOf(mazeXSize.getText()));

        FileChooser fc = new FileChooser();
        fc.setTitle("RESOURCES");
        fc.showOpenDialog(gridPane.getScene().getWindow());
    }
}
