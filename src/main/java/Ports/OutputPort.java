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
public class OutputPort {

    private Slot output;

    public void writeSlotOutput(Document elemento) throws Exception {
        output.write(elemento);
    }

    public void doWork(Document elemento) throws Exception {
        this.writeSlotOutput(elemento);
    }
}