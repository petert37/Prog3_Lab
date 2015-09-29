package hu.petert.lab4;

import hu.petert.lab4.BeerRegister.ListStyle;

import java.io.*;

public class Looper {

    private boolean isRunning = true;
    private BufferedReader reader;
    private String split_string;
    private String[] cmd;
    private BeerRegister beerRegister;
    private File wd;

    public Looper(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        split_string = " ";
        beerRegister = new BeerRegister();
        wd = new File(System.getProperty("user.dir"));
    }

    public void start(){
        while (isRunning){
            System.out.print(">");
            try {
                cmd = reader.readLine().split(split_string);
            } catch (IOException e) {
                System.err.println("Unable to read command");
                e.printStackTrace();
                continue;
            }

            switch (cmd[0]){
                case "add":
                    addBeer(cmd);
                    break;
                case "list":
                    listBeers(cmd);
                    break;
                case "load":
                    loadBeerRegister(cmd);
                    break;
                case "save":
                    saveBeerRegister(cmd);
                    break;
                case "delete":
                    deleteBeer(cmd);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Command not found");
            }

        }

    }

    private void addBeer(String[] cmd){
        beerRegister.add(cmd[1], cmd[2], Double.valueOf(cmd[3]));
    }

    private void listBeers(String[] cmd){
        ListStyle listStyle = ListStyle.DEFAULT;

        if(cmd.length >= 2){
            switch (cmd[1]){
                case "name":
                    listStyle = ListStyle.NAME;
                    break;
                case "style":
                    listStyle = ListStyle.STYLE;
                    break;
                case "strength":
                    listStyle = ListStyle.STRENGTH;
                    break;
                default:
                    listStyle = ListStyle.DEFAULT;
            }
        }

        beerRegister.list(listStyle);

    }

    private void saveBeerRegister(String[] cmd){

        if(cmd.length < 2){
            System.err.println("Syntax error");
            return;
        }

        File file = new File(wd + File.separator + cmd[1]);

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(beerRegister);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadBeerRegister(String[] cmd){

        if(cmd.length < 2){
            System.err.println("Syntax error");
            return;
        }

        File file = new File(wd + File.separator + cmd[1]);

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            beerRegister = (BeerRegister) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deleteBeer(String[] cmd){
        if(cmd.length < 2){
            System.err.println("Syntax error");
            return;
        }

        beerRegister.delete(cmd[1]);

    }

}
