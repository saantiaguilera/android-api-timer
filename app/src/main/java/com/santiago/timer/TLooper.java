package com.santiago.timer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class TLooper {
	
	private static final int MESSAGE_UPDATE_BAR = 20151105;
	
	private LooperHandler handler = null;
	
	private long loopTimeMilis = 200;
	
	private boolean running = false;
	
	public TLooper() {
		handler =new LooperHandler(this);
	}
	
	public long getLoopTimeMilis(){
		return loopTimeMilis;
	}
	
	public void setLoopTimeMilis(long refreshTimeMilis){
		this.loopTimeMilis = refreshTimeMilis;
	}
	
	public void start() {
		running = true;
		handler.sendEmptyMessage(MESSAGE_UPDATE_BAR);
	}
	
	public void stop() {
		running = false;
		handler.removeMessages(MESSAGE_UPDATE_BAR);
	}

	protected void run() {
		
	}
	
	private static class LooperHandler extends Handler {
		
		private TLooper looper = null;
		
		public LooperHandler(TLooper progressBar) {
			super(Looper.getMainLooper());
			this.looper = progressBar;
		}
		
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case MESSAGE_UPDATE_BAR:
				
				if(looper.running){
					looper.run();
					msg = obtainMessage(MESSAGE_UPDATE_BAR);
					sendMessageDelayed(msg, looper.loopTimeMilis);
				}
				
				break;
			}
			
		}
		
		
	}
	
	
}
