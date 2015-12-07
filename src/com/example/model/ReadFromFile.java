package com.example.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Admin on 01-Dec-15.
 */
public class ReadFromFile {
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> finallist = new ArrayList<String>();

    public ArrayList<String> getFinallist() {
        return finallist;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void readFile(String path) throws IOException {
        String str;
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((str = reader.readLine()) != null) {
                list.add(str);
            }
        }
        for (int i = 0; i <list.size() ; i++) {
            String [] tmp = list.get(i).split(" ");
            for (int j = 0; j <tmp.length ; j++) {

                if (!tmp[j].equals("")) {
                    finallist.add(tmp[j]);
                }
            }
        }

    }

    public void listToSearch(String path) throws IOException {
        readFile(path);
        getFinallist();
    }

}
