package andrienkoaleksandr.com.github.testreaction.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import andrienkoaleksandr.com.github.testreaction.thread.GameThread;
import andrienkoaleksandr.com.github.testreaction.R;
import andrienkoaleksandr.com.github.testreaction.activity.MainActivity;
import andrienkoaleksandr.com.github.testreaction.view.SmartButton;

/**
 * Created by Andrienko Alexander on 26.10.2014.
 *
 */
public class ContentFragment extends Fragment {

    private Button startButton;

    private TextView resultLabel;

    private GameThread gameThread;

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
//        get all buttons
        smartButtons = new ArrayList<SmartButton>();
        TableLayout tableLayout = (TableLayout)getView().findViewById(R.id.content_table);
        TableRow tableRow;
        Button button;
        for(int i = 0; i < MainActivity.getAmountRow(); i++) {
            tableRow = new TableRow(getActivity());
            tableLayout.addView(tableRow);
            for(int j = 0; j < MainActivity.getAmountElementsOfRow(); j++) {
                button = new Button(getActivity());
                button.setText("T");
                button.setBackgroundColor(getResources().getColor(R.color.GREY));
                tableRow.addView(button);
                smartButtons.add(new SmartButton(getActivity(), button));
            }
        }
        gameThread = new GameThread(this);
        startButton = (Button)getView().findViewById(R.id.startButton);
        resultLabel = (TextView)getView().findViewById(R.id.result_label);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (startButton.getText().equals("Stop")) {
                    gameThread.cancel();
                } else {
                    gameThread.start();
                }
            }
        });
    }

    public static ContentFragment newInstance() {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static List<SmartButton> getSmartButtons() {
        return smartButtons;
    }

    public Button getStartButton() {
        return startButton;
    }

    public TextView getResultLabel() {
        return resultLabel;
    }
}
