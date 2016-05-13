# android-api-timer
Like CountDownTimer, but the other way round !

Usage:

```Java
private void someMethod() {
  TTimer timer = new TTimer();
  timer.setTimeMillis(2000); //Starts from 2000 . Optional - Default 0
  timer.setTargetTimeMills(10000); // To 10000 . Mandatory
  timer.setLoopTimeMillis(500); //Every 500millis call the loop interface . Optional - Default 0
  timer.setRefreshListener(this); //Loop interface for do something every loopTimeMillis . Optional
  timer.setCompletionListener(this); // Completion interface for do something when ends . Optional
  timer.start(); // Start the timer . Mandatory?
}
```

Its outdated, I should refactor it 
