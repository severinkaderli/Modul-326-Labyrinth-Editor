package editor.models;

import com.sun.scenario.effect.impl.ImagePool;
import editor.utility.ImageProvider;
import javafx.scene.image.Image;

/**
 * Created by severin on 11/16/16.
 */
public class SpawnPoint extends GameElement {
    private static final String TYPE = "spawnpoint";

    public String getType(){
        return TYPE;
    }

    @Override
    public Image getIcon() {
        return ImageProvider.getSpawnImage();
    }
}
