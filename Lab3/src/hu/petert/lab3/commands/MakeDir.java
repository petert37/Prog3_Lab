package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;

import java.io.File;
import java.io.IOException;

public class MakeDir implements Command {
    @Override
    public File execute(File wd, String[] cmd) {

        String path = new Helper().getFullName(cmd, 1);

        File f;
        try {
            f = new File(wd.getCanonicalPath() + File.separator + path);
        } catch (IOException e) {
            e.printStackTrace();
            return wd;
        }

        if(f.exists()){
            System.err.println("Directory already exists");
            return wd;
        }

        if(!f.mkdir()) System.err.println("Failed to make directory");

        return wd;
    }
}
