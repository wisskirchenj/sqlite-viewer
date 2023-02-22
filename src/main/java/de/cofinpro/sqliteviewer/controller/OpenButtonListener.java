package de.cofinpro.sqliteviewer.controller;

import de.cofinpro.sqliteviewer.model.SqLiteViewerModel;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener on the Open button, that triggers the SqLiteViewerModel to connect and set table names.
 */
@Slf4j
public class OpenButtonListener implements ActionListener {

    private final SqLiteViewerModel sqLiteViewerModel;

    public OpenButtonListener(SqLiteViewerModel sqLiteViewerModel) {
        this.sqLiteViewerModel = sqLiteViewerModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        log.debug("Open button clicked!");
        sqLiteViewerModel.fillInTableNames();
    }
}
