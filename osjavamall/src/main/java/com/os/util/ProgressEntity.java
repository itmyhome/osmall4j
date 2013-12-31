package com.os.util;
public class ProgressEntity{
	private double pBytesRead = 0;
	private double pContentLength = 0;
	private int pItems;
	public double getpBytesRead() {
		return pBytesRead;
	}
	public void setpBytesRead(long pBytesRead) {
		this.pBytesRead = pBytesRead;
	}
	public double getpContentLength() {
		return pContentLength;
	}
	public void setpContentLength(long pContentLength) {
		this.pContentLength = pContentLength;
	}
	public int getpItems() {
		return pItems;
	}
	public void setpItems(int pItems) {
		this.pItems = pItems;
	}
	
	public double getPercent(){
		return pBytesRead/pContentLength * 100;
	}
	@Override
	public String toString() {
		return "ProgressEntity [pBytesRead=" + pBytesRead + ", pContentLength="
				+ pContentLength + ", pItems=" + pItems + "]";
	}
}
