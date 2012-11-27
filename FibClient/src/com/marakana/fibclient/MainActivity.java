package com.marakana.fibclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.marakana.fibcommon.IFibService;

public class MainActivity extends Activity {
	static final Intent FIB_SERVICE = new Intent(
			"com.marakana.fibcommon.IFibService");
	EditText input;
	TextView output;
	static IFibService fibService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		input = (EditText) findViewById(R.id.input);
		output = (TextView) findViewById(R.id.output);

		bindService(FIB_SERVICE, SERVICE_CONN, BIND_AUTO_CREATE);
	}
	
	static final ServiceConnection SERVICE_CONN = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			fibService = IFibService.Stub.asInterface(binder);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			fibService = null;
		}
	};

	public void onClick(View v) throws RemoteException {
		long n = Long.parseLong(input.getText().toString());

		// Java
		long timeJ = System.currentTimeMillis();
		long resultJ = fibService.fibJ(n);
		timeJ = System.currentTimeMillis() - timeJ;
		output.append(String.format("\n fibJ(%d) = %d (%d ms)", n, resultJ,
				timeJ));

		// Native
		long timeN = System.currentTimeMillis();
		long resultN = fibService.fibN(n);
		timeN = System.currentTimeMillis() - timeN;
		output.append(String.format("\n fibN(%d) = %d (%d ms)", n, resultN,
				timeN));

	}
}
