package com.marakana.fibcommon;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {
	public static final int ALGORITHM_JAVA = 1;
	public static final int ALGORITHM_NATIVE = 2;
	
	private int algorithm;
	private long n;

	public Request(int algorithm, long n) {
		super();
		this.algorithm = algorithm;
		this.n = n;
	}
	
	// --- Parcelable Stuff ---
	public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {
		@Override
		public Request createFromParcel(Parcel source) {
			return new Request( source.readInt(), source.readLong() );
		}

		@Override
		public Request[] newArray(int size) {
			return new Request[size];
		}
	};
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(algorithm);
		dest.writeLong(n);
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	// --- Setters & Getters ---
	public int getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(int algorithm) {
		this.algorithm = algorithm;
	}
	public long getN() {
		return n;
	}
	public void setN(long n) {
		this.n = n;
	}
}
