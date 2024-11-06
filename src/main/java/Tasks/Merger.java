/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tasks;

import Ports.Message;
import Ports.Slot;

/**
 *
 * @author Jtorr
 */
public class Merger  extends Router {

    public Merger(int numReadPorts) {
        super(numReadPorts, 1);
    }


    
    @Override
    protected void run() {
        Slot writeSlot = this.writeList.getFirst();
        
        for(int i=0;i<this.readList.size();i++){
            Slot readSlot = this.readList.get(i);
            
            while(readSlot.isEmpty()==false){
                Message messagePoped = readSlot.popMessage();
                writeSlot.pushMessage(messagePoped);
            }            
        }
    }
    
}