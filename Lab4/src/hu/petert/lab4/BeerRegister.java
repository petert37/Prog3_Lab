package hu.petert.lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BeerRegister implements Serializable {

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

        @SuppressWarnings("unchecked")
        ArrayList<Beer> tmp = (ArrayList<Beer>) beerList.clone();

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

    public void delete(String value, ListStyle listStyle){

        @SuppressWarnings("unchecked")
        ArrayList<Beer> tmp = (ArrayList<Beer>) beerList.clone();

        Beer tmpBeer;

        Comparator<Beer> comparator;
        switch (listStyle){
            case NAME:
                comparator = new NameComparator();
                tmpBeer = new Beer(value, "", 0d);
                break;
            case STYLE:
                comparator = new StyleComparator();
                tmpBeer = new Beer("", value, 0d);
                break;
            case STRENGTH:
                comparator = new StrengthComparator();
                tmpBeer = new Beer("", "", Double.parseDouble(value));
                break;
            default:
                comparator = new NameComparator();
                tmpBeer = new Beer(value, "", 0d);
        }

        Collections.sort(tmp, comparator);

        int key = Collections.binarySearch(tmp, tmpBeer, comparator);

        if(key < 0){
            System.err.println("Beer not found");
            return;
        }

        beerList.remove(tmp.get(key));

    }

}
