/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author Hooman
 */
public abstract class SearchStructure <T,E>
{

    abstract public boolean insert(T key,E data);
    abstract public boolean delete(T key);// return success
    abstract public E search(T key);// return data
    abstract public void print();
} 
