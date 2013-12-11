package org.demo.custon_view;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GesturePassActivity extends Activity {
	/** Called when the activity is first created. */
	GridView gridView;
	ImageAdapter adapter;
	ArrayList<Integer> items;
	NinePointLineView nplView;
	TextView tvMsg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//不允许旋转屏幕
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// View v = new NinePointView(MyCustomViewActivity.this);
		// View v = new NinePointLineView(MyCustomViewActivity.this);
		// setContentView(v);
		setContentView(R.layout.gesture_set_layout);
		gridView = (GridView) findViewById(R.id.gridView);
		nplView = (NinePointLineView) findViewById(R.id.nplView);
		tvMsg = (TextView) findViewById(R.id.tvMsg);
		items = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			items.add(0);
		}
		adapter = new ImageAdapter(items, this);
		gridView.setAdapter(adapter);
		nplView.setAdapter(adapter);
	}

	/*
	 * @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
	 * 
	 * if (keyCode == KeyEvent.KEYCODE_BACK) { //showDialog(this); return true;
	 * }
	 * 
	 * return true; }
	 */

	public void showDialog(final Context context) {
		if (context == null) {
			return;
		}
		AlertDialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		dialog = builder.setMessage("你确定要退出吗?").setTitle("提醒")
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
				}).show();
	}

	class ImageAdapter extends BaseAdapter {
		private ArrayList<Integer> list;
		private LayoutInflater inflater;
		private Context context;

		public ImageAdapter(ArrayList<Integer> list, Context context) {
			this.context = context;
			inflater = LayoutInflater.from(context);
			setList(list);
		}

		public void setList(ArrayList<Integer> list) {
			if (list != null) {
				this.list = list;
			} else {
				this.list = new ArrayList<Integer>();
			}
		}

		public ArrayList<Integer> getList() {
			return list;
		}

		StringBuffer beforString = new StringBuffer();

		public int change(StringBuffer sb) {
			 int result=0;
			if(beforString.length()==0&&sb.length()>=4)
				resetList(sb);
			if (sb.length() >= 4) {
				if (beforString.length() == 0) {
					beforString.append(sb.toString());
					tvMsg.setText("确认您的手势密码");
				} else {
					if (beforString.toString().equals(sb.toString())) {
						SharedPreferences preferences = GesturePassActivity.this.getSharedPreferences(
								"angellpay", GesturePassActivity.this.MODE_WORLD_READABLE);
						SharedPreferences.Editor editor = preferences.edit();
						editor.putString("gesturePass", beforString.toString());
						editor.commit();
						Intent intent=new Intent(GesturePassActivity.this, LockActivity.class);
						GesturePassActivity.this.startActivity(intent);
						finish();
					}else{
//						Toast.makeText(context, "手势密码错误,请再次输入!", 1).show();
						return -1;
					}
				}
			}
			return result;
		}

		private void resetList(StringBuffer sb) {
			ArrayList<Integer> result = new ArrayList<Integer>();
			for (int i = 0; i < 9; i++) {
				boolean isSelected = false;
				for (int j = 0; j < sb.length(); j++) {
					int k = Integer.parseInt(sb.charAt(j) + "");
					if (i == k) {
						isSelected = true;
						break;
					}

				}
				if (isSelected)
					result.add(1);
				else
					result.add(0);
			}
			setList(result);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayout layout = (LinearLayout) inflater.inflate(
					R.layout.gridview_item, null);
			ImageView imageView = (ImageView) layout
					.findViewById(R.id.imageView);
			if (list.get(position) == 0) {
				imageView
						.setBackgroundResource(R.drawable.patternindicator_grid_normal);

			} else {
				imageView
						.setBackgroundResource(R.drawable.patternindicator_grid_focused);

			}
			return layout;
		}

	}
}