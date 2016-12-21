package editor.utility;

import editor.models.*;
import javafx.scene.image.Image;

/**
 * Created by marius on 20.12.16.
 */
public class GameElementFactory {
    public static GameElement createGameElement(Type type, int colIndex, int rowIndex){
        switch (type){
            case WALL:
                return decorateWithCoordinates(new Wall(), colIndex, rowIndex);
            case DESTRUCTABLE:
                return decorateWithCoordinates(new Wall(true), colIndex, rowIndex);
            case SPAWNPOINT:
                return decorateWithCoordinates(new SpawnPoint(), colIndex, rowIndex);
            default: //default case is Floor
                return decorateWithCoordinates(new Floor(), colIndex, rowIndex);
        }
    }

    private static GameElement decorateWithCoordinates(GameElement gameElement, int colIndex, int rowIndex){
        gameElement.setRowIndex(rowIndex);
        gameElement.setColIndex(colIndex);
        return gameElement;
    }
}
