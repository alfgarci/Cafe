/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tasks;


import Ports.Message;
import Ports.Slot;



/**
 * Por defecto, el criterio que se va a seguir es el de escoger el slot cuyo 'id' sea el mismo que el valor de un atributo de un mensaje, cuya 'key' se para en el constructor del Distributor
 * 
 * NOTA: Sigue quedando por implementar 'nameWriteSlots-method' que nombra los 'Slot's de salida con un id relativo al valor resultante de aplicar la key 'distributorKey' sobre el diccionario de un mensaje. [EJ] tipoBebida = frio/caliente
 * 
 * @author Jtorr
 */
public abstract class Distributor extends Router {

    private final String distributorKey;
    
    
    public Distributor(int numWritePorts, String distributorKey) {
        super(1, numWritePorts);
        this.distributorKey = distributorKey;
        
        this.nameWriteSlots();
    }

    @Override
    protected void run() {
        Slot readSlot = this.readList.getFirst();
        
        while(readSlot.isEmpty() == false){
            Message msgX = readSlot.popMessage();
            String tipo = msgX.getDataDict().get(distributorKey); // [EJ] 'tipoBebida'
            
            // Esto puede devolver null. TODO: controlar el error con un mensaje o algo...
            Slot writeSlot = super.getWriteSlotById(tipo);
            writeSlot.pushMessage(msgX);
        }
    }
    
    
    protected abstract void nameWriteSlots();
    
    
}
