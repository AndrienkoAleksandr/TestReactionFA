package andrienkoaleksandr.com.github.testreaction.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import andrienkoaleksandr.com.github.testreaction.R;

public class Animation extends Activity
        implements android.view.animation.Animation.AnimationListener, View.OnClickListener{

    private ImageView image;
    private Button startAnimation;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        image = (ImageView) findViewById(R.id.imageView);
        startAnimation = (Button) findViewById(R.id.startAnim);
        startAnimation.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.animation, menu);
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

    @Override
    public void onAnimationStart(android.view.animation.Animation animation) {

    }

    @Override
    public void onAnimationEnd(android.view.animation.Animation animation) {

    }

    @Override
    public void onAnimationRepeat(android.view.animation.Animation animation) {

    }

    @Override
    public void onClick(View v) {

        android.view.animation.Animation anim = null;
        switch (v.getId()) {
            case R.id.startAnim:
                //first animation
                switch (counter) {
                    case 0:
                        anim = AnimationUtils.loadAnimation(this, R.anim.bounce);
                        image.setAnimation(anim);
                        image.startAnimation(anim);
                        break;
                    case 1:
                        //second animation
                        anim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                        image.setAnimation(anim);
                        image.startAnimation(anim);
                        break;
                    case 2:
                        anim = AnimationUtils.loadAnimation(this, R.anim.fade_out);
                        image.setAnimation(anim);
                        image.startAnimation(anim);
                        break;
                    case 3:
                        //second animation
                        anim = AnimationUtils.loadAnimation(this, R.anim.flip);
                        image.setAnimation(anim);
                        image.startAnimation(anim);
                        break;
                }
                break;
        }
        counter++;
        if (counter == 3) {
            counter = 0;
        }
    }
}
