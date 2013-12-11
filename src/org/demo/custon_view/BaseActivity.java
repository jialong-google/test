package org.demo.custon_view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class BaseActivity extends Activity {
	/** Called when the activity is first created. */
	private boolean isStop=true;//
	
	private Thread t;
	private int sleepTime=1*1000;
	BroadcastReceiver receiver;
	IntentFilter filter;
	private void resetThread() {
		t = new Thread() {
			public void run() {
				while (!isStop) {
					try {
						sleep(sleepTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					}
//					if(!isStop){
						System.out.println("Start Lock");
	//					startActivity(new Intent(BaseActivity.this,
		//						GesturePassActivity.class));
//						isLock = true;
//					}
					break;
				}
			}

		};
		t.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	
	@Override
	protected void onResume() {
		registerReceiver(receiver, filter);
		isStop=false;
			resetThread();
		super.onResume();
	}

	@Override
	protected void onStop() {
		unregisterReceiver(receiver);
		StopThread();
		super.onStop();
	}

	private void StopThread() {
		if (t != null ) {
			isStop=true;
			t.interrupt();
			t = null;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//不允许旋转屏幕
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		receiver=new LockBroadcastReceiver();
		filter=new IntentFilter();
		filter.addAction("android.intent.action.SCREEN_OFF");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(t!=null){
			t.interrupt();
		}
		return super.onTouchEvent(event);
	}

	
	class LockBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if("android.intent.action.SCREEN_OFF".equals(intent.getAction())){
				System.out.println("Start Activity");
				Intent intent1=new Intent(BaseActivity.this, GesturePassActivity.class);
				BaseActivity.this.startActivity(intent1);
			}
		}

	}
}