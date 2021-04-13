/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomsModelLists;

import Clases.Message;
import Clases.User;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Usuario
 */
public class CustomListModelMessage extends AbstractListModel{
    
    private final ArrayList<Message> messages = new ArrayList<>();

    @Override
    public int getSize() {
        return messages.size();    
    }

    @Override
    public Object getElementAt(int index) {
        
        Message a = messages.get(index);
        String s = "";
        String r = "";
        
        if(a.getSent() == true){
            s = "✓";
        }else{
            s = "";
        }
        
        if(a.getRead() == true){
            r = "✓";
        }else{
            r = "";
        }
        
        String devolver = a.getUser_orig() + ": " + a.getText() + " " + s + r;
        
        return devolver;
    }

    public void addMessage(Message u){
        messages.add(u);
        this.fireIntervalAdded(this, getSize(), getSize()+1);
    }
    
    public void deleteMessage(int index0){
        messages.remove(index0);
        this.fireIntervalRemoved(index0, getSize(), getSize()+1);
    }
    
    public Message getMessage(int index){
        return messages.get(index);
    }    
}
