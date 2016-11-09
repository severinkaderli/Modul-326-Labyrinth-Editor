package editor;

import javafx.application.Platform;

public class EditorController {


    public void handleOpenMenuItem() {
        System.out.println("Open");
    }

    public void handleNewMenuItem() {
        System.out.println("New");
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
