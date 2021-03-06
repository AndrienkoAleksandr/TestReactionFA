package andrienkoaleksandr.com.github.testreaction.thread;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import andrienkoaleksandr.com.github.testreaction.Constant;
import andrienkoaleksandr.com.github.testreaction.data.StatisticsStorage;
import andrienkoaleksandr.com.github.testreaction.activity.MainActivity;
import andrienkoaleksandr.com.github.testreaction.fragment.ContentFragment;
import andrienkoaleksandr.com.github.testreaction.view.SmartButton;

/**
 * Created by Andrienko Alexander on 19.10.2014.
 *
 */
public class GameThread extends Activity {

    private static int randomNumber = -1;

    private static List<SmartButton> buttons;

    private String message;

    private int nextNumber = -1;

    private int amountColumn = MainActivity.getAmountElementsOfRow();

    private int amountRow = MainActivity.getAmountRow();

    private int amountSwitch = MainActivity.getAmountFlash();

    private static float successTrying;

    private int counter = 1;

    private TimerTask timerTask;

    private ContentFragment controlFragment;

    private Random random = new Random();

    private static int speed;

    private Context context;

    public GameThread(ContentFragment controlFragment, Context context) {
        this.context = context;
        Bundle bundle = new Bundle();
        Log.v("flash and size", bundle.getInt("flash") + " " + bundle.getInt("size"));
        if (speed == 0) {
            speed = Constant.NORMAL;
        }
        this.controlFragment = controlFragment;
        buttons = ContentFragment.getSmartButtons();
    }

    public void start() {

        controlFragment.getStartButton().setText("Stop");
        controlFragment.getResultLabel().setText("Let's go!!! Quickly!");
        Timer myTimer = new Timer();
        timerTask = new TimerTask() {

            @Override
            public boolean cancel() {
                float result = successTrying / amountSwitch * 100;
                StatisticsStorage.init(context);
                StatisticsStorage.addNewResult(result);
                message = "Result " + result + " %";
                controlFragment.getResultLabel().setText(message);

                buttons.get(randomNumber).setGreyColor();
                randomNumber = -1;
                counter = 1;
                successTrying = 0;
                controlFragment.getStartButton().setText("Start!!!");
                return super.cancel();
            }

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (controlFragment.getStartButton().getText().equals("Start!!!")) {
                        } else {
                            if (randomNumber != -1) {
                                buttons.get(randomNumber).setChecked(false);
                                buttons.get(randomNumber).setClicked(false);
                            }

                            randomNumber = getRandomNumber();

                            buttons.get(randomNumber).setChecked(true);

                            counter++;
                            if (counter > amountSwitch + 1) {
                                cancel();
                            }
                        }
                    }
                });
            }
        };
        myTimer.schedule(timerTask, speed, speed);
    }

    private int getRandomNumber() {
        while (true) {
            nextNumber = random.nextInt(amountColumn * amountRow);
            if (nextNumber != randomNumber) {
                break;
            }
        }
        return nextNumber;
    }

    public static void userClickedButton(boolean isChecked, boolean isClicked) {
        if (isChecked && !isClicked) {
            successTrying++;
            buttons.get(randomNumber).setClicked(true);
        }
    }

    public void cancel() {
        timerTask.cancel();
    }

    public static void setSpeed(int speed) {
        GameThread.speed = speed;
    }

    public static int getSpeed() {
        return speed;
    }
}
