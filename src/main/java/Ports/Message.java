/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ports;

import org.w3c.dom.Document;
/**
 *
 * @author alfgarci
 */
public class Message {

    private Document metadata;
    private Document body;

    public Message(Document metadata, Document body) {
        if (metadata == null || body == null) {
            throw new IllegalArgumentException("Metadata and body must not be null");
        }
        this.metadata = metadata;
        this.body = body;
    }

    public Document getMetadata() {
        return metadata;
    }

    public Document getBody() {
        return body;
    }

    public void setMetadata(Document metadata) {
        this.metadata = metadata;
    }

    public void setBody(Document body) {
        this.body = body;
    }
}