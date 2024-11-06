/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tasks;


import Ports.Message;
import Ports.Slot;

/**
 * Clona el mesaje de entrada y se los manda a todas sus salidas (outputs)
 * 
 * @author Jtorr
 */
public class Replicator  extends Router{

    public Replicator(int numReadPorts, int numWritePorts) {
        super(1, numWritePorts);
    }

    @Override
    protected void run() {
        
        Slot readSlot = this.readList.getFirst();
        
        while (readSlot.isEmpty() == false){
            Message messageToReplicate= readSlot.popMessage();
                
            for(int j=0;j<this.writeList.size();j++){
                Slot writeSlotX = this.writeList.get(j);
                writeSlotX.pushMessage(messageToReplicate.cloneMessage());
            }
            
        }
       
    }
    
}
