package eas.com.dao;

import java.util.List;

/**
 * Created by eduardo on 11/8/2016.
 */
public interface InterfaceDao<T> {

    void insert(T t) throws Exception;

    void update(T t) throws Exception;

    void delete(T t) throws Exception;

    void delete(long id) throws Exception;

    T get(Long id) throws Exception;

    List<T> getAll() throws Exception;



}
