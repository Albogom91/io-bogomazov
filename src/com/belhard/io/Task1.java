package com.belhard.io;

import java.io.*;
import java.nio.file.Files;

public class Task1 {
    public static void main(String[] args) {
        String path = "resources/in/text.txt";
        System.out.println();
        System.out.println();
        String content = readFromFile(path);
        //res = deleteExtraEmptySpaces(res);
        content = deleteExtraEmptySpaces(content);
        content = formatParagraphs(content);
        path = "resources/out/formattedText.txt";
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
                content = content.append(temp);
                content = content.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void writeToFile(String path, String content) {
        try (FileWriter writer = new FileWriter(path)) {
            File file = new File(path);
            if (!file.exists()) {
                createFile(file);
            }
            writer.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String deleteExtraEmptySpaces(String str) {
        String regex = "\\s{2,}";
        str = str.replaceAll(regex, " ");
        regex = "\\s+(?=\\p{Punct})";
        str = str.replaceAll(regex, "");//.trim();
        return str;
    }

    public static String formatParagraphs(String str) {
        String regex = "\\.\\s+";
        str = str.replaceAll(regex, ".\n\n ");
        return str;
    }

    public static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
