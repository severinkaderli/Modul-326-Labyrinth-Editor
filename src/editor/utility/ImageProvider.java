package editor.utility;

import javafx.scene.image.Image;

/**
 * Created by marius on 07.12.16.
 */
public class ImageProvider {
    private static final Image WALL_IMAGE = new Image("/resources/img/wall.png");
    private static final Image DESTRUCTABLE_IMAGE = new Image("/resources/img/destructable.png");
    private static final Image FLOOR_IMAGE = new Image("/resources/img/floor.png");
    private static final Image SPAWN_IMAGE = new Image("/resources/img/spawn.png");

    private static ImageProvider instance = new ImageProvider();

    private ImageProvider(){
        //thou shant instantiate
    }

    private static Image getWallImage(){
        return WALL_IMAGE;
    }

    private static Image getDestructableImage(){
        return DESTRUCTABLE_IMAGE;
    }

    private static Image getFloorImage(){
        return FLOOR_IMAGE;
    }

    private static Image getSpawnImage(){
        return SPAWN_IMAGE;
    }
}
