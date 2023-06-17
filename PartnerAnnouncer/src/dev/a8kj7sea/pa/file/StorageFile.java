package dev.a8kj7sea.pa.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StorageFile {

    public File getStorageFile() {
        return storageFile;
    }

    private File storageFile;

    public StorageFile(String dataFilePath, String dataFileName) {
        this.storageFile = new File(dataFilePath, dataFileName);
        this.storageFile.getParentFile().mkdirs();
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public StorageFile(String dataFileName) {
        this.storageFile = new File(System.getProperty("user.dir"), dataFileName);
        this.storageFile.getParentFile().mkdirs();
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String string) {
        if (this.storageFile.exists() && this.storageFile != null) {
            try (FileWriter fileWriter = new FileWriter(storageFile, true)) {
                fileWriter.append(string).append(System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(storageFile, true);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}