package andrienkoaleksandr.com.github.testreaction;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import static andrienkoaleksandr.com.github.testreaction.R.color.*;

/**
 * Created by Andrienko Alexander on 17.10.2014.
 *
 */
public class SmartButton extends Button {
    public SmartButton(Context context) {
        super(context);
    }

    private boolean isChecked;

    private boolean isClicked;

    private Button button ;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        if (isChecked) {
            setRedColor();
        } else {
            setCornLinkColor();
        }
    }

    private void setRedColor() {
        button.setBackgroundColor(RED_BUTTON);
    }

    private void setCornLinkColor() {
        button.setBackgroundColor(CORNSILK_BUTTON);
    }

    private void initClickHandler() {

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MyActivity.userClickedButton(isChecked(), isClicked);
            }
        });
    }

    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

}
