package de.cofinpro.sqliteviewer.controller;

import de.cofinpro.sqliteviewer.model.SqLiteViewerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener on the Tables combo box, that triggers on a selection change in the combo box (by the user as well
 * as programmatically) the SqLiteViewerModel to build a query for the selected table.
 */
public class TablesComboBoxListener implements ActionListener {

    private final SqLiteViewerModel sqLiteViewerModel;

    public TablesComboBoxListener(SqLiteViewerModel sqLiteViewerModel) {
        this.sqLiteViewerModel = sqLiteViewerModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sqLiteViewerModel.setQueryText();
    }
}
