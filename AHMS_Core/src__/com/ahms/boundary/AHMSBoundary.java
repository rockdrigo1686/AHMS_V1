/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary;

import java.util.List;

/**
 *
 * @author rsoto
 * @param <T>
 */
public interface AHMSBoundary<T> {
    
    public List<T> search(T obj);
    public List<T> searchAll(T obj);
    public T find(T obj);
    public int insert(T obj);
    public int update(T obj);
    public int delete(T obj);    
    
    
}
