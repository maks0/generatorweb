/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.*;

/**
 *
 * @author maks
 * @param <T>
 */
public class TypedContainer <T> implements Container <T> {
    private List <T> records;

    public TypedContainer() {
        records = new ArrayList<T>();
    }

    public TypedContainer(List<T> records) {
        this.records = new ArrayList<T>(records);
    }
    
    @Override
    public List <T> getRecordsList(){
        return new ArrayList<T>(records);
    } 
    
    @Override
    public void setRecordsList (List <T> records){
        this.records = new ArrayList<T>(records);
    }

    @Override
    public void add(T record) {
       records.add(record);
    }

    @Override
    public void add(List<T> records) {
       this.records.addAll(records);
    }
}
