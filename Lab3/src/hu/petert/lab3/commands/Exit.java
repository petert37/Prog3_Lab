package hu.petert.lab3.commands;

import hu.petert.lab3.Command;

import java.io.File;

public class Exit implements Command {

    @Override
    public File execute(File wd, String[] cmd) {
        System.exit(0);
        return null;
    }

}
