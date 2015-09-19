package hu.petert.lab3_b;

public class Helper {

    public int nextIndex = 0;

    public String getFullName(String[] cmd, int nameStartIndex){

        nextIndex = nameStartIndex + 1;

        if(cmd.length <= nameStartIndex) return "";

        if(cmd[nameStartIndex].charAt(0) != '\"') return cmd[nameStartIndex];

        String path = cmd[nameStartIndex];

        for(int i = nameStartIndex + 1; i < cmd.length; i++){
            path += " " + cmd[i];
            if(path.charAt(path.length() - 1) == '\"') {
                nextIndex = i + 1;
                break;
            }
        }

        if(path.charAt(path.length() - 1) != '\"') return cmd[nameStartIndex].substring(1);

        return path.substring(1, path.length() - 1);
    }

}
