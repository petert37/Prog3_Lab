package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;
import hu.petert.lab3.SyntaxException;

import java.io.*;

public class Copy implements Command {
    @Override
    public File execute(File wd, String[] cmd) throws FileNotFoundException, SyntaxException {

        Helper helper = new Helper();

        if(cmd.length < 3){
            throw new SyntaxException();
        }

        File from = helper.getFileFromArgs(wd, cmd, 1, true);
        if(!from.isFile()) throw new FileNotFoundException();

        File to = helper.getFileFromArgs(wd, cmd, helper.nextIndex, false);

        if(!copyFile(from, to))
            System.err.println("Failed to copy file");

        return wd;
    }

    private boolean copyFile(File from, File to){

        FileInputStream input;
        FileOutputStream output;

        try {
            input = new FileInputStream(from);
            output = new FileOutputStream(to);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        for(int i = 0; i < from.length(); i++){
            try {
                output.write(input.read());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        try {
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

}
