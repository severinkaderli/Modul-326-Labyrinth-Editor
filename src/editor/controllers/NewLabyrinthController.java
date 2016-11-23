package editor.controllers;

import editor.models.Labyrinth;
import editor.utility.LabyrinthExporter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;

import static editor.utility.InputValidator.validateDimensions;
import static editor.utility.InputValidator.validateName;

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
        try {
            Labyrinth maze = createMazeFromInputs();

            FileChooser fc = new FileChooser();
            fc.setTitle(FILECHOOSER_TITLE);
            File file = fc.showSaveDialog(gridPane.getScene().getWindow());

            LabyrinthExporter.exportXML(maze, file);

        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ooops!");
            alert.setHeaderText(ex.getMessage());
            alert.showAndWait();
        }
    }

    private Labyrinth createMazeFromInputs() throws IllegalArgumentException{
        int height;
        int width;
        String name;

        try {
            height = validateDimensions(mazeYSize.getText());
            width = validateDimensions(mazeXSize.getText());
            name = validateName(mazeName.getText());
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException(ex);
        }

        Labyrinth maze = new Labyrinth(width, height, name);

        maze.setData(Labyrinth.initalizeData(width, height));

        return maze;
    }
}
