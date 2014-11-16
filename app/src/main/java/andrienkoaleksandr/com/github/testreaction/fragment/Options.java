package andrienkoaleksandr.com.github.testreaction.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.util.regex.Pattern;

import andrienkoaleksandr.com.github.testreaction.Constant;
import andrienkoaleksandr.com.github.testreaction.R;
import andrienkoaleksandr.com.github.testreaction.activity.MainActivity;
import andrienkoaleksandr.com.github.testreaction.data.FileManager;

public class Options extends Fragment implements View.OnClickListener {

    private EditText amountFlashView;

    private EditText sizeGameTableView;

    private Button save;

    private Button clearStatistics;

    private OnFragmentInteractionListener mListener;

    public static Options newInstance() {
        return new Options();
    }

    public Options() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.options, container, false);
    }

    @Override
    public void onClick(View v) {
        String flash = amountFlashView.getText().toString();
        String size = sizeGameTableView.getText().toString();
        int result;
        switch (v.getId()) {
            case R.id.save:
                if ((result = checkData(flash)) != -1) {
                    MainActivity.setAmountFlash(result);
                    saveAmountFlash(result);
                }
                if ((result = checkData(size)) != -1) {
                    MainActivity.setAmountElementsOfRow(result);
                    MainActivity.setAmountRow(result);
                    saveSize(result);
               }
                break;
            case R.id.clear_statistics:
                FileManager fileManager = FileManager.getInstance();
                fileManager.writeToFile(
                        new File(getActivity().getApplicationContext().getFilesDir(),
                                Constant.PATH_TO_RESULT), "");
                break;
        }
    }

    private int checkData(String line) {
        int result = -1;
        //check that line consist of number
        if(Pattern.matches("[1-9]+", line)) {
            result = Integer.parseInt(line);
            //check user entered not 0
            if (result == 0) {
                return -1;
            }
        }
        return result;
    }

    private void saveSize(int size) {
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Constant.OPTION_SIZE, size);
        editor.commit();
    }

    private void saveAmountFlash(int amountFlash) {
        SharedPreferences preferences = getActivity().getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Constant.OPTION_AMOUNT_OF_FLASH, amountFlash);
        editor.commit();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        amountFlashView = (EditText) getView().findViewById(R.id.flash);
        sizeGameTableView = (EditText) getView().findViewById(R.id.size);
        save = (Button) getView().findViewById(R.id.save);
        clearStatistics = (Button) getView().findViewById(R.id.clear_statistics);
        save.setOnClickListener(this);
        clearStatistics.setOnClickListener(this);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }
}
