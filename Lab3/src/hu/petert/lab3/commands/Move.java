package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;

import java.io.File;
import java.io.IOException;

public class Move implements Command {
    @Override
    public File execute(File wd, String[] cmd) {
        Helper helper = new Helper();

        if(cmd.length < 3){
            System.err.println("No such file");
            return wd;
        }

        File to, from;
        try {
            from = new File(wd.getCanonicalPath() + File.separator + helper.getFullName(cmd, 1));
            to = new File(wd.getCanonicalPath() + File.separator + helper.getFullName(cmd, helper.nextIndex));
        } catch (IOException e) {
            e.printStackTrace();
            return wd;
        }

        if(!from.exists()){
            System.err.println("No such file");
            return wd;
        }

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
