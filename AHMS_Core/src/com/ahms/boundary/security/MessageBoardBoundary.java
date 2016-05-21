/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.MessageBoard;
import com.ahms.model.manager.entity_manager.MessageBoardEM;
import java.util.List;

/**
 *
 * @author rsoto
 */
public class MessageBoardBoundary implements AHMSBoundary<MessageBoard>{
    MessageBoardEM messageBoardEM = null; 

    @Override
    public List<MessageBoard> search(MessageBoard obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MessageBoard> searchAll(MessageBoard obj) {
        messageBoardEM = new MessageBoardEM();
        return messageBoardEM.searchAll(obj);
    }

    @Override
    public MessageBoard find(MessageBoard obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(MessageBoard obj) {
        messageBoardEM = new MessageBoardEM();
        return messageBoardEM.insert(obj);
    }

    @Override
    public int update(MessageBoard obj) {
        messageBoardEM = new MessageBoardEM();
       return messageBoardEM.update(obj);
    }

    @Override
    public int delete(MessageBoard obj) {
        messageBoardEM = new MessageBoardEM();
       return messageBoardEM.delete(obj);
    }
    
}
