package com.marakana.fibservice;

import android.os.RemoteException;

import com.marakana.fibcommon.IFibService;
import com.marakana.fibnative.FibLib;

public class IFibServiceImpl extends IFibService.Stub {

	@Override
	public long fibJ(long n) throws RemoteException {
		return FibLib.fibJ(n);
	}

	@Override
	public long fibN(long n) throws RemoteException {
		return FibLib.fibN(n);
	}

}
