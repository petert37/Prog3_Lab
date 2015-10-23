package hu.petert.lab7.listeners;

import hu.petert.lab7.CaesarFrame;
import hu.petert.lab7.CaesarFrame.CodeDirection;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextFieldFocusListener implements FocusListener {

    private CaesarFrame caesarFrame;
    private CodeDirection codeDirection;

    public TextFieldFocusListener(CaesarFrame caesarFrame, CodeDirection codeDirection) {
        this.caesarFrame = caesarFrame;
        this.codeDirection = codeDirection;
    }

    @Override
    public void focusGained(FocusEvent e) {
        caesarFrame.setCodeDirection(codeDirection);
    }

    @Override
    public void focusLost(FocusEvent e) {
        caesarFrame.setCodeDirection(codeDirection);
    }
}
