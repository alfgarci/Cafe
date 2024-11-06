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
public class InputPort {

    private Slot input = new Slot();

    public void setinput(Slot input) {
        this.input = input;
    }

    public Slot getInput() {
        return input;
    }

    public void writeSlotInput(Document doc) throws Exception {
        input.write(doc);
    }

    public Document readSlotInput() throws Exception {
        return input.read();
    }
}
