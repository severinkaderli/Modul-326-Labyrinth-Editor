package editor.models;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representation of a labyrinth
 */
public class Labyrinth {

    public static void main(String[] args){
        Wall trump = new Wall();
        System.out.println(trump.getType());
    }

    /**
     * The width of the labyrinth in tiles.
     */
    private int width;

    /**
     * The height of the labyrinth in tiles.
     */
    private int height;

    /**
     * The name of the labyrinth.
     */
    private String name;

    /**
     * The data of the labyrinth
     */
    private ArrayList<ArrayList<GameElement>> data = new ArrayList<>();

    /**
     * Creates a new labyrinth.
     */
    public Labyrinth() {
    }

    /**
     * Creates a new labyrinth.
     *
     * @param width  Width of the labyrinth in tiles
     * @param height Height of the labyrinth in tiles
     * @param name   The name of the labyrinth
     */
    public Labyrinth(int width, int height, String name) {
        setWidth(width);
        setHeight(height);
        setName(name);
    }

    /**
     * Sets the width
     *
     * @param width The width of the labyrinth in tiles
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get the width of the labyrinth
     *
     * @return The width of the labyrinth
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the height
     *
     * @param height The height of the labyrinth in tiles
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get the height of the labyrinth
     *
     * @return The height of the labyrinth
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the name of the labyrinth
     *
     * @param name The name for the labyrinth
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the labyrinth
     *
     * @return The name of the labyrinth
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the data
     *
     * @param data The labyrinth data
     */
    public void setData(ArrayList<ArrayList<GameElement>> data) {
        this.data = data;
    }

    /**
     * Get the labyrinth data.
     *
     * @return The data of the labyrinth
     */
    public ArrayList<ArrayList<GameElement>> getData() {
        return data;
    }

    /**
     * Method that takes width and height to initialize the data array
     * @param width
     * @param height
     * @return
     */
    public static ArrayList<ArrayList<GameElement>> initalizeData(int width, int height){
        ArrayList<ArrayList<GameElement>> outerList = new ArrayList<>(height);
        for(List<GameElement> row : outerList){
            row = new ArrayList<GameElement>(width);
            for(GameElement g : row){
                g = new Air();
            }
        }

        return outerList;
    }
}