package andrienkoaleksandr.com.github.testreaction;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ControlFragment extends Fragment {

    private Button startButton;

    private TextView resultLabel;

    private ControlFragment fragment = this;

    private GameThread gameThread = new GameThread(fragment);

    public static ControlFragment newInstance() {
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public ControlFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_control, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    public Button getStartButton() {
        return startButton;
    }

    public TextView getResultLabel() {
        return resultLabel;
    }
}
