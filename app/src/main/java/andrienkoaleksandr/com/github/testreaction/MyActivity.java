package andrienkoaleksandr.com.github.testreaction;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
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
    private TableRow controlRow;

    public static List<SmartButton> buttons;

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
        ControlFragment controlFragment = new ControlFragment();
        ControlFragment.newInstance();
        controlFragment.onDetach();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(controlRow.getId(), controlFragment);
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
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
