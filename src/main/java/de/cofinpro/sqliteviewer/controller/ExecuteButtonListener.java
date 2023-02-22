package de.cofinpro.sqliteviewer.controller;

import de.cofinpro.sqliteviewer.model.SqLiteViewerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener on the Execute button, that triggers the SqLiteViewerModel to connect, query and set table data.
 */
public class ExecuteButtonListener implements ActionListener {
    private final SqLiteViewerModel model;

    public ExecuteButtonListener(SqLiteViewerModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.executeQuery();
    }
}
