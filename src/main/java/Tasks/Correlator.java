/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tasks;

import java.util.ArrayList;

/**
 * Cuando un 'Correlator' lee de sus dos Slots de lectura, almacena cada mensaje en 
 * un arrayList, a la espera de encontrar la pareja que le falta para mandarlo por sus outputs
 *
 * TODO: falta por implementar un metodo que permita decidir qué mensaje se va por el primer slot cual por el segundo... Puede que cada slot debiera de tener un metadato/id para que cuando se enchufe con una task donde el indice el slot sea importante, poder decidir el flujo correcto.
 * 
 * 
 * @author Jtorr
 */
public class Correlator extends Router {

    
    // Cuando un 'C'
    // Contienen la lista de mensajes leidos (sacados del Slot) que estan pendientes de encontrar tu pareja.
    private ArrayList<Message> pendingInputs_1,pendingInputs_2;
    
    public static final String CORRELATION_ID = "IdCorrelator";
    
    public Correlator() {
        super(2, 2);
        this.pendingInputs_1 = new ArrayList<Message>();
        this.pendingInputs_2 = new ArrayList<Message>();
    }

    
    // Para facilitar comportamientos, vamos a buscar un id de nombre 'IdCorrelator', si coinciden 2 mensajes es que se trata de su pareja
    @Override
    protected void run() {
        
        
        extractAllIncommingMessages();
        boolean salir = false;
        for(int i=0;i<this.pendingInputs_1.size() && salir ==false ;i++){
            Message msg1 = this.pendingInputs_1.get(i);
            String msg1_correlationID = msg1.getDataDict().get(CORRELATION_ID);
            
            for(int j=0;j<this.pendingInputs_2.size() && salir ==false;j++){
                Message msg2 = this.pendingInputs_2.get(j);
                String msg2_correlationID = msg2.getDataDict().get(CORRELATION_ID);
                
                if(msg1_correlationID.equals(msg2_correlationID)){
                    salir = false;
                    this.sendMessages(msg1, msg2);
                }
                
                
            }
        }

    }


    // TODO: aqui habria que determinar por cual slot sale cada mensaje.
    private void sendMessages(Message msg1, Message msg2){
        this.writeList.get(0).pushMessage(msg1);
        this.writeList.get(0).pushMessage(msg2);
    }
    
    
    // Extrae y guarda en 'pendings-Lists' todos los mensajes de sus Slots de lectura
    private void extractAllIncommingMessages(){
        Slot readSlot1 =   this.readList.get(0);
        Slot readSlot2 =   this.readList.get(1);
        
        while(readSlot1.isEmpty()==false){
            this.pendingInputs_1.add(readSlot1.popMessage());
        }
        while(readSlot2.isEmpty()==false){
            this.pendingInputs_2.add(readSlot2.popMessage());
        }

    }
    
   
    /**
     * Lee de dos entradas. Cuando, de cada entrada, hay 2 mensajes relacionados por algun dato/metadato, los pone en una salida a cada uno. Habria que determinar cual se pone se pone en qué salida
     */
    
}
