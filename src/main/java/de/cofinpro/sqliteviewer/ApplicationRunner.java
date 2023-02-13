package de.cofinpro.sqliteviewer;

import de.cofinpro.sqliteviewer.view.SqLiteViewer;

import javax.swing.SwingUtilities;

/**
 * application runner that starts the GraphVisualizer in the EDT-thread
 */
public class ApplicationRunner {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SqLiteViewer::new);
    }
}
