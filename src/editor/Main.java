package editor;

import editor.models.Labyrinth;
import editor.utility.LabyrinthImporter;
import editor.utility.PathValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/editor.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);
        Labyrinth lab = LabyrinthImporter.importXML(new File("C:\\Users\\vmadmin\\Documents\\example_labyrinth.xml"));
        PathValidator.validatePath(lab);
    }
}
