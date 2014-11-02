package andrienkoaleksandr.com.github.testreaction;

import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StatisticsStorage {

    private static SharedPreferences sPref;

    private static int id = 0;

    public static List<String> Headlines = new ArrayList<String>();

    public static List<String> Results = new ArrayList<String>();

    public static void addNewResult(String result) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G HH:mm:s");
        String date = dateFormat.format(System.currentTimeMillis());
        Headlines.add(date);
        Results.add(result);
    }
}
