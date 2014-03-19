package dao.word;

import java.sql.SQLException;

import domain.word.Word;

public interface WordDAO {
    void create(Word word) throws SQLException;

    Word read(Word word) throws SQLException;

    void update(Word Word) throws SQLException;

    void delete(Word word) throws SQLException;
}
