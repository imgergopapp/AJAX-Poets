package com.codecool.web.service.simple;

import com.codecool.web.dao.WorkDao;
import com.codecool.web.model.Work;
import com.codecool.web.service.WorkService;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SimpleWorkService implements WorkService {

    private final WorkDao workDao;

    public SimpleWorkService(WorkDao workDao) {
        this.workDao = workDao;
    }

    @Override
    public List<Work> findAllByUserId(int userId) throws SQLException {
        return workDao.findAllByUserId(userId);
    }

    @Override
    public Work findByWorkId(int workId) throws SQLException {
        return workDao.findByWorkId(workId);
    }

    @Override
    public int countRegexOccurances(String regex, int workId) throws SQLException {

        if (!isStringOnlyAlphabetical(regex)) {
            return -1;
        }
        Work work = findByWorkId(workId);
        String workContent = work.getContent();

        int count = 0;
        Pattern p = Pattern.compile("\\b" + regex + "\\b");
        Matcher m = p.matcher(workContent);

        while (m.find()) {
            count++;
        }
        return count;
    }

    private boolean isStringOnlyAlphabetical(String string) {
        if (string.length() == 0) {
            return false;
        }
        for (Character c : string.toCharArray()) {
            if (!Character.isAlphabetic(Character.valueOf(c))) {
                return false;
            }
        }
        return true;
    }
}
