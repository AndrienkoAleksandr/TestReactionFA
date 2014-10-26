package andrienkoaleksandr.com.github.testreaction.activity.actionbar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

import andrienkoaleksandr.com.github.testreaction.Constant;
import andrienkoaleksandr.com.github.testreaction.GameThread;
import andrienkoaleksandr.com.github.testreaction.fragment.ControlFragment;
import andrienkoaleksandr.com.github.testreaction.R;
import andrienkoaleksandr.com.github.testreaction.view.SmartButton;

/**
 * Created by Andrienko Alexander on 26.10.2014.
 *
 */
public class MyActivity extends ActionBarActivity {
    private static TableRow controlRow;

    public static List<SmartButton> buttons;

    private static ControlFragment controlFragment;

    private FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar();
        setContentView(R.layout.activity_my);

        controlRow = (TableRow) findViewById(R.id.control_row);

        //get all buttons
        buttons = new ArrayList<SmartButton>();
        buttons.add(new SmartButton(this, (Button)findViewById(R.id.button1)));
        buttons.add(new SmartButton(this, (Button)findViewById(R.id.button2)));
        buttons.add(new SmartButton(this, (Button)findViewById(R.id.button3)));
        buttons.add(new SmartButton(this, (Button)findViewById(R.id.button4)));
        buttons.add(new SmartButton(this, (Button)findViewById(R.id.button5)));
        buttons.add(new SmartButton(this, (Button)findViewById(R.id.button6)));
        buttons.add(new SmartButton(this, (Button)findViewById(R.id.button7)));
        buttons.add(new SmartButton(this, (Button)findViewById(R.id.button8)));
        buttons.add(new SmartButton(this, (Button)findViewById(R.id.button9)));
        addControlFragment();
    }

    public void addControlFragment() {
            controlFragment = ControlFragment.newInstance();
            controlFragment.onDetach();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(controlRow.getId(), controlFragment);
            ft.commit();
    }

    @Override
    protected void onPause() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(controlFragment);
        ft.commit();
        super.onPause();
    }

    // Add ActionBar Menu with dropdown of game speed
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // set speed of game
        switch (item.getItemId()) {
            case R.id.item0:
                GameThread.setSpeed(Constant.SLOW);
                return true;
            case R.id.item1:
                GameThread.setSpeed(Constant.NORMAL);
                return true;
            case R.id.item2:
                GameThread.setSpeed(Constant.FAST);
                return true;
            case R.id.item3:
                GameThread.setSpeed(Constant.NIGHTMARE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //todo try to use this future
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_control, container, false);
            return rootView;
        }
    }
}
