package editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/editor.fxml"));
        primaryStage.setTitle("Labyrinth Editor");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        //to hell with user friendliness, responsive design is hard.
        primaryStage.setMaxHeight(HEIGHT);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMinWidth(WIDTH);
        //TODO make more user friendly, this is not a top priority however.
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
