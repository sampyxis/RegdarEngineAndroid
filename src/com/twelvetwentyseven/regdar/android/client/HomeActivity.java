package com.twelvetwentyseven.regdar.android.client;

import com.twelvetwentyseven.regdar.android.widget.ActionBarRegdar;
import com.twelvetwentyseven.regdar.android.widget.ActionBarRegdar.Action;
import com.twelvetwentyseven.regdar.android.widget.ActionBarRegdar.IntentAction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_char);

        final ActionBarRegdar actionBar = (ActionBarRegdar) findViewById(R.id.actionBarRegdar);
        //actionBar.setHomeAction(new IntentAction(this, createIntent(this), R.drawable.ic_title_home_demo));
        actionBar.setTitle("Home");

        final Action shareAction = new IntentAction(this, createShareIntent(), R.drawable.ic_title_share_default);
        actionBar.addAction(shareAction);
        final Action otherAction = new IntentAction(this, new Intent(this, OtherActivity.class), R.drawable.ic_title_export_default);
        actionBar.addAction(otherAction);

        Button startProgress = (Button) findViewById(R.id.start_progress);
        startProgress.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.setProgressBarVisibility(View.VISIBLE);
            }
        });
        
        Button stopProgress = (Button) findViewById(R.id.stop_progress);
        stopProgress.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.setProgressBarVisibility(View.GONE);
            }
        });

        Button removeActions = (Button) findViewById(R.id.remove_actions);
        removeActions.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.removeAllActions();
            }
        });

        Button addAction = (Button) findViewById(R.id.add_action);
        addAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.addAction(new Action() {
                    @Override
                    public void performAction(View view) {
                        Toast.makeText(HomeActivity.this, "Added action.", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public int getDrawable() {
                        return R.drawable.ic_title_share_default;
                    }
                });
            }
        });

        Button removeAction = (Button) findViewById(R.id.remove_action);
        removeAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int actionCount = actionBar.getActionCount();
                actionBar.removeActionAt(actionCount - 1);
                Toast.makeText(HomeActivity.this, "Removed action." , Toast.LENGTH_SHORT).show();
            }
        });

        Button removeShareAction = (Button) findViewById(R.id.remove_share_action);
        removeShareAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.removeAction(shareAction);
            }
        });
    }

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return i;
    }

    private Intent createShareIntent() {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Shared from the ActionBar widget.");
        return Intent.createChooser(intent, "Share");
    }
}