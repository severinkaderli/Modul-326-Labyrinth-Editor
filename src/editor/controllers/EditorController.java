package editor.controllers;

import editor.models.GameElement;
import editor.models.Labyrinth;
import editor.models.Tool;
import editor.utility.LabyrinthExporter;
import editor.utility.LabyrinthImporter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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

    private Tool tool;

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
        labyrinth = LabyrinthImporter.importXML(currentFile);
        labyrinthData = labyrinth.getData();

        //show data canvas
        initializeEditorCanvas();
        populateEditorCanvas();
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
            dialog.setScene(new Scene(newLabyrinthRoot, CANVAS_WIDTH, CANVAS_HEIGHT));
        } catch (IOException ex) {
            System.out.println("I/O-Exception: " + ex.getMessage());
        }
        dialog.setTitle("Neues Labyrinth erstellen");
        dialog.initOwner(rootPane.getScene().getWindow());
        dialog.showAndWait();
    }

    /**
     * Handles saving of a labyrinth ([CTRL] + [S])
     */
    public void handleSaveMenuItem() {
        if (currentFile != null) {
            // TODO: Validate the labyrinth before saving

            // Save the labyrinth in file
            LabyrinthExporter.exportXML(labyrinth, currentFile);
        } else {
            System.out.println("No currentFile");
        }
    }

    private void initializeEditorCanvas() {
        //reset canvas from eventual previous labyrinths
        resetCanvas();

        //make sure we know the current size
        updateCanvasSize();

        TILE_SIZE = (Integer.min(CANVAS_HEIGHT, CANVAS_WIDTH) - MARGIN - MARGIN) / Integer.max(labyrinth.getHeight(), labyrinth.getWidth());


        //set size of the GridView
        RowConstraints r = new RowConstraints();
        r.setPrefHeight(TILE_SIZE);
        r.setMinHeight(TILE_SIZE);
        r.setMaxHeight(TILE_SIZE);

        for (int rowIndex = 0; rowIndex < labyrinth.getHeight(); rowIndex++) {
            canvasGridPane.addRow(rowIndex);
            canvasGridPane.getRowConstraints().add(r);
        }

        ColumnConstraints c = new ColumnConstraints();
        c.setPrefWidth(TILE_SIZE);
        c.setMinWidth(TILE_SIZE);
        c.setMaxWidth(TILE_SIZE);

        for (int colIndex = 0; colIndex < labyrinth.getWidth(); colIndex++) {
            canvasGridPane.addRow(colIndex);
            canvasGridPane.getColumnConstraints().add(c);
        }

        //canvasGridPane.setPadding(new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
    }

    private void resetCanvas() {
        canvasGridPane.getChildren().clear();
        canvasGridPane.getRowConstraints().clear();
        canvasGridPane.getColumnConstraints().clear();
    }

    private void checkWhereClicked(Point2D click){
        System.out.println("onCanvas=" + canvasGridPane.contains(click) + " x=" + click.getX() + " y=" + click.getY());
    }

    private void populateEditorCanvas() {
        canvasGridPane.setAlignment(Pos.BOTTOM_CENTER);
        canvasGridPane.setGridLinesVisible(true);
        //canvasGridPane.setVgap(3);
        canvasGridPane.setPadding(new Insets(2, 2, 2, 2));
        rootPane.requestLayout();

        for (ArrayList<GameElement> row : labyrinthData) {
            int rowIndex = labyrinthData.indexOf(row);
            for (GameElement tile : row) {
                int colIndex = row.indexOf(tile);

                tile.setColIndex(colIndex);
                tile.setRowIndex(rowIndex);



                tile.onMouseClickedProperty().setValue(event -> {
                    
                    System.out.printf("type=%s x=%d y=%d%n",tile.getType(), tile.getColIndex(), tile.getRowIndex());
                });

                //TODO make images a usable size

                canvasGridPane.add(tile, colIndex, rowIndex);
            }
        }
    }

    public void handleWallToolSelected(){
        this.tool = Tool.WALL;
    }

    public void handleDestructableToolSelected(){
        this.tool = Tool.DESTRUCTABLE;
    }

    public void handleFloorToolSelected(){
        this.tool = Tool.FLOOR;
    }

    public void handleSpawnpointToolSelected(){
        this.tool = Tool.SPAWNPOINT;
    }

    /**
     * Close the application when the exit menu item is clicked.
     */
    public void handleExitMenuItem() {
        Platform.exit();
    }

}
