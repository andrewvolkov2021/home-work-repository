package by.it_academy.jd2.Mk_JD2_82_21_vote.controller;

import java.util.Comparator;
import java.util.Map;

public class ArtistComparator implements Comparator<Map.Entry<String, Integer>> {
    public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
        return b.getValue() - a.getValue();
    }
}
