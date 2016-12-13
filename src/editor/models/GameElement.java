package editor.models;

import javafx.scene.image.Image;

/**
 * The class representation of a game element.
 */
public abstract class GameElement {
    private final String TYPE = "gameElement";

    public String getType(){
        return this.TYPE;
    }
    public abstract Image getImage();
}
