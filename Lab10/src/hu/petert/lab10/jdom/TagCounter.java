package hu.petert.lab10.jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagCounter {

    Document document;
    Element root;
    private Map<String, Integer> tagList;
    
    public TagCounter(String fileName){

        tagList = new HashMap<>();

        SAXBuilder builder = new SAXBuilder();
        File input = new File(fileName);

        try {
            document = builder.build(input);
            root = document.getRootElement();
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

    }

    private void countDescendantTags(Element element){
        if(!tagList.containsKey(element.getQualifiedName()))
            tagList.put(element.getQualifiedName(), 0);

        tagList.put(element.getQualifiedName(), tagList.get(element.getQualifiedName()) + 1);

        List<Element> children = element.getChildren();

        children.forEach(this::countDescendantTags);

    }

    public void listTags(){
        countDescendantTags(root);
        for(String s : tagList.keySet())
            System.out.println(s + ": " + tagList.get(s));
    }
    
    
}
