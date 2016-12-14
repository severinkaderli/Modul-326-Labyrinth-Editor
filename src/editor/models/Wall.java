package editor.models;

import editor.utility.ImageProvider;
import javafx.scene.image.Image;

/**
 * Created by severin on 11/16/16.
 */
public class Wall extends GameElement {
    private static final String TYPE = "wall";
    private boolean destructable;

    public Wall(boolean destructable){
        this.destructable = destructable;
    }

    /**
     * Provices a default constructor.
     * Is not destructable.
     */
    public Wall(){
        this.destructable = false;
    }

    public void setDestructable(boolean destructable){
        this.destructable = destructable;
    }

    public boolean isDestructable(){
        return this.destructable;
    }

    public String getType(){
        return this.TYPE;
    }

    @Override
    public Image getImage() {
        if(destructable) {
            return ImageProvider.getDestructableImage();
        }else{
            return ImageProvider.getWallImage();
        }
    }


}