package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;
import hu.petert.lab3.SyntaxException;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Tail implements Command {
    @Override
    public File execute(File wd, String[] cmd) throws SyntaxException, FileNotFoundException {
        int length = 10;
        boolean lengthSpecified = false;

        if(cmd.length < 2){
            throw new SyntaxException();
        }

        if(cmd[1].equals("-n")){
            lengthSpecified = true;
            if(cmd.length < 4){
                throw new SyntaxException();
            }

            try {
                length = Integer.valueOf(cmd[2]);
            } catch (NumberFormatException e) {
                System.err.println("Wrong number format");
                return wd;
            }
        }

        File file = new Helper().getFileFromArgs(wd, cmd, lengthSpecified ? 3 : 1, true);

        printLastLines(file, length);

        return wd;
    }

    private boolean printLastLines(File file, int lines) throws FileNotFoundException {

        LinkedList<String> list = new LinkedList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));;

        String line;
        try {
            while((line = reader.readLine()) != null){
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ListIterator<String> it = list.listIterator(Math.max(0, list.size() - lines));

        while(it.hasNext())
            System.out.println(it.next());

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
