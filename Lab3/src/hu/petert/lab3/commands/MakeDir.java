package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;
import hu.petert.lab3.SyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MakeDir implements Command {
    @Override
    public File execute(File wd, String[] cmd) throws FileNotFoundException, SyntaxException {

        File file = new Helper().getFileFromArgs(wd, cmd, 1, false);

        if(file.exists()){
            System.err.println("Directory already exists");
            return wd;
        }

        if(!file.mkdir()) System.err.println("Failed to make directory");

        return wd;
    }
}
