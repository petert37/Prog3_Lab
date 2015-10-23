package hu.petert.lab7;

public class Main {

    public static void main(String[] args){
        CaesarFrame caesarFrame = new CaesarFrame();
        caesarFrame.setLocationRelativeTo(null);
        caesarFrame.setVisible(true);
    }

    public static String caesarCode(String input, char offset) {
        char[] out = input.toUpperCase().toCharArray();
        for (int i = 0; i < out.length; i++) {
            if(out[i] < 'A' || out[i] > 'Z') continue;
            out[i] += offset - 'A';
            if (out[i] > 'Z')
                out[i] -= 'Z' - 'A' + 1;
        }
        return new String(out);
    }

    public static String caesarDecode(String input, char offset){
        char[] out = input.toUpperCase().toCharArray();
        for (int i = 0; i < out.length; i++) {
            if(out[i] < 'A' || out[i] > 'Z') continue;
            out[i] -= offset - 'A';
            if (out[i] < 'A')
                out[i] += 'Z' - 'A' + 1;
        }
        return new String(out);
    }

}
