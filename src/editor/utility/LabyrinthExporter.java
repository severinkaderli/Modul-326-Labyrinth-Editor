package editor.utility;

import editor.model.GameElement;
import editor.model.Labyrinth;
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
import java.util.ArrayList;

/**
 * Created by severin on 11/21/16.
 */
public class LabyrinthExporter {

    /**
     *
     * @return
     */
    public static String exportXML(Labyrinth labyrinth) {
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
}
