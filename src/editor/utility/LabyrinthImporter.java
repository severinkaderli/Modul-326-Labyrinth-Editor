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

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            Element root = document.getDocumentElement();
            Element meta = (Element) root.getElementsByTagName("meta").item(0);
            Element dataElement = (Element) root.getElementsByTagName("data").item(0);
            NodeList rows = dataElement.getElementsByTagName("row");

            labyrinth.setWidth(Integer.parseInt(meta.getElementsByTagName("width").item(0).getTextContent()));
            labyrinth.setHeight(Integer.parseInt(meta.getElementsByTagName("height").item(0).getTextContent()));
            labyrinth.setName(meta.getElementsByTagName("name").item(0).getTextContent());

            ArrayList<ArrayList<GameElement>> data = new ArrayList<>();

            for(int i = 0; i < rows.getLength(); i++) {
                ArrayList<GameElement> row = new ArrayList<>();
                Element rowNode = (Element) rows.item(i);
                NodeList fields = rowNode.getElementsByTagName("field");
                for(int j = 0; j < fields.getLength(); j++) {

                    String fieldType = fields.item(j).getAttributes().getNamedItem("type").getNodeValue();
                    switch (fieldType) {
                        case "wall":
                            row.add(new Wall());
                            break;
                        case "spawnpoint":
                            row.add(new SpawnPoint());


                            break;
                        case "air":
                            row.add(new Floor());
                            break;

                        default:
                            throw new InvalidAttributeValueException(fieldType + " is not a valid game element.");
                    }
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
}
