package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;

import java.io.*;

public class Copy implements Command {
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

        if(!copyFile(from, to))
            System.err.println("Failed to copy file");


        return wd;
    }

    private boolean copyFile(File from, File to){

        FileInputStream input;
        FileOutputStream output;

        try {
            input = new FileInputStream(from);
            output = new FileOutputStream(to);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        for(int i = 0; i < from.length(); i++){
            try {
                output.write(input.read());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        try {
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

}
