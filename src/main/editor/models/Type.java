package editor.models;

/**
 * Created by marius on 18.12.16.
 */
public enum Type {
    FLOOR("floor"),
    WALL("wall"),
    DESTRUCTABLE("destructablewall"),
    SPAWNPOINT("spawnpoint"),
    GENERIC("gameElement");

    //represents the xml compatible type
    private String val;

    Type(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
