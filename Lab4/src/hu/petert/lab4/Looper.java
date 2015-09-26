package hu.petert.lab4;

import hu.petert.lab4.BeerRegister.ListStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Looper {

    private boolean isRunning = true;
    private BufferedReader reader;
    private String split_string;
    private String[] cmd;
    private BeerRegister beerRegister;

    public Looper(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        split_string = " ";
        beerRegister = new BeerRegister();
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

                    break;
                case "save":

                    break;
                case "delete":

                    break;
                case "exit":
                    System.exit(0);
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

}
