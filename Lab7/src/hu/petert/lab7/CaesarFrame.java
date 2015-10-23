package hu.petert.lab7;

import hu.petert.lab7.listeners.InputFieldDocumentListener;
import hu.petert.lab7.listeners.OkButtonActionListener;
import hu.petert.lab7.listeners.TextFieldFocusListener;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CaesarFrame extends JFrame {

    public enum CodeDirection{
        CODE, DECODE
    }

    private static final int TEXTFIELD_WIDTH = 20;

    private CodeDirection codeDirection = CodeDirection.CODE;

    private JTextField inputTextField, outputTextField;
    private JComboBox<Character> offsetComboBox;

    public CaesarFrame() throws HeadlessException {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SwingLab");
        setSize(400, 110);
        //setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        initializeComponents();

    }

    private void initializeComponents(){

        JPanel topPanel = new JPanel();

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        Vector<Character> letters = new Vector<>(26);
        for(char c = 'A'; c <= 'Z'; c++)
            letters.add(c);
        offsetComboBox = new JComboBox<>(letters);
        topPanel.add(offsetComboBox);

        inputTextField = new JTextField(TEXTFIELD_WIDTH);
        inputTextField.getDocument().addDocumentListener(new InputFieldDocumentListener(this));
        inputTextField.addFocusListener(new TextFieldFocusListener(this, CodeDirection.CODE));
        //inputTextField.addKeyListener(new InputFieldKeyListener());
        topPanel.add(inputTextField);

        JButton codeButton = new JButton("Code!");
        codeButton.addActionListener(new OkButtonActionListener(this));
        topPanel.add(codeButton);

        bottomPanel.add(new JLabel("Output:"));

        outputTextField = new JTextField(TEXTFIELD_WIDTH);
        outputTextField.addFocusListener(new TextFieldFocusListener(this, CodeDirection.DECODE));
        bottomPanel.add(outputTextField);

        add(topPanel);
        add(bottomPanel);

    }

    public void updateOutput(){
        if(codeDirection == CodeDirection.CODE)
            outputTextField.setText(Main.caesarCode(inputTextField.getText(), (Character) offsetComboBox.getSelectedItem()));
    }

    public void updateInput(){
        if(codeDirection == CodeDirection.DECODE)
            inputTextField.setText(Main.caesarDecode(outputTextField.getText(), (Character) offsetComboBox.getSelectedItem()));
    }

    public CodeDirection getCodeDirection() {
        return codeDirection;
    }

    public void setCodeDirection(CodeDirection codeDirection) {
        this.codeDirection = codeDirection;
    }

//    private class InputFieldKeyListener extends KeyAdapter{
//
//        @Override
//        public void keyTyped(KeyEvent e) {
//            updateOutput();
//        }
//    }

}
