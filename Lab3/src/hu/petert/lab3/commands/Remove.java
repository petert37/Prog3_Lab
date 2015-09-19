package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

public class Remove implements Command {
    @Override
    public File execute(File wd, String[] cmd) {

        if(cmd.length < 2){
            System.err.println("No such file or directory");
            return wd;
        }

        String path = new Helper().getFullName(cmd, 1);

        File f;
        try {
            f = new File(wd.getCanonicalPath() + File.separator + path);
        } catch (IOException e) {
            e.printStackTrace();
            return wd;
        }

        try {
            Files.delete(f.toPath());
        } catch (NoSuchFileException e) {
            System.err.println("No such file or directory");
        } catch (DirectoryNotEmptyException e) {
            System.err.println("Directory is not empty");
        } catch (Exception e){
            e.printStackTrace();
        }

        return wd;
    }
}
