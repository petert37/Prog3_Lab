package hu.petert.lab3.commands;

import hu.petert.lab3.Command;

import java.io.File;
import java.io.IOException;

public class PrintPath implements Command {
    @Override
    public File execute(File wd, String[] cmd) {
        try {
            System.out.println(wd.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wd;
    }
}
