package hu.petert.lab3.commands;

import hu.petert.lab3.Command;

import java.io.File;

public class RecursiveList implements Command {

    @Override
    public File execute(File wd, String[] cmd) {
        listDir(wd, "");
        return wd;
    }

    private void listDir(File wd, String tab){

        File[] files = wd.listFiles();

        for(int i = 0; i < files.length; i++){
            System.out.println(tab + files[i].getName());
            if(files[i].isDirectory()) listDir(files[i], tab + "  ");
        }
    }
}
