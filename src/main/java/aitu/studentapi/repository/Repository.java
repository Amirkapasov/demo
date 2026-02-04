package aitu.studentapi.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    void save(T entity) throws SQLException;
    List<T> findAll() throws SQLException;
}
