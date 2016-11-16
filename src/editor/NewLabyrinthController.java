package editor;

import javafx.application.Platform;

public class NewLabyrinthController {

    /**
     * Close the application when the exit menu item is clicked.
     */
    public void handleExitMenuItem() {
        Platform.exit();
    }
}
