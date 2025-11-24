package edu.ccrm.service;
import java.util.List;


public interface Searchable<T> 
{
    List<T> searchByQuery(String query);
}
