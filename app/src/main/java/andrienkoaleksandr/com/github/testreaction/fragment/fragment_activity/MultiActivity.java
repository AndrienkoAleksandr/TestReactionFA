package andrienkoaleksandr.com.github.testreaction.fragment.fragment_activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import andrienkoaleksandr.com.github.testreaction.R;
import andrienkoaleksandr.com.github.testreaction.fragment.StatisticsDetail;
import andrienkoaleksandr.com.github.testreaction.fragment.list_fragments.StatisticsItems;

public class MultiActivity extends FragmentActivity
        implements StatisticsItems.OnHeadlineSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            StatisticsItems firstFragment = new StatisticsItems();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    public void onArticleSelected(int position) {

        StatisticsDetail articleFrag = (StatisticsDetail)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFrag != null) {
            articleFrag.updateArticleView(position);

        } else {
            StatisticsDetail newFragment = new StatisticsDetail();
            Bundle args = new Bundle();
            args.putInt(StatisticsDetail.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}