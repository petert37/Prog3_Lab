package hu.petert.lab3.commands;

import hu.petert.lab3.Command;

import java.io.File;

public class List implements Command {

    @Override
    public File execute(File wd, String[] cmd) {

        boolean detailed = false;

        if(cmd.length > 1 && cmd[1].equals("-l")) detailed = true;

        File[] files = wd.listFiles();

        for(int i = 0; i < files.length; i++){
            System.out.print(files[i].getName());
            if(detailed){
                if(files[i].isDirectory()) System.out.print(" D");
                else {
                    System.out.print(" F");
                    System.out.print(" " + files[i].length() + " bytes");
                }

            }
            System.out.println();
        }

        return wd;
    }
}
