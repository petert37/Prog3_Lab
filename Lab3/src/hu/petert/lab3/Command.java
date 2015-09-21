package hu.petert.lab3;

import java.io.File;
import java.io.FileNotFoundException;

public interface Command {
    File execute(File wd, String[] cmd) throws FileNotFoundException, SyntaxException;
}
