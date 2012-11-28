package com.marakana.fibservice;

import android.os.RemoteException;

import com.marakana.fibcommon.IFibService;
import com.marakana.fibcommon.Request;
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

	@Override
	public long fib(Request request) throws RemoteException {
		switch( request.getAlgorithm() ) {
		case Request.ALGORITHM_JAVA:
			return FibLib.fibJ( request.getN() );
		case Request.ALGORITHM_NATIVE:
			return FibLib.fibN( request.getN() );
		default:
			throw new IllegalArgumentException("Unknown algorithm");
		}
	}

}
