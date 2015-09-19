package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Tail implements Command {
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

        printLastLines(f, length);

        return wd;
    }

    private boolean printLastLines(File file, int lines) {

        LinkedList<String> list = new LinkedList<>();

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

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
