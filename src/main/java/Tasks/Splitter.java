/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tasks;

import Ports.Slot;
import org.w3c.dom.NodeList;

/**
 *
 * @author alfgarci
 */
public class Splitter {
    
    private Slot input;
    private Slot output;
    private NodeList drinks;
    
    public Splitter(Slot input, Slot output) {
        this.input = input;
        this.output = output;
    }
    
    public void run() throws Exception {
        
    }
}
