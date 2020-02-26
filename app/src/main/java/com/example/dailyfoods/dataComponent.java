package com.example.dailyfoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dataComponent {

    static Map<String, Integer> chairlist = new HashMap<>();
    static Map<String, Integer> tablelist = new HashMap<>();
    static Map<String, Integer> desklist = new HashMap<>();
    static Map<String, Integer> randomlist = new HashMap<>();

    public static Map<String, Integer> getChairlist() {
        return chairlist;
    }

    public static Map<String, Integer> getTablelist() {
        return tablelist;
    }

    public static Map<String, Integer> getDesklist() {
        return desklist;
    }

    public static Map<String, Integer> getRandomlist() {
        return randomlist;
    }

    public static Map<String, Integer> getAlllist() {
        return alllist;
    }

    static Map<String, Integer> alllist = new HashMap<>();

    static {
        //Add list of chairs here
        chairlist.put("chair@chair.sfb", R.drawable.chair_thumb);


        //Add list of tables here
        tablelist.put("couch@couch.sfb", R.drawable.couch_thumb);
        tablelist.put("table@table.sfb", R.drawable.ic_launcher_foreground);

        //Add list of desks here
        desklist.put("desk@Desk.sfb", R.drawable.officetable);

        //Add list of random here
        randomlist.put("lamp@LampPost.sfb", R.drawable.lamp_thumb);


        //all data
        alllist.putAll(chairlist);
        alllist.putAll(desklist);
        alllist.putAll(tablelist);
        alllist.putAll(randomlist);

    }

}
