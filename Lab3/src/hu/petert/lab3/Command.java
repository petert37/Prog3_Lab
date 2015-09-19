package hu.petert.lab3;

import java.io.File;

public interface Command {
    File execute(File wd, String[] cmd);
}
