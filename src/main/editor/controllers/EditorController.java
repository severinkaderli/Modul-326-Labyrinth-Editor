package editor.controllers;

import editor.models.GameElement;
import editor.models.Labyrinth;
import editor.models.Type;
import editor.utility.GameElementFactory;
import editor.utility.LabyrinthExporter;
import editor.utility.LabyrinthImporter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private AnchorPane canvasAnchorPane;

    private Type selected_tool  = Type.FLOOR;

    //tile size, depends on the size of the labyrinth
    private int TILE_SIZE = 40;

    private int CANVAS_HEIGHT = 600;    //probable size, but may be adjusted
    private int CANVAS_WIDTH = 800;     //ditto
    private final int MARGIN = 40;

    private void updateCanvasSize() {
        CANVAS_HEIGHT = (int) canvasAnchorPane.getHeight();
        CANVAS_WIDTH = (int) canvasAnchorPane.getWidth();
    }

    /**
     * updates the contents of the editor
     */
    private void updateEditorCanvas() {
        //show data canvas
        initializeEditorCanvas();
        populateEditorCanvas();
    }

    /**
     * Handles opening an existing labyrinth ([CTRL] + [O])
     */
    public void handleOpenMenuItem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open labyrinth file");

        // Configure the file chooser so only xml files will be shown
        FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML-Files", "*.xml");
        fileChooser.getExtensionFilters().add(xmlFilter);

        // Import the xml file
        currentFile = fileChooser.showOpenDialog(rootPane.getScene().getWindow());

        // Check if a file was selected
        if(currentFile != null) {
            labyrinth = LabyrinthImporter.importXML(currentFile);
            labyrinthData = labyrinth.getData();
            updateEditorCanvas();
        }

    }


    /**
     * Handles creation of a new labyrinth ([CTRL] + [N])
     */
    public void handleNewMenuItem() {
        //initialise a dialog
        final Stage dialog = new Stage(StageStyle.TRANSPARENT);
        dialog.initModality(Modality.WINDOW_MODAL);
        try {
            Parent newLabyrinthRoot = FXMLLoader.load(getClass().getResource("/fxml/newLabyrinth.fxml"));
            dialog.setScene(new Scene(newLabyrinthRoot, 400, 250));
        } catch (IOException ex) {
            System.out.println("I/O-Exception: " + ex.getMessage());
        }
        dialog.setTitle("Neues Labyrinth erstellen");
        dialog.initOwner(rootPane.getScene().getWindow());
        dialog.showAndWait();
        currentFile = (File)dialog.getUserData();
        this.labyrinth = LabyrinthImporter.importXML((File)dialog.getUserData());
        this.labyrinthData = labyrinth.getData();

        updateEditorCanvas();
    }

    /**
     * Handles saving of a labyrinth ([CTRL] + [S])
     */
    public void handleSaveMenuItem() {
        if (currentFile != null) {

            // Validate that the labyrinth has exactly four spawn points
            if(labyrinth.getNumberOfSpawnpoints() != 4) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("A labyrinth needs exactly four spawnpoints!");
                alert.setHeaderText("A labyrinth needs exactly four spawnpoints!");
                alert.showAndWait();
            } else {
                // Save the labyrinth in file
                LabyrinthExporter.exportXML(labyrinth, currentFile);
            }


        } else {
            System.out.println("No currentFile");
        }
    }

    private void initializeEditorCanvas() {
        //reset canvas from eventual previous labyrinths
        resetCanvas();

        //make sure we know the current size
        updateCanvasSize();

        //calculate the TILE_SIZE appropriate for the window size and size of the Labyrinth
        int smallerCanvasDimension = Integer.min(CANVAS_HEIGHT, CANVAS_WIDTH) - 2 * MARGIN;
        int largerLabyrinthDimension = Integer.max(labyrinth.getHeight(), labyrinth.getWidth());

        TILE_SIZE = smallerCanvasDimension / largerLabyrinthDimension;


        //set size of the GridView
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPrefHeight(TILE_SIZE);
        rowConstraints.setMinHeight(TILE_SIZE);
        rowConstraints.setMaxHeight(TILE_SIZE);

        for (int rowIndex = 0; rowIndex < labyrinth.getHeight(); rowIndex++) {
            canvasGridPane.addRow(rowIndex);
            canvasGridPane.getRowConstraints().add(rowConstraints);
        }

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(TILE_SIZE);
        columnConstraints.setMinWidth(TILE_SIZE);
        columnConstraints.setMaxWidth(TILE_SIZE);

        for (int colIndex = 0; colIndex < labyrinth.getWidth(); colIndex++) {
            canvasGridPane.addRow(colIndex);
            canvasGridPane.getColumnConstraints().add(columnConstraints);
        }
    }

    private void resetCanvas() {
        canvasGridPane.getChildren().clear();
        canvasGridPane.getRowConstraints().clear();
        canvasGridPane.getColumnConstraints().clear();
    }

    private void populateEditorCanvas() {
        canvasGridPane.setAlignment(Pos.BOTTOM_CENTER);
        int padding = 2;
        canvasGridPane.setPadding(new Insets(padding, padding, padding, padding));
        rootPane.requestLayout();

        for (ArrayList<GameElement> row : labyrinthData) {
            int rowIndex = labyrinthData.indexOf(row);
            for (GameElement tile : row) {
                int colIndex = row.indexOf(tile);

                tile.setColIndex(colIndex);
                tile.setRowIndex(rowIndex);

                tile.fitWidthProperty().bind(canvasGridPane.widthProperty().divide(labyrinth.getWidth()));
                tile.fitHeightProperty().bind(canvasGridPane.heightProperty().divide(labyrinth.getHeight()));

                //handle the user clicking a tile
                tile.onMouseClickedProperty().setValue(event -> handleTileClicked(tile));

                canvasGridPane.add(tile, colIndex, rowIndex);
            }
        }
    }

    private void handleTileClicked(GameElement tile){
        //create new GameElement at the same position
        GameElement updatedTile = GameElementFactory.createGameElement(selected_tool, tile.getColIndex(), tile.getRowIndex());

        //put that GameElement into the appropriate spot in the Labyrinth Data
        labyrinthData.get(updatedTile.getRowIndex()).set(updatedTile.getColIndex(), updatedTile);

        updateEditorCanvas(); //redraw the canvas
    }

    public void handleWallToolSelected() {
        this.selected_tool = Type.WALL;
    }

    public void handleDestructableToolSelected() {
        this.selected_tool = Type.DESTRUCTABLE;
    }

    public void handleFloorToolSelected() {
        this.selected_tool = Type.FLOOR;
    }

    public void handleSpawnpointToolSelected() {
        this.selected_tool = Type.SPAWNPOINT;
    }

    /**
     * Close the application when the exit menu item is clicked.
     */
    public void handleExitMenuItem() {
        Platform.exit();
    }

}
