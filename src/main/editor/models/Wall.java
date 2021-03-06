package editor.models;

import editor.utility.ImageProvider;
import javafx.scene.image.Image;

/**
 * Created by severin on 11/16/16.
 */
public class Wall extends GameElement {
    private static final Type TYPE = Type.WALL;
    private static final Type DESTRUCTABLE_TYPE = Type.DESTRUCTABLE;
    private boolean destructable;

    public Wall(boolean destructable){
        this.destructable = destructable;
        super.updateIcon();
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
        if(destructable){
            return DESTRUCTABLE_TYPE.getVal();
        }else {
            return TYPE.getVal();
        }
    }

    @Override
    public Image getIcon() {
        if(destructable) {
            return ImageProvider.getDestructableImage();
        }else{
            return ImageProvider.getWallImage();
        }
    }


}