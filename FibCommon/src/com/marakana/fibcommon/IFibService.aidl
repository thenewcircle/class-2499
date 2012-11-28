package com.marakana.fibcommon;

import com.marakana.fibcommon.Request;
import com.marakana.fibcommon.IFibListener;

interface IFibService {
	long fibJ(long n);
	long fibN(long n);
	
	long fib(in Request request);
	
	oneway void asyncFib(in Request request, in IFibListener listener);
}