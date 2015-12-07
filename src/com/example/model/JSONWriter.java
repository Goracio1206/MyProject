package com.javatask;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWriter {

    private static FileTime getCreationFileTime(File file) throws IOException {
        Path p = Paths.get(file.getAbsolutePath());
        BasicFileAttributes view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
        FileTime fileTime = view.creationTime();

        return fileTime;
    }



    public JSONArray addData(File file, int limit, String q, int length) throws  IOException{


        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((reader.readLine()) != null) {
                list.add(reader.readLine());
                System.out.println(reader.readLine());
            }
        }

        JSONArray array = new JSONArray();
        for (int i = 0; i <list.size() ; i++) {
            array.add(list.get(i).toString());
        }
        return array;
    }

    public JSONObject addMetaData(File file) throws IOException {
        long size = file.length() / 1024 / 1024 / 8;
        String srt = size + "KB";

        JSONObject metaData = new JSONObject();
        metaData.put("fileName",file.getName());
        metaData.put("fileSize", size );
        metaData.put("fileCreationDate", new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss").format(getCreationFileTime(file).toMillis()));

        return metaData;
    }


    public static void main(String[] args) throws IOException {
        File test = new File("D:\\mytest2\\mytest.txt");
        JSONWriter write1  = new JSONWriter();

        try(FileWriter file = new FileWriter(test)) {
            file.write(write1.addMetaData(test).toJSONString());
            write1.addData(test, 5,"",5);
            System.out.println(write1.addData(test, 4, "", 5));
        }
        System.out.println(test.length() + " B");

    }
}



