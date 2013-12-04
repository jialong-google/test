package org.demo.custon_view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class LockActivity extends Activity {
	/** Called when the activity is first created. */
	NinePointLineView nplView;
	TextView tvMsg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.lock_layout);
		initView();
		initListener();
	}


	private void initListener() {
		// TODO Auto-generated method stub
		tvMsg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(LockActivity.this, "提醒", "忘记密码要重新登录,你确定要重新登录吗?")
						.setPositiveButton("重新登录"	, new AlertDialog.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						}).show();
				
			}
		});
	}


	private void initView() {
		nplView = (NinePointLineView) findViewById(R.id.nplView);
		nplView.setActivity(this);
		tvMsg = (TextView) findViewById(R.id.tvMsg);
	}

	
	  @Override 
	  public boolean onKeyDown(int keyCode, KeyEvent event) {
	  
	 if (keyCode == KeyEvent.KEYCODE_BACK) {
		 showDialog(this,"提醒","你确定要退出吗?").show(); 
		 return true;
	 }
	
	 return true; 
	 }
	 

	public AlertDialog.Builder showDialog(final Context context,String title,String message) {
		if (context == null) {
			return null;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		 builder.setMessage(message).setTitle(title)
				.setPositiveButton("确定", new AlertDialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						((Activity) context).finish();
						android.os.Process.killProcess(android.os.Process
								.myPid());
					}
				}).setNegativeButton("取消", new AlertDialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		return builder;
	}

}