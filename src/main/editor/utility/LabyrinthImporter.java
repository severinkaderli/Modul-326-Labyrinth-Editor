package editor.utility;

import editor.models.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.management.InvalidAttributeValueException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * This class is used to import a labyrinth from different sources, e.g. a xml file.
 *
 * @author Severin Kaderli
 */
 */
public class LabyrinthImporter {

    /**
     * Create a new labyrinth from a xml file
     *
     * @param xmlFile The xml file with the labyrinth data
     * @return The labyrinth created from the xml
     */
    public static Labyrinth importXML(File xmlFile) {
        Labyrinth labyrinth = new Labyrinth();
        ArrayList<ArrayList<GameElement>> data = new ArrayList<>();

        try {
            // Parse the xml and create the document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Get the root element and the meta data for the labyrinth
            Element rootElement = document.getDocumentElement();
            Element metaElement = (Element) rootElement.getElementsByTagName("meta").item(0);
            labyrinth.setWidth(Integer.parseInt(metaElement.getElementsByTagName("width").item(0).getTextContent()));
            labyrinth.setHeight(Integer.parseInt(metaElement.getElementsByTagName("height").item(0).getTextContent()));
            labyrinth.setName(metaElement.getElementsByTagName("name").item(0).getTextContent());

            // Get the rows as a data list
            Element dataElement = (Element) rootElement.getElementsByTagName("data").item(0);
            NodeList dataRows = dataElement.getElementsByTagName("row");

            // Loop through all rows and get the game elements from the data
            // add it to the labyrinth
            for(int i = 0; i < dataRows.getLength(); i++) {
                ArrayList<GameElement> row = new ArrayList<>();

                // Get fields from the rows
                Element rowNode = (Element) dataRows.item(i);
                NodeList fields = rowNode.getElementsByTagName("field");
                for(int j = 0; j < fields.getLength(); j++) {

                    String gameElement = fields.item(j).getAttributes().getNamedItem("type").getNodeValue();
                    row.add(getGameElementByName(gameElement));
                }
                data.add(row);
            }
            labyrinth.setData(data);

            return labyrinth;

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return labyrinth;
    }

    /**
     * This function returns an instance of the game element, specified by
     * the name.
     *
     * @param name The name of the game element
     * @return An instance of the needed game element
     */
    private static GameElement getGameElementByName(String name) throws InvalidAttributeValueException {
        switch (name) {
            case "wall":
                return new Wall();
            case "spawnpoint":
                return new SpawnPoint();
            case "floor":
                return new Floor();
            case "destructablewall":
                return new Wall(true);
            default:
                throw new InvalidAttributeValueException(name + " is not a valid game element.");
        }
    }
}
