package de.cofinpro.sqliteviewer.model;

import de.cofinpro.sqliteviewer.controller.DbAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;
import javax.swing.table.DefaultTableModel;

/**
 * application model class that connects to the JFrame derived SQLiteViewer via a restricted interface. It comprises
 * the application logic performed on the state (entry fields, combo box selection) as well as it keeps a reference
 * to the DbAdapter for the currently opened database file. It is triggered by UIListeners.
 */
@Slf4j
public class SqLiteViewerModel {

    private static final String SELECT_TEMPLATE = "SELECT * FROM %s;";

    private final FormElementsAccess sqLiteViewer;
    private DbAdapter dbAdapter;

    public SqLiteViewerModel(FormElementsAccess sqLiteViewer) {
        this.sqLiteViewer = sqLiteViewer;
    }

    /**
     * triggered on Open button press, this method creates a new DbAdapter instance and connects via it to the database.
     * The table names are returned and are filled into the Viewers Tables Combobox.
     */
    public void fillInTableNames() {
        log.debug("connecting to {}", getDbFileToOpen());
        dbAdapter = new DbAdapter(getDbFileToOpen());
        setTableNameChoices(dbAdapter.connectAndGetTableNames());
        if (dbAdapter.isConnected()) {
            sqLiteViewer.setEnabledSqlViewPort(true);
        } else {
            sqLiteViewer.setEnabledSqlViewPort(false);
            sqLiteViewer.setTableModel(new DefaultTableModel());
        }
    }

    /**
     * triggered on Combo Box Selection change, this method sets the selected table into a "Select *" query for
     * execution in the viewer. If no selection (empty ComboBox) the query is cleared in the TextArea.
     */
    public void setQueryText() {
        sqLiteViewer.setQueryText(getSelectedTableName().map(SELECT_TEMPLATE::formatted).orElse("")
        );
    }

    /**
     * triggered on execute button connects to the database and executes the query against the database. The received
     * TableModel is set to the Table in the viewer.
     */
    public void executeQuery() {
        final var queryText = sqLiteViewer.getQueryText();
        final var tableModel = queryText.isEmpty()
                ? new DefaultTableModel()
                : dbAdapter.connectAndGetTableData(queryText);
        sqLiteViewer.setTableModel(tableModel);
    }

    private String getDbFileToOpen() {
        return sqLiteViewer.getDbFileToOpen();
    }

    private Optional<String> getSelectedTableName() {
        return Optional.ofNullable(sqLiteViewer.getSelectedTableName());
    }

    private void setTableNameChoices(Collection<String> tableNames) {
        sqLiteViewer.setTableNameChoices(tableNames);
    }
}
