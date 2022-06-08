import model.Address;
import model.Hotel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
   private static String key;
   private static Node currentItem;
   private static Matcher matcher;
   private static Hotel hotel = null;
   private static NodeList addres1;
   private static Address address = null;
   private static List<Hotel> hotelList11 = new ArrayList<>();


   private static List<String> PriceList = new ArrayList<>();

    public static void main(String[] args) throws XPathExpressionException {
        Document document = null;

        try {
            document = DocumentBuilder();
        } catch (Exception e) {
            System.out.println("Ошибка создания документа при считывании" + e.toString());
        }
        Node nodeHotel = document.getFirstChild();
        NodeList Roothotel = nodeHotel.getChildNodes();
        //Price
        getingPrice(Roothotel, document);
        for (int i = 0; i < Roothotel.getLength(); i++) {
            if (Roothotel.item(i).getNodeType() != Node.ELEMENT_NODE ) {
                continue;
            }
            hotel = new Hotel();
            if (i%2!=0){
                hotel.setPrice(PriceList.get((i/2)));
            }

            NodeList nodeList =Roothotel.item(i).getChildNodes();
            for (int j=0; j< nodeList.getLength();j++){
                if (nodeList.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                address = new Address();
                switch (nodeList.item(j).getNodeName()){
                    case "Name":{
                        hotel.setName(nodeList.item(j).getTextContent());
                        break;
                    }
                    case "Address":{
                        addres1 = nodeList.item(j).getChildNodes();
                        for (int l=0;l<addres1.getLength();l++){
                            if (addres1.item(l).getNodeType() != Node.ELEMENT_NODE) {
                                continue;
                            }
                            switch (addres1.item(l).getNodeName()){
                                case "AddressLine":
                                    address.setAddressLine(addres1.item(l).getTextContent());
                                 break;
                                case "City":
                                    address.setCity(addres1.item(l).getTextContent());
                                 break;
                                case "Country":
                                    address.setCountry(addres1.item(l).getTextContent());
                                 break;
                                case "State":
                                    address.setState(addres1.item(l).getTextContent());
                                 break;
                            }
                        }
                        hotel.setAddress(address);

                    } break;
                }
            }
            Pattern pattern = Pattern.compile(".*" + "Hilton" + ".*");
            matcher = pattern.matcher(hotel.getName());
            if ((hotel.getAddress().getState().contains("New York") || hotel.getAddress().getState().contains("NY")) && matcher.find()){
                hotelList11.add(hotel);

            }
        }
        XMLjdomWriter xmLjdomWriter = new XMLjdomWriter(hotelList11);
        xmLjdomWriter.saveList();
    }

    private static Document DocumentBuilder() throws Exception {
        File file = new File("Hotels.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }

    private static void getingPrice(NodeList Roothotel, Document document) throws XPathExpressionException {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//Hotels/Hotel[@Price]");
        NodeList nl = null;
        for (int i=0;i<Roothotel.getLength();i++){
            nl = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
            try {
                currentItem = nl.item(i);
                key = currentItem.getAttributes().getNamedItem("Price").getNodeValue();
                PriceList.add(key);
            }catch (Exception e){
            }
        }
    }
}



