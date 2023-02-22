package de.cofinpro.sqliteviewer.view;

import de.cofinpro.sqliteviewer.controller.ExecuteButtonListener;
import de.cofinpro.sqliteviewer.controller.OpenButtonListener;
import de.cofinpro.sqliteviewer.controller.TablesComboBoxListener;
import de.cofinpro.sqliteviewer.model.SqLiteViewerModel;
import de.cofinpro.sqliteviewer.model.FormElementsAccess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * JFrame based class representing the viewer window of the SQLite viewer.
 */
public class SqLiteViewer extends JFrame implements FormElementsAccess {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private static final String OPEN_BUTTON_TEXT = "Open";
    private static final String EXECUTE_BUTTON_TEXT = "Execute";
    private static final String TITLE = "SQLite Viewer";
    private static final String FILE_NAME_TEXT_FIELD = "FileNameTextField";
    private static final String OPEN_FILE_BUTTON = "OpenFileButton";
    private static final String TABLES_COMBO_BOX = "TablesComboBox";
    private static final String EXECUTE_QUERY_BUTTON = "ExecuteQueryButton";
    private static final String QUERY_TEXT_AREA = "QueryTextArea";
    private static final String TABLE = "Table";

    private final transient SqLiteViewerModel model;
    private JTextField fileNameTextField;
    private JTextArea queryTextArea;
    private JComboBox<String> tablesComboBox;
    private JTable jTable;

    public SqLiteViewer() {
        super(TITLE);
        setName(TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        model = new SqLiteViewerModel(this);
        add(createEntryPanel(), BorderLayout.NORTH);
        add(createSqlViewPort(), BorderLayout.CENTER);
        setVisible(true);
    }

    private Component createEntryPanel() {
        final var entryPanel = new JPanel(new BorderLayout(3, 0));
        entryPanel.add(createFileNameField(), BorderLayout.CENTER);
        entryPanel.add(createOpenButton(), BorderLayout.EAST);
        entryPanel.add(createComboBox(), BorderLayout.SOUTH);
        return entryPanel;
    }

    private Component createSqlViewPort() {
        final var viewPort = new JPanel(null);
        viewPort.add(createSqlTextArea());
        viewPort.add(createExecuteButton());
        viewPort.add(createTable());
        return viewPort;
    }

    private Component createTable() {
        jTable = new JTable(new DefaultTableModel(15, 9));
        jTable.setName(TABLE);
        jTable.setBounds(5, 115, 690, 290);
        jTable.setBorder(new LineBorder(Color.BLACK, 1));
        return jTable;
    }

    private Component createSqlTextArea() {
        queryTextArea = new JTextArea("");
        queryTextArea.setBounds(5, 5, 590, 100);
        queryTextArea.setName(QUERY_TEXT_AREA);
        return queryTextArea;
    }

    private Component createExecuteButton() {
        final var executeButton = new JButton(EXECUTE_BUTTON_TEXT);
        executeButton.setBounds(605, 5, 90, 40);
        executeButton.setName(EXECUTE_QUERY_BUTTON);
        executeButton.addActionListener(new ExecuteButtonListener(model));
        return executeButton;
    }

    private Component createFileNameField() {
        fileNameTextField = new JTextField();
        fileNameTextField.setName(FILE_NAME_TEXT_FIELD);
        return fileNameTextField;
    }

    private Component createOpenButton() {
        final var openButton = new JButton(OPEN_BUTTON_TEXT);
        openButton.setName(OPEN_FILE_BUTTON);
        openButton.addActionListener(new OpenButtonListener(model));
        return openButton;
    }

    private Component createComboBox() {
        tablesComboBox = new JComboBox<>();
        tablesComboBox.setName(TABLES_COMBO_BOX);
        tablesComboBox.addActionListener(new TablesComboBoxListener(model));
        return tablesComboBox;
    }

    @Override
    public String getDbFileToOpen() {
        return fileNameTextField.getText();
    }

    @Override
    public void setQueryText(String queryText) {
        queryTextArea.setText(queryText);
    }

    @Override
    public String getQueryText() {
        return queryTextArea.getText();
    }

    @Override
    public String getSelectedTableName() {
        return tablesComboBox.getItemAt(tablesComboBox.getSelectedIndex());
    }

    @Override
    public void setTableNameChoices(Collection<String> tableNames) {
        tablesComboBox.removeAllItems();
        tableNames.forEach(tablesComboBox::addItem);
    }

    @Override
    public void setTableModel(TableModel tableModel) {
        jTable.setModel(tableModel);
    }
}
