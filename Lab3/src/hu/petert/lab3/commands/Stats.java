package hu.petert.lab3.commands;

import hu.petert.lab3.Command;
import hu.petert.lab3.Helper;

import java.io.*;
import java.util.StringTokenizer;

public class Stats implements Command {
    @Override
    public File execute(File wd, String[] cmd) {

        int lines = 0;
        int words = 0;
        int letters = 0;

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
                lines++;
                String[] splitter = line.split(" ");
                words += splitter.length;
                for(String s : splitter)
                    letters += s.length();
            }

//            while((line = reader.readLine()) != null){
//                lines++;
//                StringTokenizer st = new StringTokenizer(line, " ");
//                while(st.hasMoreTokens()){
//                    words++;
//                    letters += st.nextToken().length();
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lines: " + lines + ", Words: " + words + ", Letters: " + letters);

        return wd;
    }
}

