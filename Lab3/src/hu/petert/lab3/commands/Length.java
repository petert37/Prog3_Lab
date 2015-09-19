package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;

import java.io.File;
import java.io.IOException;

public class Length implements Command {
    @Override
    public File execute(File wd, String[] cmd) {

        File f;
        try {
            f = new File(wd.getCanonicalPath() + File.separator + new Helper().getFullName(cmd, 1));
        } catch (IOException e) {
            e.printStackTrace();
            return wd;
        }

        if(!f.exists()){
            System.err.println("No such file");
            return wd;
        }

        System.out.println("Length: " + f.length() + " bytes");

        return wd;
    }
}
