package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;
import hu.petert.lab3.SyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Length implements Command {
    @Override
    public File execute(File wd, String[] cmd) throws FileNotFoundException, SyntaxException{

        File f = new Helper().getFileFromArgs(wd, cmd, 1, true);

        System.out.println("Length: " + f.length() + " bytes");

        return wd;
    }
}
