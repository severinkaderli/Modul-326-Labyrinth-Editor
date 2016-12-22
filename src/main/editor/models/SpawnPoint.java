package editor.models;

import editor.utility.ImageProvider;
import javafx.scene.image.Image;

/**
 * Created by severin on 11/16/16.
 */
public class SpawnPoint extends GameElement {
    private static final Type TYPE = Type.SPAWNPOINT;

    public String getType(){
        return TYPE.getVal();
    }

    @Override
    public Image getIcon() {
        return ImageProvider.getSpawnImage();
    }
}
