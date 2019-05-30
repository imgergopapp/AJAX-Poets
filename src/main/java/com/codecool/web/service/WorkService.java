package com.codecool.web.service;

import com.codecool.web.model.Work;

import java.sql.SQLException;
import java.util.List;

public interface WorkService {
    List<Work> findAllByUserId(int userId) throws SQLException;

    Work findByWorkId(int workId) throws SQLException;

    int countRegexOccurrences(String regex, int workId) throws SQLException;

}
