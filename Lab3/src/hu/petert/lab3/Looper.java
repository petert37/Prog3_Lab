package hu.petert.lab3;

import hu.petert.lab3.commands.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Looper {

    private boolean isRunning = true;
    private File wd;
    private String split_string;
    private String[] cmd;
    private BufferedReader reader;

    public Looper(){
        wd = new File(System.getProperty("user.dir"));
        reader = new BufferedReader(new InputStreamReader(System.in));
        split_string = " ";
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
            if(cmd[0].equals("exit")) wd = new Exit().execute(wd, cmd);
            else if(cmd[0].equals("reclist")) wd = new RecursiveList().execute(wd, cmd);
            else if(cmd[0].equals("pwd")) wd = new PrintPath().execute(wd, cmd);
            else if(cmd[0].equals("cd")) wd = new ChangeDir().execute(wd, cmd);
            else if(cmd[0].equals("ls")) wd = new List().execute(wd, cmd);
            else if(cmd[0].equals("rm")) wd = new Remove().execute(wd, cmd);
            else if(cmd[0].equals("mkdir")) wd = new MakeDir().execute(wd, cmd);
            else if(cmd[0].equals("cp")) wd = new Copy().execute(wd, cmd);
            else if(cmd[0].equals("head")) wd = new Head().execute(wd, cmd);
            else if(cmd[0].equals("mv")) wd = new Move().execute(wd, cmd);
            else if(cmd[0].equals("cat")) wd = new PrintFile().execute(wd, cmd);
            else if(cmd[0].equals("wc")) wd = new Stats().execute(wd, cmd);
            else if(cmd[0].equals("length")) wd = new Length().execute(wd, cmd);
            else if(cmd[0].equals("tail")) wd = new Tail().execute(wd, cmd);
            else if(cmd[0].equals("grep")) wd = new PrintFilePattern().execute(wd, cmd);
            else System.err.println("Command not found");

        }
    }

}
