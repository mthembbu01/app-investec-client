package com.tstcore.appinvestecclient.services;

import java.util.List;

public interface IService<E> {

    List<E> findAll();

    E findOneById(long id);

    E save(E e);

    E update(E e, long id);

    E update(E e);

    void delete(long id);
    void delete(E e);
}
