package hu.petert.lab7.listeners;

import hu.petert.lab7.CaesarFrame;
import hu.petert.lab7.CaesarFrame.CodeDirection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OkButtonActionListener implements ActionListener {

    private CaesarFrame caesarFrame;

    public OkButtonActionListener(CaesarFrame caesarFrame){

        this.caesarFrame = caesarFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(caesarFrame.getCodeDirection() == CodeDirection.CODE)
            caesarFrame.updateOutput();
        else if(caesarFrame.getCodeDirection() == CodeDirection.DECODE)
            caesarFrame.updateInput();
    }
}
