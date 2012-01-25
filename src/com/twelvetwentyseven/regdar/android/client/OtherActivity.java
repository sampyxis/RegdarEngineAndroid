package com.twelvetwentyseven.regdar.android.client;

import com.twelvetwentyseven.regdar.android.widget.ActionBarRegdar;
import com.twelvetwentyseven.regdar.android.widget.ActionBarRegdar.AbstractAction;
import com.twelvetwentyseven.regdar.android.widget.ActionBarRegdar.Action;
import com.twelvetwentyseven.regdar.android.widget.ActionBarRegdar.IntentAction;
import com.twelvetwentyseven.regdar.android.widget.RegdarUserWidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class OtherActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_test);

        ActionBarRegdar actionBar = (ActionBarRegdar) findViewById(R.id.actionbar);
        // You can also assign the title programmatically by passing a
        // CharSequence or resource id.
        //actionBar.setTitle(R.string.some_title);
        actionBar.setHomeAction(new IntentAction(this, HomeActivity.createIntent(this), R.drawable.ic_title_home_default));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.addAction(new IntentAction(this, createShareIntent(), R.drawable.ic_title_share_default));
        actionBar.addAction(new ExampleAction());
        
        // Set the user name
        RegdarUserWidget regdarUser = (RegdarUserWidget) findViewById(R.id.regdarUserWidgetTest);
        
        regdarUser.setUserName("Sam Harper");
    }

    private Intent createShareIntent() {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Shared from the ActionBar widget.");
        return Intent.createChooser(intent, "Share");
    }

    private class ExampleAction extends AbstractAction {

        public ExampleAction() {
            super(R.drawable.ic_title_export_default);
        }

        @Override
        public void performAction(View view) {
            Toast.makeText(OtherActivity.this,
                    "Example action", Toast.LENGTH_SHORT).show();
        }

    }

}