package com.marakana.fibclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.marakana.fibcommon.FibManager;
import com.marakana.fibcommon.IFibListener;
import com.marakana.fibcommon.Request;

public class MainActivity extends Activity {
	EditText input;
	static TextView output;
	FibManager fibManager;
	static long time;

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
		time = System.currentTimeMillis();
		request.setAlgorithm(Request.ALGORITHM_JAVA);
		fibManager.asyncFib( request, LISTENER );
	}
	
	private static final IFibListener LISTENER = new IFibListener.Stub() {
		
		@Override
		public void onResponse(long result) throws RemoteException {
			time = System.currentTimeMillis() - time;
			Message msg = HANDLER.obtainMessage(47, time);
			HANDLER.sendMessage(msg);
		}
	};
	
	private static final Handler HANDLER = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			long result = (Long)msg.obj;
			output.append(String.format("\n fibJ() = %d (%d ms)", result,
					time));
		}
		
	};
}
