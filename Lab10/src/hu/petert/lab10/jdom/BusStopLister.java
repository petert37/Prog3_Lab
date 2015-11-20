package hu.petert.lab10.jdom;

import hu.petert.lab10.Distance;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusStopLister {

    Element root;
    Document document;

    public BusStopLister(String inPath){

        File input = new File(inPath);

        SAXBuilder builder = new SAXBuilder();

        try {
            document = builder.build(input);
            root = document.getRootElement();
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

    }

    private void filterDocument(){

        List<Element> children = root.getChildren();
        List<Element> toDetach = new ArrayList<>();

        for(Element e : children){
            if(!e.getQualifiedName().equals("node"))
                toDetach.add(e);
            else{
                List<Element> c = e.getChildren();
                boolean foundBus = false;
                for(Element e1 : c){
                    if((e1.getAttributeValue("v")).equals("bus_stop"))
                        foundBus = true;
                }
                if(!foundBus) toDetach.add(e);
            }
        }

        toDetach.forEach(Element::detach);

//        Iterator<Element> elementIterator = root.getChildren().iterator();
//        while (elementIterator.hasNext()){
//            Element e = elementIterator.next();
//            if(!e.getQualifiedName().equals("node"))
//                elementIterator.remove();
//            else{
//                List<Element> c = e.getChildren();
//                boolean foundBus = false;
//                for(Element e1 : c){
//                    if((e1.getAttributeValue("v")).equals("bus_stop"))
//                        foundBus = true;
//                }
//                if(!foundBus) elementIterator.remove();
//            }
//        }

    }

    public void filterBuses(String outPath, double latitude, double longitude){
        filterDocument();
        addDistances(latitude, longitude);
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outPath);
            outputter.output(document, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addDistances(double latitude, double longitude){
        List<Element> buses = document.getRootElement().getChildren();
        double lat, lon, distance;
        for(Element e : buses){
            lat = Double.parseDouble(e.getAttributeValue("lat"));
            lon = Double.parseDouble(e.getAttributeValue("lon"));
            distance = Distance.getDistance(lat, lon, latitude, longitude);
            Element element = new Element("tag");
            element.setAttribute("k", "distance");
            element.setAttribute("v", String.valueOf(distance));
            e.addContent(element);
        }
    }

}
