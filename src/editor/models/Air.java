package editor.models;

import editor.utility.ImageProvider;
import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;

/**
 * Created by marius on 11/16/16.
 */
public class Air extends GameElement {
    private static final String TYPE = "air";

    public String getType(){
        return this.TYPE;
    }

    @Override
    public Image getImage() {
        return ImageProvider.getFloorImage();
    }


}