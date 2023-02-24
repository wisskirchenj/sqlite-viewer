package de.cofinpro.sqliteviewer.controller;

import de.cofinpro.sqliteviewer.model.SqLiteViewerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener on the Open button, that triggers the SqLiteViewerModel to connect and set table names.
 */
public class OpenButtonListener implements ActionListener {

    private final SqLiteViewerModel sqLiteViewerModel;

    public OpenButtonListener(SqLiteViewerModel sqLiteViewerModel) {
        this.sqLiteViewerModel = sqLiteViewerModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sqLiteViewerModel.fillInTableNames();
    }
}
