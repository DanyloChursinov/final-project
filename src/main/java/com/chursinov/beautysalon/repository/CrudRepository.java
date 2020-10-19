package com.chursinov.beautysalon.repository;

import java.sql.SQLException;

public interface CrudRepository<T, E> {
    long count(String sql) throws SQLException, InterruptedException;
    void deleteById(int id, String sql) throws InterruptedException;
    boolean existById(int id, String sql) throws InterruptedException;
}
