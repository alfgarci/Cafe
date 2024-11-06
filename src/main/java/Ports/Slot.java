/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ports;

import java.util.LinkedList;
import java.util.Queue;
import org.w3c.dom.Document;

/**
 *
 * @author alfgarci
 */  
public class Slot {

    private final Queue<Document> testQueue = new LinkedList();

    public Queue getQueue() {
        return testQueue;
    }

    public Document read() throws Exception {
        return testQueue.remove();
    }

    public void write(Document doc) throws Exception {
        testQueue.add(doc);
    }
}