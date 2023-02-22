package de.cofinpro.sqliteviewer.controller;

import de.cofinpro.sqliteviewer.log4j.MockedAppender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.apache.logging.log4j.Level.ERROR;
import static org.apache.logging.log4j.Level.WARN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DbAdapterTest {

    static final String smallDbPath = "test.db";
    static final String largeDbPath = "test2.db";
    MockedAppender mockedAppender;
    Logger logger;

    void addMockAppender(Level level) {
        mockedAppender = new MockedAppender();
        logger = (Logger) LogManager.getLogger(DbAdapter.class);
        logger.addAppender(mockedAppender);
        logger.setLevel(level);
    }

    @Test
    void whenNonExist_WarnMessage() {
        addMockAppender(WARN);
        new DbAdapter("nonexist.db");
        assertEquals(1, mockedAppender.getMessages().size());
        assertEquals(mockedAppender.getMessages().get(0), "Database-File nonexist.db does not exist!");
        logger.removeAppender(mockedAppender);
    }

    @Test
    void whenExist_NoWarnMessage() {
        addMockAppender(WARN);
        assertEquals(0, mockedAppender.getMessages().size());
        new DbAdapter(smallDbPath);
        logger.removeAppender(mockedAppender);
    }

    @Test
    void whenSmallDb_OneTableReturned() {
        addMockAppender(ERROR);
        var dbAdapter = new DbAdapter(smallDbPath);
        assertEquals(List.of("card"), dbAdapter.connectAndGetTableNames());
        assertEquals(0, mockedAppender.getMessages().size());
        logger.removeAppender(mockedAppender);
    }

    @Test
    void whenLargeDb_ThreeTablesReturned() {
        addMockAppender(ERROR);
        var dbAdapter = new DbAdapter(largeDbPath);
        assertEquals(List.of("card", "account", "transactions"), dbAdapter.connectAndGetTableNames());
        assertEquals(0, mockedAppender.getMessages().size());
        logger.removeAppender(mockedAppender);
    }

    @Test
    void whenNoDb_EmptyTableModelReturned() {
        var dbAdapter = new DbAdapter("not_there");
        assertEquals(0, dbAdapter.connectAndGetTableData("SELECT * FROM TABLE;").getRowCount());
    }

    @Test
    void whenAccountTableQueried_RightTableModelReturned() {
        var dbAdapter = new DbAdapter(largeDbPath);
        var tableModel = dbAdapter.connectAndGetTableData("SELECT * FROM account;");
        assertEquals(3, tableModel.getRowCount());
        assertEquals(2, tableModel.getColumnCount());
        assertEquals("id", tableModel.getColumnName(0));
        assertEquals("no", tableModel.getColumnName(1));
    }
}