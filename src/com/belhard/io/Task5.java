package com.belhard.io;

import java.io.*;

public class Task5 {
    public static void main(String[] args) {
        String path = "resources/in/Files.java";
        System.out.println();

        String content = readFromFile(path);

        content = formatJava(content);
        path = "resources/out/Files.java";
        writeToFile(path, content);

    }

    public static String readFromFile(String path) {
        StringBuilder content = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(path);
             BufferedInputStream bis = new BufferedInputStream(fis);
             InputStreamReader isr = new InputStreamReader(bis);
             BufferedReader br = new BufferedReader(isr)) {
            byte[] buffer = new byte[1024];
            String temp;
            while ((temp = br.readLine()) != null) {
                content = content.append(temp).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void writeToFile(String path, String content) {
        try (FileWriter writer = new FileWriter(path)) {
            File file = new File(path);
            writer.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatJava(String str){
        String regex = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/";
        str = str.replaceAll(regex, "" );
        return str;
    }
}
