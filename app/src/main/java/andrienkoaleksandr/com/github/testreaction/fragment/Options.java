package andrienkoaleksandr.com.github.testreaction.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

import andrienkoaleksandr.com.github.testreaction.R;
import andrienkoaleksandr.com.github.testreaction.activity.MainActivity;

public class Options extends Fragment implements View.OnClickListener {

    private EditText amountFlashView;

    private EditText sizeGameTableView;

    private Button save;

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
        Bundle args = new Bundle();
        switch (v.getId()) {
            case R.id.save:
            if ((result = checkData(flash)) != -1) {
                MainActivity.setAmountFlash(result);
            }
            if ((result = checkData(size)) != -1) {
                MainActivity.setAmountElementsOfRow(result);
                MainActivity.setAmountRow(result);
            }
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        amountFlashView = (EditText) getView().findViewById(R.id.flash);
        sizeGameTableView = (EditText) getView().findViewById(R.id.size);
        save = (Button) getView().findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }
}
