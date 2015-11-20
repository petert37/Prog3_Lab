package hu.petert.lab10.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Main {

    public static void main(String[] args){

        String path = "bme.xml";
        double latitude = 47.4786346;
        double longitude = 19.0555773;

        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-i") && i + 1 < args.length)
                path = args[i + 1];
            if(args[i].equals("-lat") && i + 1 < args.length)
                latitude = Double.parseDouble(args[i + 1]);
            if(args[i].equals("-lon") && i + 1 < args.length)
                longitude = Double.parseDouble(args[i + 1]);
        }

        TagCounter handler = new TagCounter();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(path), handler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        handler.listTags();
        System.out.println();
        handler.listBusStopsSorted(latitude, longitude);
    }

}
