package com.belhard.io;

import java.io.*;
import java.nio.file.*;
import java.util.Date;

public class Task3 {
    public static void main(String[] args) {
        File sourceDir = new File("resources/in/");
        File destDir = new File("resources/out/");
        try{
            copyDirectory(sourceDir, destDir);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private static void copyDirectory(File sourceDir, File destDir) throws IOException{
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        for (String f : sourceDir.list()) {
            File source = new File(sourceDir, f);
            File destination = new File(destDir, f);
            if (f.endsWith("jpg")) {
                copyFile(source, destination);
            }
        }
    }

    public static void copyFile(File path, File dir) {
        Path sourceFile = path.toPath();
        Path targetDir = dir.toPath();
        try {
            Files.copy(sourceFile, targetDir);
            log("resources/out/nioLog.txt", sourceFile, targetDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log (String path, Path source, Path dest){
        try (FileWriter writer = new FileWriter(path, true)){
            StringBuilder content = new StringBuilder();
            Date date = new Date();
            content.append(date)
                    .append("\n")
                    .append("Source name: " + source.getFileName())
                    .append("\n")
                    .append(dest.getFileName())
                    .append("\n")
                    .append("Size: " + Files.size(dest))
                    .append("\n")
                    .append("\n");
            writer.append(content.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
