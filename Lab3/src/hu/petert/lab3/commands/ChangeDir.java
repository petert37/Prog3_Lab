package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;

import java.io.File;
import java.io.IOException;

public class ChangeDir implements Command {
    @Override
    public File execute(File wd, String[] cmd) {

        if(cmd.length < 2 || cmd[1] == null){
            System.err.println("No such directory");
            return wd;
        }

        String path = new Helper().getFullName(cmd, 1);

        if(path.equals("..")) return wd.getParentFile();

        File newFile;

        try {
            newFile = new File(wd.getCanonicalPath() + File.separator + path);
        } catch (IOException e) {
            e.printStackTrace();
            return wd;
        }

        if(newFile.exists() && newFile.isDirectory()){
            return newFile;
        } else {
            System.err.println("No such directory");
            return wd;
        }
    }
}
