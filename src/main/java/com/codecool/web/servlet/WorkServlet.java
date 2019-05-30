package com.codecool.web.servlet;

import com.codecool.web.dao.WorkDao;
import com.codecool.web.dao.database.DatabaseWorkDao;
import com.codecool.web.model.Work;
import com.codecool.web.service.WorkService;
import com.codecool.web.service.simple.SimpleWorkService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/work")
public class WorkServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            WorkDao workDao = new DatabaseWorkDao(connection);
            WorkService workService = new SimpleWorkService(workDao);

            int workId = Integer.parseInt(req.getParameter("workId"));
            Work work = workService.findByWorkId(workId);

            sendMessage(resp, HttpServletResponse.SC_OK, work);
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            WorkDao workDao = new DatabaseWorkDao(connection);
            WorkService workService = new SimpleWorkService(workDao);

            String regex = req.getParameter("regex");
            int workId = Integer.parseInt(req.getParameter("workId"));
            int count = workService.countRegexOccurrences(regex, workId);

            sendMessage(resp, HttpServletResponse.SC_OK, count);
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }
}
