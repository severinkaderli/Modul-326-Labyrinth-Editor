package editor.controllers;

import editor.models.GameElement;
import editor.models.Labyrinth;
import editor.utility.LabyrinthExporter;
import editor.utility.LabyrinthImporter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This controller manages the main logic for this application.
 */
public class EditorController {

    /**
     * The current state of the labyrinth
     */
    private Labyrinth labyrinth;

    private ArrayList<ArrayList<GameElement>> labyrinthData;

    /**
     * The current opened file.
     */
    private File currentFile;

    @FXML
    private GridPane rootPane;

    @FXML
    private GridPane canvasGridPane;

    //tile size, depends on the size of the labyrinth
    private int TILE_SIZE = 40;

    private final int WINDOW_HEIGHT = 400;
    private final int WINDOW_WIDTH = 600;
    private final int MARGIN = 80;

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
        currentFile = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        labyrinth = LabyrinthImporter.importXML(currentFile);
        labyrinthData = labyrinth.getData();

        //show data canvas
        initializeEditorCanvas();
        populateEditorCanvas();
    }

    public void handleNewMenuItem(){
        System.out.println("New");
        //initialise a dialog
        final Stage dialog = new Stage(StageStyle.TRANSPARENT);
        dialog.initModality(Modality.WINDOW_MODAL);
        try{
            Parent newLabyrinthRoot = FXMLLoader.load(getClass().getResource("/fxml/newLabyrinth.fxml"));
            dialog.setScene(new Scene(newLabyrinthRoot, WINDOW_WIDTH, WINDOW_HEIGHT));
        }catch(IOException ex){
            System.out.println("IOEX: " + ex.getMessage());
        }
        dialog.setTitle("Neues Labyrinth erstellen");
        dialog.initOwner(rootPane.getScene().getWindow());
        dialog.showAndWait();
    }

    public void handleSaveMenuItem(){
        if(currentFile != null) {
            // TODO: Validate the labyrinth before saving

            // Save the labyrinth in file
            LabyrinthExporter.exportXML(labyrinth, currentFile);
        } else{
            System.out.println("No currentFile");
        }
    }

    private void initializeEditorCanvas(){
        TILE_SIZE = (WINDOW_HEIGHT - MARGIN) / Integer.max(labyrinth.getHeight(), labyrinth.getWidth());

        RowConstraints r = new RowConstraints();
        r.setPrefHeight(TILE_SIZE);
        r.setMinHeight(TILE_SIZE);
        r.setMaxHeight(TILE_SIZE);

        for (int rowIndex = 0; rowIndex < labyrinth.getHeight(); rowIndex++){
            canvasGridPane.addRow(rowIndex);
            canvasGridPane.getRowConstraints().add(r);
        }

        ColumnConstraints c = new ColumnConstraints();
        c.setPrefWidth(TILE_SIZE);
        c.setMinWidth(TILE_SIZE);
        c.setMaxWidth(TILE_SIZE);

        for (int colIndex = 0; colIndex < labyrinth.getWidth(); colIndex++){
            canvasGridPane.addRow(colIndex);
            canvasGridPane.getColumnConstraints().add(c);
        }

        canvasGridPane.setPadding(new Insets(40, 40, 40, 40));
    }

    private void populateEditorCanvas(){
        canvasGridPane.setAlignment(Pos.CENTER);
        canvasGridPane.setGridLinesVisible(true);
        rootPane.requestLayout();
        for(ArrayList<GameElement> row : labyrinthData){
            int rowIndex = labyrinthData.indexOf(row) + 1;
            System.out.print("row=" + rowIndex);
            for(GameElement tile : row){
                int colIndex = row.indexOf(tile) + 1;
                System.out.println("col=" + colIndex);
                ImageView image = new ImageView(tile.getImage());
                image.prefHeight(TILE_SIZE);
                image.minHeight(TILE_SIZE);
                image.maxHeight(TILE_SIZE);
                image.prefWidth(TILE_SIZE);
                image.minWidth(TILE_SIZE);
                image.maxWidth(TILE_SIZE);

                double scale = TILE_SIZE/32;
                image.setScaleX(scale);
                image.setScaleY(scale);
                canvasGridPane.add(image, colIndex, rowIndex);
            }
        }
    }

    /**
     * Close the application when the exit menu item is clicked.
     */
    public void handleExitMenuItem() {
        Platform.exit();
    }

}
