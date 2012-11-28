package com.marakana.fibcommon;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

public class FibManager {
	private static final Intent FIB_SERVICE = new Intent(
			"com.marakana.fibcommon.IFibService");

	private Context context;
	private static IFibService fibService;

	public FibManager(Context context) {
		super();
		this.context = context;

		context.bindService(FIB_SERVICE, SERVICE_CONN, Context.BIND_AUTO_CREATE);

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

	// --- Proxy calls ---

	public long fib(Request request) {
		try {
			return fibService.fib(request);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
