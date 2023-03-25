package com.example.salmaan.helpers;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface Repo<T> {
    void insert(T t) throws SQLException;

    void update(T t) throws SQLException;

}
