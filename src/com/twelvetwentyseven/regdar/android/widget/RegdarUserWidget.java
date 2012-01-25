package com.twelvetwentyseven.regdar.android.widget;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.twelvetwentyseven.regdar.android.client.R;
import com.twelvetwentyseven.regdar.common.Network;
import com.twelvetwentyseven.regdar.common.Network.RegisterName;
import com.twelvetwentyseven.regdar.common.Network.UpdateNames;
import com.twelvetwentyseven.regdar.common.Network.login;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RegdarUserWidget extends RelativeLayout implements OnClickListener{
	
    private LayoutInflater mInflater;
    private RelativeLayout mBarView;
    private TextView mUserText;
    public Client client;
    private View mBackIndicator;

	public RegdarUserWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBarView = (RelativeLayout) mInflater.inflate(R.layout.regdar_user, null);
        addView(mBarView);
        
        mBackIndicator = mBarView.findViewById(R.id.regdar_home_is_back);
        //Turn off the indicator for now
        setDisplayHomeAsUpEnabled(false);
        
        // Get a referrence to the text view
        mUserText = (TextView) mBarView.findViewById(R.id.regdar_user_name_text);
        mUserText.setText("Please Login");
        
//        // Register the client
        //connection = new regdarConnection();
//        client = new Client();
//        client.start();
//        Network.register(client);
//        client.addListener(new Listener() {
//			public void connected (Connection connection) {
//				RegisterName registerName = new RegisterName();
//				registerName.name = "Android User";
//				client.sendTCP(registerName);
//			}
//
//			public void received (Connection connection, Object object) {
//				if (object instanceof UpdateNames) {
//					// Recived data from the network - deal with it
//					UpdateNames updateNames = (UpdateNames)object;
//					setUserName("me");
//					return;
//				}
//
//			}
//			public void disconnected (Connection connection) {
//
//			}
//		});
        
        
		// We'll do the connect on a new thread so the ChatFrame can show a progress bar.
		// Connecting to localhost is usually so fast you won't see the progress bar.
//		new Thread("Connect") {
//			public void run () {
//				try {
//					client.connect(5000, "10.0.2.2", Network.port);
//					// Server communication after connection can go here, or in Listener#connected().
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//			}
//		}.start();
        
	}

	// Network functions
	public void loginUser(String userName, String password){
		login tryLogin = new login();
		tryLogin.connectedID = client.getID();
		client.sendTCP(tryLogin);
	}
	// end network functions
	
	//Function to set user name
	public void setUserName(String userName){
		mUserText.setText(userName);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	public void disconnect(){
		//client.stop();
	}
	
	
    /* Emulating Honeycomb, setdisplayHomeAsUpEnabled takes a boolean
     * and toggles whether the "home" view should have a little triangle
     * indicating "up" */
    public void setDisplayHomeAsUpEnabled(boolean show) {
        mBackIndicator.setVisibility(show? View.VISIBLE : View.GONE);
    }

}
