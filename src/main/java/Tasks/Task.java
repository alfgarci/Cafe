/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tasks;

import java.util.ArrayList;

/**
 *
 * @author Jtorr
 */
public abstract class Task {
    
    protected ArrayList<Slot> writeList =  new ArrayList<>();
    protected ArrayList<Slot> readList = new ArrayList<>();
    
    public Task(int numReadPorts, int numWritePorts){
        
        // Se istancia el numero de outputs(writePorts)/inputs(readPorts)
        for(int i=0;i<numWritePorts;i++){
            this.writeList.add(new Slot());
        }
        for(int i=0;i<numReadPorts;i++){
            this.readList.add(new Slot());
        }

        // Bucle infinito
         while(true){
            this.run();
        }
    }
    
   
    public Slot getReadSlotById(String id){
        Slot foundSlot = null;
        for(int i=0;i<this.readList.size();i++){
            Slot slotX = this.readList.get(i);
            if(slotX.getId().equals(id)){
                foundSlot = slotX;
                break;
            }
        }
        
        return foundSlot;
    }    
    public Slot getWriteSlotById(String id){
        Slot foundSlot = null;
        for(int i=0;i<this.readList.size();i++){
            Slot slotX = this.readList.get(i);
            if(slotX.getId().equals(id)){
                foundSlot = slotX;
                break;
            }
        }
        
        return foundSlot;
    }
  
    
    
    protected abstract void run();
    
    
    
}
