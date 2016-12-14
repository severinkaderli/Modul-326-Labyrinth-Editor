package editor.models;

import editor.utility.ImageProvider;
import javafx.scene.image.Image;

/**
 * Created by marius on 11/16/16.
 */
public class Floor extends GameElement {
    private static final String TYPE = "air";

    public String getType(){
        return TYPE;
    }

    @Override
    public Image getImage() {
        return ImageProvider.getFloorImage();
    }


}
