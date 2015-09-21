package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;
import hu.petert.lab3.SyntaxException;

import java.io.*;

public class PrintFile implements Command {
    @Override
    public File execute(File wd, String[] cmd) throws FileNotFoundException, SyntaxException {

        File file = new Helper().getFileFromArgs(wd, cmd, 1, true);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;

        try {
            while((line = reader.readLine()) != null)
                System.out.println(line);
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
