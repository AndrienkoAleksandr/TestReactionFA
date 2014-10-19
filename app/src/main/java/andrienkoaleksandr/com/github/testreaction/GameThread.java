package andrienkoaleksandr.com.github.testreaction;

import android.app.Activity;
import android.os.Handler;
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

    private int counter = 1;

    private static int randomNumber = -1;

    private static List<SmartButton> buttons;

    private String message;

    private final int amountSwitch = 5;

    private int nextNumber = -1;

    private final int amountColumn = 3;

    private final int amountRow = 3;

    private static float successTrying;

    public GameThread(List<SmartButton> buttons) {
        this.buttons = buttons;
    }

    public void start() {
//        final Handler handler = new Handler();
//        final Runnable r = new Runnable() {
//            @Override
//            public void run() {
//
//                    if (randomNumber != -1) {
//                        buttons.get(randomNumber).setCornLinkColor();
//                        buttons.get(randomNumber).setClicked(false);
//                    }
//
//                    randomNumber = getRandomNumber();
//
//                    buttons.get(randomNumber).setRedColor();
//                    counter++;
//
//                    Log.v("Look at me !!!!!", counter + "");
//
//
//                if (counter > amountSwitch + 1) {
//                    return;
//                }
//
//            }
//        };
//        handler.post(r);handler.postDelayed(r, 1000);

        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
                             @Override
                             public void run() {
                                 runOnUiThread(new Runnable() {
                                     @Override
                                     public void run() {

                                             if(i%2 == 1) {
                                                 buttons.get(0).setCornLinkColor();
                                             } else {
                                                 buttons.get(0).setRedColor();
                                             }
                                         i++;

                                             Log.v("Look at me !!!!!", i + "");

                                     }
                                 });
                             }
                         },
            1000, 1000);
    }

    private int getRandomNumber() {
        while (true) {
            Random random = new Random();
            nextNumber = random.nextInt(amountColumn * amountRow);
            if (nextNumber != randomNumber) {
                break;
            }
        }
        return nextNumber;
    }

    public void cancel() {
//        message = "Your winning percentage " + successTrying / amountSwitch * 100 + " %";
//        reactionView.setResult(message);
//        super.cancel();
//        buttons.get(randomNumber)
//                .setChecked(false);
//        randomNumber = -1;
//        counter = 1;
//        successTrying = 0;
//        reactionView.getStartButton().setText("Start!!!");
    }

    public static void userClickedButton(boolean isChecked, boolean isClicked) {
        if (isChecked && !isClicked) {
            successTrying++;
            buttons.get(randomNumber).setClicked(true);
        }
    }
}
