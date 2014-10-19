package andrienkoaleksandr.com.github.testreaction;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Andrienko Alexander on 19.10.2014.
 *
 */
public class GameThread extends Activity {

    int i = 0;

    private boolean isClicked;

    private static int randomNumber = -1;

    private static List<SmartButton> buttons;

    private String message;

    private final int amountSwitch = 5;

    private int nextNumber = -1;

    private final int amountColumn = 3;

    private final int amountRow = 3;

    private static float successTrying;

    private int counter = 1;

    private TimerTask timerTask;

    private ControlFragment controlFragment;

    private Random random = new Random();

    public GameThread(List<SmartButton> buttons, ControlFragment controlFragment) {
        this.buttons = buttons;
        this.controlFragment = controlFragment;
    }

    public void start() {
        controlFragment.startButton.setText("Stop");
//        controlFragment.setResult("Come on!!! Let's go!!! Quickly!");

        Timer myTimer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public boolean cancel() {
                message = "Your winning percentage " +
                        successTrying / amountSwitch * 100 + " %";
//                                 reactionView.setResult(message);
                buttons.get(randomNumber).setGreyColor();
                randomNumber = -1;
                counter = 1;
                successTrying = 0;
                controlFragment.startButton.setText("Start!!!");
                return super.cancel();
            }

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (randomNumber != -1) {
                            buttons.get(randomNumber).setGreyColor();
                            buttons.get(randomNumber).setClicked(false);
                        }

                        randomNumber = getRandomNumber();

                        buttons.get(randomNumber).setRedColor();
                        counter++;
                        Log.v("!!!!!!!!!!!!!!!!!", counter + " " + randomNumber);
                        if (counter > amountSwitch + 1) {
                            cancel();
                        }
                    }
                });
            }
        };
        myTimer.schedule(timerTask, 1000, 1000);
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
}
