package hu.petert.lab7.listeners;

import hu.petert.lab7.CaesarFrame;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InputFieldDocumentListener implements DocumentListener {

    private CaesarFrame caesarFrame;

    public InputFieldDocumentListener(CaesarFrame caesarFrame) {
        this.caesarFrame = caesarFrame;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        caesarFrame.updateOutput();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        caesarFrame.updateOutput();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        caesarFrame.updateOutput();
    }
}
