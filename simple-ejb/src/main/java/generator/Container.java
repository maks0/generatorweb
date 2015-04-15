/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.List;

/**
 *
 * @author maks
 */
public interface Container <T> {
    
    List <T> getRecordsList();
    void setRecordsList (List <T> records);
    void add(T record);
    void add(List <T> records);
    
}
