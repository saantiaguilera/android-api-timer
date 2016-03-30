package com.santiago.timer;

public class TTimer extends TLooper {
	
	private TimerCompletionListener completionListener = null;
	private TimerRefreshListener refreshListener = null;
	
	private int targetTimeMilis = 1000;
	private int timeMilis = 0;
	
	public void setTimeMilis(int timeMilis){
		this.timeMilis = timeMilis;
	}
	
	public void setTargetTimeMilis(int targetTimeMilis){
		this.targetTimeMilis = targetTimeMilis;
	}
	
	public void setCompletionListener (TimerCompletionListener completionListener) {
		this.completionListener = completionListener;
	}
	
	public void setRefreshListener (TimerRefreshListener refreshListener) {
		this.refreshListener = refreshListener;
	}
	
	public void reset() {
		timeMilis = 0;
		start();
	}
	
	@Override
	protected void run() {
		if(targetTimeMilis!=0 && timeMilis>=targetTimeMilis) {
			stop();
			onTimerCompleted();
		} else {
			timeMilis+=getLoopTimeMilis();
			if(timeMilis!=0)
				onRefresh();
		}
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
