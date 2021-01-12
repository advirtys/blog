package com.mycompany.blog.dao;

import java.util.List;

public interface AbstractDAO<T> {
    List<T> getAll();

    T getById(int id);
}
