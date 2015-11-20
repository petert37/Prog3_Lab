package hu.petert.lab10.jdom;

public class Main {

    public static void main(String[] args){

        String inPath = "bme.xml";
        String outPath = "bme_bus.xml";
        double latitude = 47.4786346;
        double longitude = 19.0555773;

        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-i") && i + 1 < args.length)
                inPath = args[i + 1];
            if(args[i].equals("-o") && i + 1 < args.length)
                outPath = args[i + 1];
            if(args[i].equals("-lat") && i + 1 < args.length)
                latitude = Double.parseDouble(args[i + 1]);
            if(args[i].equals("-lon") && i + 1 < args.length)
                longitude = Double.parseDouble(args[i + 1]);
        }

        TagCounter tagCounter = new TagCounter(inPath);
        tagCounter.listTags();

        BusStopLister lister = new BusStopLister(inPath);
        lister.filterBuses(outPath, latitude, longitude);

    }

}
