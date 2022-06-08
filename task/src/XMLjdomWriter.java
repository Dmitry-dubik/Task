import model.Hotel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLjdomWriter {
    private static Element box = null;
    private static List<Hotel> hotels;

    public XMLjdomWriter(List<Hotel> _hotels) {
        hotels = _hotels;
    }
    public void saveList(){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("List");
            Element namesElement = doc.createElement("Names");
            Element priceElement = doc.createElement("Prices");
            Element addresesElement = doc.createElement("Addreses");
            rootElement.appendChild(namesElement);
            rootElement.appendChild(priceElement);
            rootElement.appendChild(addresesElement);
            setInformation(doc, namesElement, priceElement, addresesElement);
            doc.appendChild(rootElement);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("Result.xml"));
            transformer.transform(source, console);
            transformer.transform(source, file);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    private static void setInformation(Document document, Element namesElement, Element priceElement, Element addresesElement){
        for (int i=0;i<hotels.size();i++){
            box = document.createElement("Name");
            box.appendChild(document.createTextNode(hotels.get(i).getName()));
            namesElement.appendChild(box);
            box = null;
            box = document.createElement("Price");
            box.appendChild(document.createTextNode(hotels.get(i).getPrice()));
            priceElement.appendChild(box);
            box = null;
            box = document.createElement("Addres");
            box.appendChild(document.createTextNode(hotels.get(i).getAddress().toString()));
            addresesElement.appendChild(box);
        }
    }
    }

