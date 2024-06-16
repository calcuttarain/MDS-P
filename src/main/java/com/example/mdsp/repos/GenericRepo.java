package com.example.mdsp.repos;


import com.example.mdsp.exceptions.ElementNotFoundException;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public interface GenericRepo <T>{
    public void add(T entity);//c

    public T get(int id) throws ElementNotFoundException;//r

    public void update(T entity);//u

    public void delete(int id);//d
}
