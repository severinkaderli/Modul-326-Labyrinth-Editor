package editor.controllers;

import editor.models.Labyrinth;
import editor.utility.LabyrinthExporter;
import editor.utility.LabyrinthImporter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

/**
 * This controller manages the main logic for this application.
 */
public class EditorController {

    /**
     * The current state of the labyrinth
     */
    private Labyrinth labyrinth;

    @FXML
    private GridPane rootPane;

    /**
     * Open a file chooser to select an existing labyrinth file which will be opened.
     */
    public void handleOpenMenuItem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open labyrinth file");

        // Configure the file chooser so only xml files will be shown
        FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML-Files", "*.xml");
        fileChooser.getExtensionFilters().add(xmlFilter);

        // Import the xml file
        File xmlFile = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        labyrinth = LabyrinthImporter.importXML(xmlFile);

        // TODO: This is only used for testing purposes. Should be removed later.
        LabyrinthExporter.exportXML(labyrinth);
    }

    public void handleNewMenuItem(){
        System.out.println("New");
        //initialise a dialog
        final Stage dialog = new Stage(StageStyle.TRANSPARENT);
        dialog.initModality(Modality.WINDOW_MODAL);
        try{
            Parent newLabyrinthRoot = FXMLLoader.load(getClass().getResource("/fxml/newLabyrinth.fxml"));
            dialog.setScene(new Scene(newLabyrinthRoot, 600, 400));
        }catch(IOException ex){
            System.out.println("IOEX: " + ex.getMessage());
        }
        dialog.setTitle("Neues Labyrinth erstellen");
        dialog.initOwner(rootPane.getScene().getWindow());
        dialog.showAndWait();
    }

    public void handleSaveMenuItem(){
        System.out.println("Save");
    }

    /**
     * Close the application when the exit menu item is clicked.
     */
    public void handleExitMenuItem() {
        Platform.exit();
    }

}
