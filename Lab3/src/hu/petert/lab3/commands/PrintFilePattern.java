package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;

import java.io.*;

public class PrintFilePattern implements Command {
    @Override
    public File execute(File wd, String[] cmd) {

        Helper helper = new Helper();

        if(cmd.length < 3){
            System.err.println("Syntax error");
            return wd;
        }

        String regex = helper.getFullName(cmd, 1);

        File f;
        try {
            f = new File(wd.getCanonicalPath() + File.separator + new Helper().getFullName(cmd, helper.nextIndex));
        } catch (IOException e) {
            e.printStackTrace();
            return wd;
        }

        if(!f.exists()){
            System.err.println("No such file");
            return wd;
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return wd;
        }

        String line;
        try {
            while((line = reader.readLine()) != null){
                if(line.matches(regex))
                    System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wd;
    }
}
