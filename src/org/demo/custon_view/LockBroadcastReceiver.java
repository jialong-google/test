package org.demo.custon_view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LockBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction()=="android.intent.action.SCREEN_OFF"){
			Intent intent1=new Intent(context, BaseActivity.class);
			context.startActivity(intent1);
		}
	}

}
