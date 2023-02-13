package de.cofinpro.sqliteviewer.view;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * JFrame based class representing the viewer window of the SQLite viewer.
 */
public class SqLiteViewer extends JFrame {

    private static final String TITLE = "SQLite Viewer";
    private static final String FILE_NAME_TEXT_FIELD = "FileNameTextField";
    private static final String OPEN_FILE_BUTTON = "OpenFileButton";
    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;

    public SqLiteViewer() {
        super(TITLE);
        setName(TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createEntryPanel(), BorderLayout.NORTH);
        setVisible(true);
    }

    private Component createEntryPanel() {
        final var entryPanel = new JPanel(new BorderLayout(3, 0));
        entryPanel.add(createFileNameField(), BorderLayout.CENTER);
        entryPanel.add(createOpenButton(), BorderLayout.EAST);
        return entryPanel;
    }

    private Component createFileNameField() {
        final var fileNameTextField = new JTextField();
        fileNameTextField.setName(FILE_NAME_TEXT_FIELD);
        return fileNameTextField;
    }

    private Component createOpenButton() {
        final var openButton = new JButton("Open");
        openButton.setName(OPEN_FILE_BUTTON);
        return openButton;
    }
}
