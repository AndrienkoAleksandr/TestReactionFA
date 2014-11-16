package andrienkoaleksandr.com.github.testreaction.data;

import android.app.Activity;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Andrienko Alexander on 15.11.2014.
 * This singleton class for access to file with game result
 */
public class FileManager {

    private static FileManager fileManager = null;

    private FileManager() {
    }

    public static FileManager getInstance(){
        if (fileManager == null) {
            fileManager = new FileManager();
        }
        return fileManager;
    }

    public void writeToFile(File file, String lines) {
        file.delete();
        createFileIfNotExist(file);
        FileOutputStream writer = null;
        byte[] bytes = lines.getBytes();
        try  {
            writer = new FileOutputStream(file);
            writer.write(bytes);
        } catch (IOException x) {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    Log.e(FileManager.getInstance().getClass().getSimpleName(),
                            "IOException: %s%n", x);
                }
            }
            Log.e(FileManager.getInstance().getClass().getSimpleName(),
                    "IOException: %s%n", x);
        }
    }

    public String readFile(File file) {
        createFileIfNotExist(file);
        StringBuffer line = new StringBuffer();
        FileInputStream reader = null;
        try  {
            reader = new FileInputStream(file);
            int c;
            while ((c = reader.read()) != -1) {
                line.append((char)c);
            }
        } catch (IOException x) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(FileManager.getInstance().getClass().getSimpleName(),
                            "IOException: %s%n", x);
                }
            }
            Log.e(FileManager.getInstance().getClass().getSimpleName(),
                    "IOException: %s%n", x);
        }
        return line.toString();
    }

    public void createFileIfNotExist(File file) {
        try {
            File directories = new File(file.getParent());
            if (!directories.exists()) {
                directories.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            Log.e(FileManager.getInstance().getClass().getSimpleName(), "IOException: %s%n", e);
        }
    }

    public static void main(String[] args) {
        FileManager fm = FileManager.getInstance();
        File file = new File("app\\src\\main\\res\\result\\Statistics.txt");
        fm.createFileIfNotExist(file);
        System.out.println(fm.readFile(file));
        fm.writeToFile(file, "123456");
    }
}
