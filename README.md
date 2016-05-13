# android-api-timer
Like CountDownTimer, but the other way round !

Usage:

```Java
private void someMethod() {
  NormalTimer timer = new NormalTimer();
  timer.setDuration(2000); //Default is 1000
  timer.setRefreshInterval(500); //Every 500millis call the loop interface . Optional - Default 200
  timer.setRefreshListener(this); //Loop interface for do something every loopTimeMillis . Optional
  timer.setCompletionListener(this); // Completion interface for do something when ends . Optional
  timer.start(); // Start the timer . Mandatory?
}
``` 
