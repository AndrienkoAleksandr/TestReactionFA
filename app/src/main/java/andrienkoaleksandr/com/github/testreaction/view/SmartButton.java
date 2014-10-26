package andrienkoaleksandr.com.github.testreaction.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import andrienkoaleksandr.com.github.testreaction.GameThread;
import andrienkoaleksandr.com.github.testreaction.R;

/**
 * Created by Andrienko Alexander on 17.10.2014.
 *
 */
public class SmartButton extends Button {

    private boolean isChecked;

    private boolean isClicked;

    private Button button ;

    public SmartButton(Context context, Button button) {
        super(context);
        this.button = button;
        initClickHandler();
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        if (isChecked) {
            setRedColor();
        } else {
            setGreyColor();
        }
    }

    public void setRedColor() {
        button.setBackgroundColor(getResources().getColor(R.color.RED_BUTTON));
    }

    public void setGreyColor() {
        button.setBackgroundColor(getResources().getColor(R.color.GREY));
    }

    private void initClickHandler() {

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.v("Clicked!!!!", "!!!!!!");
                GameThread.userClickedButton(isChecked(), isClicked);
            }
        });
    }

    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }
}
