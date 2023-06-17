package dev.a8kj7sea.pa.file.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import dev.a8kj7sea.pa.file.StorageFile;

public class FileUtils {

    public static void storeHashMapToFile(String guildId, String channelId, StorageFile storagefile) {

        storagefile.write(guildId + ":" + channelId);

    }

    public static HashMap<String, String> loadHashMapFromFile(File file) {
        HashMap<String, String> hashMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    hashMap.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hashMap;
    }
}
