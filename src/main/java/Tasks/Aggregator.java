/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tasks;

import Ports.Slot;
import Ports.Message;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author alfgarci
 */
public class Aggregator extends Task {

    // Constructor
    public Aggregator(int numReadPorts, int numWritePorts) {
        super(numReadPorts, numWritePorts); // Inicializa los puertos
    }

    @Override
    protected void run() {
        try {
            // Crea un nuevo documento para almacenar los datos agregados
            Document aggregatedDocument = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element rootElement = aggregatedDocument.createElement("AggregatedData");
            aggregatedDocument.appendChild(rootElement);

            // Procesa cada slot de entrada
            for (Object obj : readList) {
                Slot input = (Slot) obj; // Casting explícito a Slot

                // Procesa los mensajes en el slot
                while (!input.isEmpty()) {
                    Message message = input.popMessage();
                    Document messageBody = message.getBody();

                    // Agrega los nodos del mensaje al documento agregado
                    Node importedNode = aggregatedDocument.importNode(messageBody.getDocumentElement(), true);
                    rootElement.appendChild(importedNode);
                }
            }

            // Crea un nuevo mensaje con el documento agregado
            Message aggregatedMessage = new Message(aggregatedDocument, aggregatedDocument);

            // Escribe el mensaje combinado en el primer slot de salida
            Slot output = (Slot) writeList.get(0);
            output.pushMessage(aggregatedMessage);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}