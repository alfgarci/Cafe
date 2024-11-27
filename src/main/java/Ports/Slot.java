/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ports;

import java.util.LinkedList;

/**
 *
 * @author Jtorr
 */
public class Slot {
    
    private String id; // Para diferenciar un slot de otro
    
    private LinkedList<Message> colaMensajes = new LinkedList<>();
    
    public Slot(){
        // ??
        this.id =null;
    }
    public void setId(String id){
        if(this.id ==null){
            this.id = id;
        }
    }
    public String getId(){
        return this.id;
    }
    
    public Message popMessage(){
        return this.colaMensajes.poll();
    }
    public void pushMessage(Message msg){
        this.colaMensajes.push(msg);
    }
    public boolean isEmpty(){
        return this.colaMensajes.isEmpty();
    }
    
    
}
