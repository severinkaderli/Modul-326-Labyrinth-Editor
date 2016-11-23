package editor.controllers;

import editor.model.GameElement;
import editor.model.Labyrinth;
import editor.utility.NewLabyrinthInputValidator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.util.ArrayList;

import static editor.utility.NewLabyrinthInputValidator.validateDimensions;
import static editor.utility.NewLabyrinthInputValidator.validateName;

public class NewLabyrinthController {

    @FXML private GridPane gridPane;
    @FXML private TextField mazeXSize;
    @FXML private TextField mazeYSize;
    @FXML private TextField mazeName;

    private static String FILECHOOSER_TITLE = "Speicherort auswählen";

    /**
     * Close the application when the exit menu item is clicked.
     */
    public void handleExitMenuItem() {
        Platform.exit();
    }

    /**
     * trigger the creation and saving of the maze when the "save" button is pushed
     */
    public void buttonPushed(){
        Labyrinth maze = createMazeFromInputs();

        FileChooser fc = new FileChooser();
        fc.setTitle(FILECHOOSER_TITLE);
        fc.showOpenDialog(gridPane.getScene().getWindow());
    }

    private Labyrinth createMazeFromInputs() {
        int height;
        int width;
        String name;

        height = validateDimensions(mazeYSize.getText());
        width = validateDimensions(mazeXSize.getText());
        name = validateName(mazeName.getText());

        Labyrinth maze = new Labyrinth(width, height, name);

        maze.setData(Labyrinth.initalizeData(width, height));

        return maze;
    }
}
