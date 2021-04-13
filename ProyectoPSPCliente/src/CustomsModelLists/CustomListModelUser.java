package CustomsModelLists;


import Clases.Message;
import Clases.User;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class CustomListModelUser extends AbstractListModel{
    
    private final ArrayList<User> users = new ArrayList<>();

    @Override
    public int getSize() {
        return users.size();    
    }

    @Override
    public Object getElementAt(int index) {
        
        User a = users.get(index);
        
        String devolver = a.getId_user() + "    " + a.stringState();
        
        return devolver;
    }

    public void addUser(User u){
        users.add(u);
        this.fireIntervalAdded(this, getSize(), getSize()+1);
    }
    
    public void deleteUser(int index0){
        users.remove(index0);
        this.fireIntervalRemoved(index0, getSize(), getSize()+1);
    }
    
    public User getUser(int index){
        return users.get(index);
    }
}
