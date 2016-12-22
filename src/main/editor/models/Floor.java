package editor.models;

import editor.utility.ImageProvider;
import javafx.scene.image.Image;

/**
 * Created by marius on 11/16/16.
 */
public class Floor extends GameElement {
    private static Type TYPE = Type.FLOOR;

    public String getType(){
        return TYPE.getVal();
    }

    @Override
    public Image getIcon() {
        return ImageProvider.getFloorImage();
    }


}
