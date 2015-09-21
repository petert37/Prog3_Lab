package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;
import hu.petert.lab3.SyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Move implements Command {
    @Override
    public File execute(File wd, String[] cmd) throws SyntaxException, FileNotFoundException {
        Helper helper = new Helper();

        if(cmd.length < 3){
            throw new SyntaxException();
        }

        File from = helper.getFileFromArgs(wd, cmd, 1, true);

        File to = helper.getFileFromArgs(wd, cmd, helper.nextIndex, false);

        if(!moveFile(from, to))
            System.err.println("Failed to move file");

        return wd;
    }

    private boolean moveFile(File from, File to){
        try {
            java.nio.file.Files.move(from.toPath(), to.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
