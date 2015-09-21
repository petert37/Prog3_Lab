package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;
import hu.petert.lab3.SyntaxException;

import java.io.*;

public class PrintFilePattern implements Command {
    @Override
    public File execute(File wd, String[] cmd) throws SyntaxException, FileNotFoundException {

        Helper helper = new Helper();

        if(cmd.length < 3){
            throw new SyntaxException();
        }

        String regex = helper.getFullName(cmd, 1);

        File file = helper.getFileFromArgs(wd, cmd, helper.nextIndex, true);
        BufferedReader reader = new BufferedReader(new FileReader(file));

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
