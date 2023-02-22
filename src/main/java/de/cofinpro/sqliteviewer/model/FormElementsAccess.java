package de.cofinpro.sqliteviewer.model;

import java.util.Collection;
import javax.swing.table.TableModel;

/**
 * interface implemented by the SQliteViewer that restricts access for its model class to the necessary actions.
 */
public interface FormElementsAccess {

    String getDbFileToOpen();

    void setQueryText(String queryText);

    String getQueryText();

    String getSelectedTableName();

    void setTableNameChoices(Collection<String> tableNames);

    void setTableModel(TableModel tableModel);
}
