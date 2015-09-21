package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;
import hu.petert.lab3.SyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ChangeDir implements Command {
    @Override
    public File execute(File wd, String[] cmd) throws FileNotFoundException, SyntaxException {

        if(cmd.length < 2) throw new SyntaxException();

        if(cmd[1].equals("..")) return wd.getParentFile();

        File file = new Helper().getFileFromArgs(wd, cmd, 1, true);

        if(file.isDirectory()){
            return file;
        } else {
            throw new FileNotFoundException("Not a directory");
        }

    }
}
