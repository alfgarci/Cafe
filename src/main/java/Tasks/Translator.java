/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tasks;

import Ports.Slot;
import Ports.Message;
import java.io.File;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;

/**
 *
 * @author alfgarci
 */
public class Translator extends Task {

    private String tipo; // Tipo de traducción

    // Constructor
    public Translator(int numReadPorts, int numWritePorts, String tipo) {
        super(numReadPorts, numWritePorts); // Inicializa los puertos
        this.tipo = tipo;
    }

    @Override
    protected void run() {
        try {
            // Obtiene el slot de entrada con casting
            Slot input = (Slot) this.readList.get(0);

            // Procesa mensajes mientras el slot de entrada no esté vacío
            while (!input.isEmpty()) {
                // Obtiene un mensaje del slot de entrada
                Message message = input.popMessage();
                Document metadata = message.getMetadata(); // Obtiene los metadatos del mensaje original

                // Configura el transformador XSLT
                TransformerFactory factory = TransformerFactory.newInstance();
                Source xslt = new StreamSource(new File("transform.xsl"));
                Transformer transformer = factory.newTransformer(xslt);

                // Define archivos de entrada y salida para la transformación
                String inputFile = "inputReplicada" + tipo + "_body.xml";
                String outputFile = "outputReplicadaTranslator" + tipo + ".xml";

                // Realiza la transformacion
                Source text = new StreamSource(new File(inputFile));
                File outputTransformation = new File(outputFile);
                transformer.transform(text, new StreamResult(outputTransformation));

                // Lee el documento transformado
                Document transformedBody = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder()
                        .parse(outputTransformation);

                // Crea un nuevo mensaje con el documento transformado y los metadatos originales
                Message outputMessage = new Message(metadata, transformedBody);

                // Obtiene el slot de salida con casting y escribe el mensaje transformado
                Slot output = (Slot) this.writeList.get(0);
                output.pushMessage(outputMessage);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de excepciones
        }
    }

    // Metodo para convertir un documento a una cadena de texto
    public String convertDocumentToString(Document document) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(document), new StreamResult(sw));
            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al convertir el Document a String";
        }
    }
}
