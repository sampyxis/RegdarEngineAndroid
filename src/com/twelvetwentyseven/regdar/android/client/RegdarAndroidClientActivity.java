package com.twelvetwentyseven.regdar.android.client;

import com.twelvetwentyseven.regdar.android.connection.regdarConnection;
import com.twelvetwentyseven.regdar.android.widget.RegdarUserWidget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegdarAndroidClientActivity extends Activity {
    /** Called when the activity is first created. */
	private Button loginButton;
	private TextView userNameTxt;
	private TextView userPasswordTxt;
	private RegdarUserWidget regdarUser;
	
    @SuppressWarnings("unused")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        regdarUser = (RegdarUserWidget)findViewById(R.id.regdarUserWidgetTest);
        
        // Get the button
        loginButton = (Button)findViewById(R.id.loginBtn);
        userNameTxt = (TextView)findViewById(R.id.userNameTxtMain);
        userPasswordTxt = (TextView)findViewById(R.id.passwordTxtMain);
        
		regdarConnection newConnection = regdarConnection.getInstance();
        
        loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Make sure we have a user id and password
				//
				String userName = "";
				String userPass = "";
				userName = userNameTxt.getText().toString();
				userPass = userPasswordTxt.getText().toString();
				
				regdarUser.loginUser("sam", "ss");
				
				regdarUser.setUserName(userName);
				
			}
		});
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        //regdarUser.connectClient();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        // Stop the client
       // regdarUser.disconnect();
    }
    
    
}