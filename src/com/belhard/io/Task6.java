package com.belhard.io;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Date;

public class Task6 {
    public static void main(String[] args) {
        String path = "resources";
        String indent = "  ";
        StringBuilder sb = new StringBuilder();
        System.out.println();
        String content = listFiles(path, sb, indent);
        System.out.println(content);
        writeToFile("resources/out/fileList.txt", content);
    }

    public static String listFiles(String path, StringBuilder sb, String indent){
        File file = new File(path);
        File[] files = file.listFiles();
        for(File elem : files){
            sb.append(indent);
            sb.append(elem.getName() + " (");
            Path p = elem.toPath();
            try{
                sb.append(Files.size(p) + " bytes, ");
                BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
                sb.append("Creation date: " + attr.creationTime()).
                        append(")").
                        append("\n");
            }catch (IOException e){
                e.printStackTrace();
            }
            if(elem.isDirectory()){
                sb.append("---------\n");
                listFiles(elem.getAbsolutePath(), sb, "    " + indent);
                sb.append("---------\n");
            }
        }
        return sb.toString();
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
