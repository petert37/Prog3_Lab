package hu.petert.lab3_b;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Start {

    boolean zip_in, zip_out;
    File fIn, fOut;
    String regex;
    Helper helper;

    public Start(String[] args){

        helper = new Helper();

        if(!readArgs(args)) return;

        if(!printText()){
            System.err.println("Error processing text");
        }

    }

    private boolean readArgs(String[] args){

        for(int i = 0; i < args.length;){
            switch (args[i]) {
                case "-p":
                    regex = helper.getFullName(args, i + 1);
                    break;
                case "-i":
                    fIn = new File(System.getProperty("user.dir") + File.separator + helper.getFullName(args, i + 1));
                    break;
                case "-o":
                    fOut = new File(System.getProperty("user.dir") + File.separator + helper.getFullName(args, i + 1));
                    break;
                case "-gi":
                    zip_in = true;
                    break;
                case "-go":
                    zip_out = true;
                    break;
            }
            i = i < helper.nextIndex ? helper.nextIndex : i + 1;
        }

        if(fIn != null && !fIn.exists()){
            System.err.println("No such file");
            return false;
        }

        if(fIn == null || fOut == null || regex == null){
            System.err.println("Syntax error");
            return false;
        }

        return true;

    }

    private boolean filterText(BufferedReader in, BufferedWriter out, String regex){

        String line;
        try {
            while((line = in.readLine()) !=null){
                if(line.matches(regex)) {
                    out.write(line);
                    out.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean printText(){
        BufferedReader reader;
        BufferedWriter writer;

        try {
            if(zip_in)
                reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(fIn))));
            else
                reader = new BufferedReader(new FileReader(fIn));

            if(zip_out)
                writer = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(fOut))));
            else
                writer = new BufferedWriter(new FileWriter(fOut));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        filterText(reader, writer, regex);

        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

}
