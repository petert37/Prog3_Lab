package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;
import hu.petert.lab3.SyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

public class Remove implements Command {
    @Override
    public File execute(File wd, String[] cmd) throws SyntaxException, FileNotFoundException {

        if(cmd.length < 2){
            throw new SyntaxException();
        }

        File file = new Helper().getFileFromArgs(wd, cmd, 1, true);

        try {
            Files.delete(file.toPath());
        } catch (DirectoryNotEmptyException e) {
            System.err.println("Failed to remove: directory is not empty");
        } catch (Exception e){
            e.printStackTrace();
        }

        return wd;
    }
}
