package editor.model;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * The class representation of a labyrinth
 */
public class Labyrinth {

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

        ArrayList<GameElement> temp = new ArrayList<>();
        temp.add(new Wall());
        temp.add(new Wall());
        data.add(temp);
    }

    /**
     * Create a new labyrinth from a xml file
     *
     * @param xml The xml file with the labyrinth data
     * @return The labyrinth created from the xml
     */
    public static Labyrinth createFromXML(File xml) {
        Labyrinth labyrinth = new Labyrinth();

        // TODO: Parse the xml and get the meta information
        // labyrinth.setWidth();
        // labyrinth.setHeight();
        // labyrinth.setName();

        // TODO: Get the labyrinth data from the file
        ArrayList<ArrayList<GameElement>> data = new ArrayList<>();

        return labyrinth;
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
     * Print out information of the labyrinth to the console.
     */
    public void debug() {
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("Name: " + name);
    }
}
