package com.marakana.fibclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.marakana.fibcommon.FibManager;
import com.marakana.fibcommon.Request;

public class MainActivity extends Activity {
	EditText input;
	TextView output;
	FibManager fibManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		input = (EditText) findViewById(R.id.input);
		output = (TextView) findViewById(R.id.output);

		fibManager = new FibManager(this);
	}
	

	public void onClick(View v) throws RemoteException {
		long n = Long.parseLong(input.getText().toString());
		Request request = new Request(-1, n);
		
		// Java
		long timeJ = System.currentTimeMillis();
		request.setAlgorithm(Request.ALGORITHM_JAVA);
		long resultJ = fibManager.fib( request );
		timeJ = System.currentTimeMillis() - timeJ;
		output.append(String.format("\n fibJ(%d) = %d (%d ms)", n, resultJ,
				timeJ));

		// Native
		long timeN = System.currentTimeMillis();
		request.setAlgorithm(Request.ALGORITHM_NATIVE);
		long resultN = fibManager.fib( request );
		timeN = System.currentTimeMillis() - timeN;
		output.append(String.format("\n fibN(%d) = %d (%d ms)", n, resultN,
				timeN));

	}
}
