package com.santiago.timer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public abstract class BaseLooper {
	
	private static final int MESSAGE_UPDATE = 20151105;
	
	private BaseLooperHandler handler = null;
	
	private long refreshTimeMillis = 200;
	
	private boolean running = false;
	
	public BaseLooper() {
		handler =new BaseLooperHandler(this);
	}

	protected long getRefreshInterval(){
		return refreshTimeMillis;
	}
	
	public void setRefreshInterval(long refreshTimeMilis){
		this.refreshTimeMillis = refreshTimeMilis;
	}
	
	public void start() {
		running = true;
		handler.sendEmptyMessage(MESSAGE_UPDATE);
	}
	
	public void stop() {
		running = false;
		handler.removeMessages(MESSAGE_UPDATE);
	}

	protected abstract void run();
	
	private static class BaseLooperHandler extends Handler {
		
		private BaseLooper looper = null;
		
		public BaseLooperHandler(BaseLooper basicLooper) {
			super(Looper.getMainLooper());
			this.looper = basicLooper;
		}
		
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case MESSAGE_UPDATE:
					if(looper.running) {
						looper.run();
						msg = obtainMessage(MESSAGE_UPDATE);
						sendMessageDelayed(msg, looper.refreshTimeMillis);
					}
			}
		}
		
	}
	
}
