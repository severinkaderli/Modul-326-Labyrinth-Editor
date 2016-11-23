package editor.model;

/**
 * The class representation of a game element.
 */
public abstract class GameElement {
    private final String TYPE = "gameElement";
    protected abstract String getType();

    public String getType(){
        return this.TYPE;
    }
}
