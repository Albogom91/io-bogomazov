package com.belhard.io;

import java.io.*;
import java.net.URL;

public class Task4 {
    public static void main(String[] args) {
        String content = new String();
        try{
            URL url = new URL("https://www.cia.gov/");
            content = readFromURL(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        content = formatTags(content);
        writeToFile("resources/out/cia.html", content);
    }

    public static String readFromURL(URL url){
        StringBuilder sb = new StringBuilder();
        try(InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr)) {
            String temp;
            while((temp = br.readLine()) != null){
                sb.append(temp);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String formatTags(String str){
        String regex = "(<([a-z]+)*[^/]*?>)";
        str = str.replaceAll(regex, "\n$1");
        return str;
    }

    public static void writeToFile(String path, String content) {
        try (FileWriter writer = new FileWriter(path)) {
            File file = new File(path);
            writer.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
