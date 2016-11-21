package editor;

import editor.model.Labyrinth;
import editor.utility.LabyrinthExporter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/editor.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Labyrinth lab = new Labyrinth(30, 30, "Test-Labyrinth");
        LabyrinthExporter.exportXML(lab);
        //launch(args);
    }
}
