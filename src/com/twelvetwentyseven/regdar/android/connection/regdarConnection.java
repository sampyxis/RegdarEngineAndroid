package com.twelvetwentyseven.regdar.android.connection;


import java.io.IOException;


import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.twelvetwentyseven.regdar.android.untility.miniLog;
import com.twelvetwentyseven.regdar.common.Network;
import com.twelvetwentyseven.regdar.common.Network.RegisterName;
import com.twelvetwentyseven.regdar.common.Network.UpdateNames;

public class regdarConnection {
	Client client;
	private String host = "10.0.2.2";
	// Singleton
	private static regdarConnection singleInstance = null;
	
	public regdarConnection(){	
		
        // Register the client
        client = new Client();
        client.start();
        Network.register(client);
        
        client.addListener(new Listener() {
			public void connected (Connection connection) {
				RegisterName registerName = new RegisterName();
				registerName.name = "Android User";
				client.sendTCP(registerName);
			}
			
			

			public void received (Connection connection, Object object) {
				if (object instanceof UpdateNames) {
					// Recived data from the network - deal with it
					//UpdateNames updateNames = (UpdateNames)object;
					//setUserName(Integer.toString(client.getID()));
					miniLog.Log("recieved");
					return;
				}

			}
					
			
			public void disconnected (Connection connection) {

			}
		});
        
		new Thread("Connect") {
			public void run () {
				try {
					client.connect(5000, host, Network.port);
					// Server communication after connection can go here, or in Listener#connected().
				} catch (IOException ex) {
					ex.printStackTrace();
					//System.exit(1);
				}
			}
		}.start();
	}
	
	// Get single Instance
	public static regdarConnection getInstance() {
		if(null==singleInstance){
			singleInstance = new regdarConnection();
		}
		return singleInstance;
	}	
	
    public void connectServer(){
		// We'll do the connect on a new thread so the ChatFrame can show a progress bar.
		// Connecting to localhost is usually so fast you won't see the progress bar.
		new Thread("Connect") {
			public void run () {
				try {
					client.connect(5000, host, Network.port);
					// Server communication after connection can go here, or in Listener#connected().
				} catch (IOException ex) {
					ex.printStackTrace();
					//System.exit(1);
				}
			}
		}.start();

    }
    
    public void updateName(){
    	
    }

}
