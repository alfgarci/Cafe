/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Tasks;

/**
 *
 * @author alfgarci
 */
import Ports.Message;
import Ports.Slot;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Splitter extends Task {

    // Constructor
    public Splitter(int numReadPorts, int numWritePorts) {
        super(numReadPorts, numWritePorts); // Inicializa los puertos
    }

    @Override
    protected void run() {
        try {
            // Obtiene el primer slot de entrada con casting
            Slot input = (Slot) this.readList.get(0);

            // Verifica si hay mensajes en el slot de entrada
            while (!input.isEmpty()) {
                // Obtiene un mensaje del slot
                Message message = input.popMessage();
                Document doc = message.getBody();

                // Utiliza XPath para encontrar nodos "drinks"
                XPath xPath = XPathFactory.newInstance().newXPath();
                NodeList drinks = (NodeList) xPath.compile("//drinks/*").evaluate(doc, XPathConstants.NODESET);

                // Obtiene el nodo "order_id"
                NodeList order = doc.getElementsByTagName("order_id");
                Node ord = order.item(0);

                // Procesa cada nodo dentro de "drinks"
                for (int i = 0; i < drinks.getLength(); i++) {
                    Document doc2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                    Node drink = drinks.item(i);

                    // Si el nodo es válido, lo copia al nuevo documento
                    if (drink.getNodeType() == Node.ELEMENT_NODE) {
                        Node copyNode = doc2.importNode(drink, true);
                        doc2.appendChild(copyNode);

                        // Añade "order_id" al nuevo documento
                        Node TagOrder = doc2.importNode(ord, true);
                        doc2.getDocumentElement().appendChild(TagOrder);
                    }

                    // Crea un nuevo mensaje con el documento generado
                    Message newMessage = new Message(message.getMetadata(), doc2);

                    // Escribe el mensaje en el primer slot de salida con casting
                    Slot output = (Slot) this.writeList.get(0);
                    output.pushMessage(newMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
