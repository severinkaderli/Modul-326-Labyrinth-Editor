package editor.utility;

import editor.models.GameElement;
import editor.models.Labyrinth;
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
 * This class is used to export labyrinths to different formats, e.g. a xml file.
 */
public class LabyrinthExporter {

    /**
     * Export the labyrinth as a xml file.
     *
     * @param labyrinth The labyrinth which will be exported
     * @param xmlFile The File where the xml will be written to.
     * @return The created xml file
     */
    public static void exportXML(Labyrinth labyrinth, File xmlFile) {
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
            width.appendChild(document.createTextNode(String.valueOf(labyrinth.getWidth())));
            Element height = document.createElement("height");
            height.appendChild(document.createTextNode(String.valueOf(labyrinth.getHeight())));
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(String.valueOf(labyrinth.getName())));

            meta.appendChild(width);
            meta.appendChild(height);
            meta.appendChild(name);

            // Labyrinth data
            Element dataNode = document.createElement("data");
            root.appendChild(dataNode);

            for(ArrayList<GameElement> row : labyrinth.getData()) {
                Element rowNode = document.createElement("row");
                dataNode.appendChild(rowNode);
                for(GameElement tile : row) {
                    Element fieldNode = document.createElement("field");
                    Attr typeAttribute = document.createAttribute("type");
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
            transformer.transform(new DOMSource(document), new StreamResult(xmlFile));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
