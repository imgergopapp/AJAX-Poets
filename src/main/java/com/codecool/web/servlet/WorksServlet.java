package com.codecool.web.servlet;

import com.codecool.web.dao.WorkDao;
import com.codecool.web.dao.database.DatabaseWorkDao;
import com.codecool.web.model.User;
import com.codecool.web.model.Work;
import com.codecool.web.service.WorkService;
import com.codecool.web.service.simple.SimpleWorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/works")
public class WorksServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            WorkDao workDao = new DatabaseWorkDao(connection);
            WorkService workService = new SimpleWorkService(workDao);

            User loggedInUser = (User) req.getSession().getAttribute("user");

            List<Work> works = workService.findAllByUserId(loggedInUser.getId());

            sendMessage(resp, HttpServletResponse.SC_OK, works);
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }
}
