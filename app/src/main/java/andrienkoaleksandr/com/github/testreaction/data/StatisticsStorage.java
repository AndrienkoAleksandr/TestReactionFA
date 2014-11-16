package andrienkoaleksandr.com.github.testreaction.data;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import andrienkoaleksandr.com.github.testreaction.Constant;
import andrienkoaleksandr.com.github.testreaction.activity.MainActivity;
import andrienkoaleksandr.com.github.testreaction.entity.Result;
import andrienkoaleksandr.com.github.testreaction.thread.GameThread;

public class StatisticsStorage {

    private static int id = 0;

    public static List<String> Headlines_Date = new ArrayList<String>();

    public static List<String> Results = new ArrayList<String>();

    private static File file;

    public static void init(Context context) {
        getResult(context);
    }


    public static void addNewResult(float result) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:s");
        String date = dateFormat.format(System.currentTimeMillis());
        Headlines_Date.add(date);
        Results.add("Result " + result + " %");
        Result resultForeSave = new Result(0, result, date, MainActivity.getAmountRow(),
                GameThread.getSpeed());
        saveResult(resultForeSave.toJSON());
    }

    public static void saveResult(final String currentResult) {
                FileManager fileManager = FileManager.getInstance();
                String oldResult = fileManager.readFile(file);
                oldResult = oldResult.substring(0, oldResult.length() - 2) + ", ";
                String newResult = oldResult + currentResult + "]}";
                fileManager.writeToFile(file, newResult);
    }

    public static void getResult(Context context) {
        Headlines_Date.clear();
        Results.clear();
        file = new File(context.getFilesDir(), Constant.PATH_TO_RESULT);
        FileManager fileManager = FileManager.getInstance();
        String result = fileManager.readFile(file);
        if (result.equals("")) {
            fileManager.writeToFile(file,
                    "{\"Result\": [{\"id\": 0, \"result\": 0, \"date\": \"init statistics\"," +
                            "\"sizeTable\": 0, \"speed\": 0}]}");
        }
        try {
            result = fileManager.readFile(file);
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("Result");
            for (int i = 1; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Headlines_Date.add(jsonObj.getString("date"));
                String resultLine =  "Your result: " + String.valueOf(jsonObj.getDouble("result")) +
                        "%\n" + "speed: " + String.valueOf(jsonObj.getInt("speed")) +
                        "\nsize of table: " + String.valueOf(jsonObj.getInt("sizeTable"));
                Results.add(resultLine);
            }
        } catch (JSONException e) {
            Log.e("StatisticsStorage", e.toString());
        }
    }
}
