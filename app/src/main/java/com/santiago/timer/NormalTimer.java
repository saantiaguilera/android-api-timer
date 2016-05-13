package com.santiago.timer;

public class NormalTimer extends BaseLooper {
	
	private TimerCompletionListener completionListener = null;
	private TimerRefreshListener refreshListener = null;
	
	private int targetTimeMilis = 1000;
	private int timeMilis = 0;
	
	public void setDuration(int targetTimeMilis){
		this.targetTimeMilis = targetTimeMilis;
	}
	
	public void reset() {
		timeMilis = 0;
		start();
	}
	
	@Override
	protected void run() {
		if(targetTimeMilis != 0 && timeMilis >= targetTimeMilis) {
			stop();
			onTimerCompleted();
		} else {
			timeMilis += getRefreshInterval();
			if(timeMilis != 0)
				onRefresh();
		}
	}

	public void setCompletionListener (TimerCompletionListener completionListener) {
		this.completionListener = completionListener;
	}

	public void setRefreshListener (TimerRefreshListener refreshListener) {
		this.refreshListener = refreshListener;
	}
	
	protected void onRefresh() {
		if(refreshListener!=null)
			refreshListener.onTimerRefresh(timeMilis);
	}
	
	protected void onTimerCompleted() {
		if(completionListener!=null)
			completionListener.onTimerCompletion();
	}
	
	public interface TimerCompletionListener {
		void onTimerCompletion();
	}
	
	public interface TimerRefreshListener {
		void onTimerRefresh(int timeMilis);
	}
	
}
