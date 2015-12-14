package com.example.model;

/**
 * This class is for Parseing string to separate file path and link parameters.
 *
 * @author Vitalii Nedzelskyi
 * @vesion 1.0
 */
public class StringParser {

    /**
     *  "path" is displayed file location.
     *  "limit" is displayed number of chars which will be returned
     *  "q" is displayed String which will be represented
     *  "length" is displayed max string length
     *  "metaData" will set to display metadata or not
     */
    private String path;
    private int limit;
    private String q;
    private int length;
    private boolean metaData = false;

    /**
     * Will return value of length
     * @return
     */
    public int getLength() {
        return length;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isMetaData() {
        return metaData;
    }

    public String getQ() {
        return q;
    }

    public String getPath() {
        return path;
    }

    public void parse(String query) {
        String[] URLParts = query.split("\\?");
        this.path = URLParts[0];
        String[] pairs = URLParts[1].split("&");
        for (int i = 0; i < pairs.length; i++ ){
            String[] result = pairs[i].split("=");
            if (result[0].equals("limit")) {
                this.limit = Integer.parseInt(result[1]);
            }
            if (result[0].equals("q")) {
                this.q = result[1];
            }
            if (result[0].equals("length")) {
                this.length = Integer.parseInt(result[1]);
            }
            if (result[0].equals("includeMetaData")) {
                this.metaData = Boolean.valueOf(result[1]);
            }
        }
    }
}
