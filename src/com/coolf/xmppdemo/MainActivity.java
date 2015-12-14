package com.coolf.xmppdemo;

import java.io.IOException;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {

//	private String url = "192.168.56.1";
	private String url = "openfire.coolf.com";
	private int port = 8222;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
        		  .setUsernameAndPassword("mp1", "123456")
        		  .setServiceName("coolf.com")
//        		  .setHost("openfire.coolf.com")
        		  .setHost("192.168.2.106")
        		  .setPort(5222)
        		  .setSecurityMode(SecurityMode.disabled)
        		  .build();
        
        SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
        
        		final AbstractXMPPConnection conn2 = new XMPPTCPConnection(config);
        		
        		new Thread(new Runnable() {

					@Override
					public void run() {
						try {
//							Thread.sleep(3000);
							conn2.connect();
							conn2.login();
							Log.e("", "coolf connection.getUser() = " + conn2.getUser());
							
							ChatManager chatmanager = ChatManager.getInstanceFor(conn2);
							Chat newChat = chatmanager.createChat("coolf@openfire.coolf.com");
							newChat.sendMessage("Howdy!");   
//						} catch (InterruptedException e) {
////							// TODO Auto-generated catch block
//							e.printStackTrace();
//							Log.e("", "coolf entry1 e");
						} catch (SmackException e) {
							// TODO Auto-generated catch block
							Log.e("", "coolf entry2 e = " + e);
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							Log.e("", "coolf entry3 e = " + e);
							e.printStackTrace();
						} catch (XMPPException e) {
							// TODO Auto-generated catch block
							Log.e("", "coolf entry4 e = " + e);
							e.printStackTrace();
						}						
					}
        			
        		}).start();
        
//        XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
//        configBuilder.setHost(url);
//        configBuilder.setPort(port);
//        configBuilder.setServiceName("coolf.com");
//        configBuilder.setCompressionEnabled(false);
//        configBuilder.setDebuggerEnabled(false);
//        configBuilder.setSendPresence(true);
////        configBuilder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//        
//        final AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());
//        new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(1500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					Log.e("", "coolf entry1 e");
//				}
//				try {
//					connection.connect();
//				} catch (SmackException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					Log.e("", "coolf entry2 e = " + e);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					Log.e("", "coolf entry3 e = " + e);
//				} catch (XMPPException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					Log.e("", "coolf entry4 e = " + e);
//				}
//				try {
//					connection.login("mp1", "123456", "android");
//				} catch (XMPPException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					Log.e("", "coolf entry5 e = " + e);
//				} catch (SmackException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					Log.e("", "coolf entry6 e = " + e);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					Log.e("", "coolf entry7 e = " + e);
//				}
//			}
//		}).start();
    }
}
