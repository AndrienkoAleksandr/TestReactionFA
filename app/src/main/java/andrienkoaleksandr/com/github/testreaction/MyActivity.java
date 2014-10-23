package andrienkoaleksandr.com.github.testreaction;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    private static TableRow controlRow;

    public static List<SmartButton> buttons;

    private static ControlFragment controlFragment;

    private FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            controlFragment = new ControlFragment();
            ControlFragment.newInstance();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
