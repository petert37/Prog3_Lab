package hu.petert.lab3;

import java.io.File;
import java.io.FileNotFoundException;

public class Helper {

    public int nextIndex = 0;

    public String getFullName(String[] cmd, int nameStartIndex) throws SyntaxException{

        if(cmd.length <= nameStartIndex) throw new SyntaxException();

        String fileName, fileNameTemp;

        nextIndex = nameStartIndex + 1;

        if(cmd[nameStartIndex].charAt(0) != '\"') fileName = cmd[nameStartIndex];
        else{
            fileNameTemp = cmd[nameStartIndex];

            for(int i = nameStartIndex + 1; i < cmd.length; i++){
                fileNameTemp += " " + cmd[i];
                if(fileNameTemp.charAt(fileNameTemp.length() - 1) == '\"') {
                    nextIndex = i + 1;
                    break;
                }
            }

            if(fileNameTemp.charAt(fileNameTemp.length() - 1) != '\"') throw new SyntaxException();

            fileName = fileNameTemp.substring(1, fileNameTemp.length() - 1);
        }

        return fileName;

    }

    public File getFileFromArgs(File wd, String[] cmd, int nameStartIndex, boolean checkExistence) throws FileNotFoundException, SyntaxException{

        if(cmd.length <= nameStartIndex) throw new SyntaxException();

        File file;
        String fileName = getFullName(cmd, nameStartIndex);

        file = new File(wd.getPath() + File.separator + fileName);

        if(checkExistence && !file.exists()) throw new FileNotFoundException();

        return file;
    }

}
