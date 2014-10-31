package andrienkoaleksandr.com.github.testreaction.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

import andrienkoaleksandr.com.github.testreaction.R;
import andrienkoaleksandr.com.github.testreaction.activity.actionbar.MyActivity;
import andrienkoaleksandr.com.github.testreaction.view.SmartButton;

/**
 * Created by Andrienko Alexander on 26.10.2014.
 *
 */
public class ContentFragment extends Fragment {

    private static List<SmartButton> smartButtons;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //get all buttons
        smartButtons = new ArrayList<SmartButton>();
        TableLayout tableLayout = (TableLayout)getView().findViewById(R.id.table_content);
        TableRow tableRow;
        Button button;
        for(int i = 0; i < MyActivity.getAmountRow(); i++) {
            tableRow = new TableRow(getActivity());
            tableLayout.addView(tableRow);
            for(int j = 0; j < MyActivity.getAmountElementsOfRow(); j++) {
                button = new Button(getActivity());
                button.setText("T");
                button.setBackgroundColor(getResources().getColor(R.color.GREY));
                tableRow.addView(button);
                smartButtons.add(new SmartButton(getActivity(), button));
            }
        }
    }

    public static List<SmartButton> getSmartButtons() {
        return smartButtons;
    }
}
