package com.belhard.io;

import java.io.*;
import java.util.regex.*;
import java.util.Date;

public class Task2 {
    public static void main(String[] args) {
        File source = new File("resources/in/");
        File dest = new File("resources/out/");
        System.out.println();
        try{
            copyDirectory(source, dest);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void log (String path, File source, File dest){
        try (FileWriter writer = new FileWriter(path, true)){
            StringBuilder content = new StringBuilder();
            Date date = new Date();
            content.append(date)
                    .append("\n")
                    .append("Source name: " + source.getName())
                    .append("\n")
                    .append(dest.getName())
                    .append("\n")
                    .append("\n");
            writer.append(content.toString());
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
                log("resources/out/log.txt", source, destination);
            }
        }
    }

    private static void copyFile(File sourceFile, File destinationFile) {
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destinationFile)) {
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buf)) > 0) {
                fos.write(buf, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
