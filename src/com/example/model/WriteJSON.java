package com.example.model;

import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class WriteJSON {

    public JSONObject result = new JSONObject();

    private String getCreationTime(String path) {
        Path file = Paths.get(path);
        String creationDate = "";
        try {
            BasicFileAttributes attrib = Files.getFileAttributeView(file, BasicFileAttributeView.class).readAttributes();
            creationDate = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss").format(attrib.creationTime().toMillis()).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return creationDate;
    }

    public void addMetaData(String path) {
        File file = new File(path);
        JSONObject metadata = new JSONObject();
            metadata.put("fileName", file.getName());
            metadata.put("fileSize", file.length());
            metadata.put("fileCreationDate", getCreationTime(file.getPath()));
        result.put("metaData", metadata);
    }


    public void addData(String path, int limit, String q, int length,boolean metaData) throws IOException {
        JSONObject JSONresponse = new JSONObject();
        ReadFromFile read = new ReadFromFile();
        read.listToSearch(path);
        ArrayList<String> list = read.getFinallist();
        JSONArray array = new JSONArray();

//        if (metaData){
//            addMetaData(path);
//        }

        for (int i = 0; i <list.size() ; i++) {
            if (limit == 0) {
                for (int j = 0; j <10000 ; j++) {
                    array.add((char)j);
                }
            }else if (list.get(i).contains(q)){
                if (list.get(i).length()>length || length != 0) {
                    array.add(list.get(i));
                }
            }
        }
        result.put("text", array);


    }

    public static void main(String[] args) throws IOException {
        WriteJSON wr = new WriteJSON();
        wr.addMetaData("d:\\mytest2\\mytest.txt");
        wr.addData("d:\\mytest2\\mytest.txt",3, "a", 3,true);

        System.out.println(wr.result.toJSONString());

        FileWriter write = new FileWriter("d:\\mytest2\\1.txt");
        write.write(wr.result.toJSONString());
        write.flush();
        write.close();

    }
}
