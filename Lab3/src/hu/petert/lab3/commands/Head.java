package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;

import java.io.*;

public class Head implements Command {
    @Override
    public File execute(File wd, String[] cmd) {

        int length = 10;
        boolean lengthSpecified = false;

        if(cmd.length < 2){
            System.err.println("No such file");
            return wd;
        }

        if(cmd[1].equals("-n")){
            lengthSpecified = true;
            if(cmd.length < 4){
                System.err.println("Syntax error");
                return wd;
            }

            try {
                length = Integer.valueOf(cmd[2]);
            } catch (NumberFormatException e) {
                System.err.println("Wrong number format");
                return wd;
            }
        }

        String path =  new Helper().getFullName(cmd, lengthSpecified ? 3 : 1);

        File f;
        try {
            f = new File(wd.getCanonicalPath() + File.separator + path);
        } catch (IOException e) {
            e.printStackTrace();
            return wd;
        }

        if(!f.exists()){
            System.err.println("No such file");
            return wd;
        }

        printLines(f, length);

        return wd;
    }

    private boolean printLines(File file, int lines){

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 0; i < lines; i++){
            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            if(line == null){
                System.err.println("Not enough lines in file");
                return true;
            }

            System.out.println(line);

        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }
}
