package editor.model;

/**
 * Created by severin on 11/16/16.
 */
public class Wall extends GameElement {
    private static final String TYPE = "wall";

    public String getType(){
        return this.getClass().TYPE;
    }
}
