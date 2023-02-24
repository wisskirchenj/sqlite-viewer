package de.cofinpro.sqliteviewer.controller;

import lombok.extern.slf4j.Slf4j;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Instances of this class are created for a given database file (constructor parameter) and set up a datasource.
 * Later method calls open a new connection to retrieve data from this source.
 */
@Slf4j
public class DbAdapter {

    private final SQLiteDataSource database;

    /**
     * open a SQLiteDatasource in read-only mode(!), if the given file path is a regular file, else database set to null.
     * @param dbFile file to open.
     */
    public DbAdapter(String dbFile) {
        if (Files.isRegularFile(Path.of(dbFile))) {
            final var config = new SQLiteConfig();
            config.setReadOnly(true);
            database = new SQLiteDataSource(config);
            database.setUrl("jdbc:sqlite:" + dbFile);
        } else {
            log.warn("Database-File {} does not exist!", dbFile);
            showErrorDialog("File doesn't exist!");
            database = null;
        }
    }

    /**
     * connect to the database field and retrieve the table names.
     * @return collection of table names (except system tables).
     */
    public DefaultTableModel connectAndGetTableData(String query) {
        final var tableModel = new DefaultTableModel();
        if (database == null) {
            return tableModel;
        }
        try (var statement = database.getConnection().createStatement()) {
            var resultSet = statement.executeQuery(query);
            int columns = addHeaderRow(tableModel, resultSet);
            while (resultSet.next()) {
                tableModel.addRow(getRowData(resultSet, columns));
            }
        } catch (SQLException e) {
            showErrorDialog(e.getMessage());
            log.error("", e);
        }
        return tableModel;
    }

    private Object[] getRowData(ResultSet resultSet, int columns) throws SQLException {
        Object[] rowData = new Object[columns];
        for (int i = 1; i <= columns; i++) {
            rowData[i - 1] = resultSet.getObject(i);
        }
        return rowData;
    }

    private int addHeaderRow(DefaultTableModel tableModel, ResultSet resultSet) throws SQLException {
        int columns = resultSet.getMetaData().getColumnCount();
        Object[] columnNames = new Object[columns];
        for (int i = 1; i <= columns; i++) {
            final var columnLabel = resultSet.getMetaData().getColumnLabel(i);
            tableModel.addColumn(columnLabel);
            columnNames[i - 1] = columnLabel;
        }
        tableModel.addRow(columnNames);
        return columns;
    }

    /**
     * connect to the database field and retrieve the table names.
     * @return collection of table names (except system tables).
     */
    public Collection<String> connectAndGetTableNames() {
        if (database == null) {
            return Collections.emptyList();
        }
        var tableNames = new ArrayList<String>();
        try (var statement = database.getConnection().createStatement()) {
            var tableResultSet = statement.executeQuery(
                    "SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';"
            );
            while (tableResultSet.next()) {
                tableNames.add(tableResultSet.getString("name"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        log.debug("{}", tableNames);
        return tableNames;
    }

    public boolean isConnected() {
        return database != null;
    }

    private static void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage);
    }
}
