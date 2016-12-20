package editor.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The class representation of a game element.
 */
public abstract class GameElement extends ImageView {
    private int rowIndex;
    private int colIndex;

    private final Type TYPE = Type.GENERIC;

    public String getType(){
        return TYPE.getVal();
    }
    public abstract Image getIcon();

    GameElement(){
        this.setImage(this.getIcon());
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    void updateIcon(){
        this.setImage(this.getIcon());
    }
}
