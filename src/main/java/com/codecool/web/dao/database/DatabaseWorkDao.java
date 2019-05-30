package com.codecool.web.dao.database;

import com.codecool.web.dao.WorkDao;
import com.codecool.web.model.Work;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseWorkDao extends AbstractDao implements WorkDao  {

    public DatabaseWorkDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Work> findAllByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM  works AS w" +
            " WHERE w.user_id = ?";

        List<Work> works = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                 works.add(fetchWork(resultSet));
            }
        }
        return works;
    }

    @Override
    public Work findByWorkId(int workId) throws SQLException {
        String sql = "SELECT * FROM works WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, workId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchWork(resultSet);
                }
            }
        }
        return null;
    }

    private Work fetchWork(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        Date date = resultSet.getDate("date");
        LocalDate localDate = date.toLocalDate();
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        return new Work(id,localDate,title,content);
    }
}
