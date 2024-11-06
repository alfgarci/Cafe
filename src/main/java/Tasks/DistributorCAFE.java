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

public class DistributorCAFE extends Distributor{
   
    private final String ID_BEBIDA_FRIA = "fria";
    private final String ID_BEBIDA_CALIENTE = "caliente";
    
    private static final String TIPO_BEBIDA_KEY = "tipoBebida";

    public DistributorCAFE(int numWritePorts, String distributorKey) {
        super(2, TIPO_BEBIDA_KEY);
    }

  

    @Override
    protected void nameWriteSlots() {
        this.writeList.get(0).setId(ID_BEBIDA_FRIA);
        this.writeList.get(0).setId(ID_BEBIDA_CALIENTE);
    }
}


  /*
    public DistributorCAFE(int numWritePorts) {
        super(2);
        this.writeList.get(0).setId(ID_BEBIDA_FRIA);
        this.writeList.get(0).setId(ID_BEBIDA_CALIENTE);
    }
    
    
    @Override
    protected void run() {
        Slot readSlot = this.readList.getFirst();
        
        while(readSlot.isEmpty() == false){
            Message msgX = readSlot.popMessage();
            String tipoBebida = msgX.getDataDict().get("tipoBebida");
            
            // Esto puede devolver null. TODO: controlar error con un mensaje o algo...
            Slot writeSlot = super.getWriteSlotById(tipoBebida);
            writeSlot.pushMessage(msgX);
            
            
                
            
        }
        
        

    }
    */