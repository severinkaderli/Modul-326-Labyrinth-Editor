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

    public String toXml() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Root element
            Document document = docBuilder.newDocument();
            Element root = document.createElement("labyrinth");
            document.appendChild(root);

            // Meta information
            Element meta = document.createElement("meta");
            root.appendChild(meta);

            Element width = document.createElement("width");
            width.appendChild(document.createTextNode(String.valueOf(this.width)));
            Element height = document.createElement("height");
            height.appendChild(document.createTextNode(String.valueOf(this.height)));
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(String.valueOf(this.name)));

            meta.appendChild(width);
            meta.appendChild(height);
            meta.appendChild(name);

            // Labyrinth data
            Element dataNode = document.createElement("data");
            root.appendChild(dataNode);

            for(ArrayList<GameElement> row : data) {
                Element rowNode = document.createElement("row");
                dataNode.appendChild(rowNode);
                for(GameElement tile : row) {
                    Element fieldNode = document.createElement("field");
                    Attr typeAttribute = document.createAttribute("type");
                    // TODO: Get correct type from the GameElement
                    typeAttribute.setValue(tile.getType());
                    fieldNode.setAttributeNode(typeAttribute);
                    rowNode.appendChild(fieldNode);
                }
            }

            // Output as string in the console
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(System.out));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        //TODO make it return an actual file.
        return "This is just a test string.";

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
