package hu.petert.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BeerRegister {

    public enum ListStyle{
        DEFAULT, NAME, STYLE, STRENGTH
    }

    ArrayList<Beer> beerList;

    public BeerRegister(){

        beerList = new ArrayList<>();

    }

    public void add(String name, String style, Double strength){
        beerList.add(new Beer(name, style, strength));
    }

    public void list(ListStyle listStyle){
        ArrayList<Beer> tmp = new ArrayList<>();
        tmp.addAll(beerList);

        if(listStyle != ListStyle.DEFAULT){
            Comparator<Beer> comparator;
            switch (listStyle){
                case NAME:
                    comparator = new NameComparator();
                    break;
                case STYLE:
                    comparator = new StyleComparator();
                    break;
                case STRENGTH:
                    comparator = new StrengthComparator();
                    break;
                default:
                    comparator = new NameComparator();
            }

            Collections.sort(tmp, comparator);
        }

        for(Beer b : tmp)
            System.out.println(b.toString());

    }

}
