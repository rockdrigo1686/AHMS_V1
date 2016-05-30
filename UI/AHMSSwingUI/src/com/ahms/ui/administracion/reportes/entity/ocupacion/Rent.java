/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.administracion.reportes.entity.ocupacion;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author rsoto
 */
public class Rent {

    List<Room> room;

    public List<Room> getRoom() {
        return room;
    }

    @XmlElement
    public void setRoom(List<Room> room) {
        this.room = room;
    }

}
