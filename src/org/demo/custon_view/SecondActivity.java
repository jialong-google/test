package org.demo.custon_view;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;  
import android.widget.Toast;

//这是经过验证后的第二个窗口
public class SecondActivity extends ListActivity{//BaseActivity {
    /** Called when the activity is first created. */
	public final static String XiuGai1="com.example.myfirstapp.XiuGai1";
	public final static String XiuGai2="com.example.myfirstapp.XiuGai2";
   // @Override
    //public void onCreate(Bundle savedInstanceState) {
//    	super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
       //	TextView tv=new TextView(this);
       //	tv.setText("Second");
       //	setContentView(tv);
       // 	setContentView(R.layout.);
   // }
	
	
	private static int num_of_item=0;
	public static SharedPreferences sp;
	public static SimpleAdapter mSchedule;
	public static ListView list;
	public ArrayList<HashMap<String,String>> mylist;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
	    sp = getSharedPreferences("SPnew", MODE_PRIVATE);
		//初始化两个读取字符串
        String name_id="none";
        String string_password="none";
        
		list=(ListView) getListView();
		
		mylist=new ArrayList<HashMap<String,String>>();
		
		mSchedule=new SimpleAdapter(this,mylist,R.layout.listitem,new String[]{"ItemTitle","ItemText"},new int[]{R.id.ItemTitle,R.id.ItemText});
		
		
		list.setAdapter(mSchedule);
		//显示过去存储过的信息
		num_of_item=sp.getInt("num", 0);
		
		for(int i=0;i<num_of_item;i++){
        	
        	name_id =sp.getString("usrname"+i, "none");
        	string_password=sp.getString("password"+i, "none");
        	System.out.println("usrname"+i+": "+name_id);
        	System.out.println("password"+i+": "+string_password);
        	if(name_id!="none" || string_password!="none"){
        		HashMap<String,String> map =new HashMap<String,String>();
    			map.put("ItemTitle",name_id);
    			map.put("ItemText",string_password);
    			mylist.add(map);
        	}
        }
		//点击listviewitem使之显示Toast窗口
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				setTitle("点击第"+arg2+"个项目");   
				//取出arg2项目的内容 并显示出来
				String getName=sp.getString("usrname"+arg2, "出错啦！");
				String getpassword=sp.getString("password"+arg2, "出错啦！");
				Toast toast = Toast.makeText(getApplicationContext(),"用户名:"+getName+"\n密码:"+getpassword, Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
			
		});
		//长按弹出
		list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.setHeaderTitle("长按菜单-ContextMenu");      
                menu.add(0, 0, 0, "修改");   
                menu.add(0, 1, 0, "删除");
			}
		});
		//若得到了intent使之添加信息
		Intent intent=getIntent();
		
		String id=intent.getStringExtra(AddIDandPassword.EXTRA_MESSAGE1);
		String password=intent.getStringExtra(AddIDandPassword.EXTRA_MESSAGE2);
		if(id!=null && password!=null){
			HashMap<String,String> map =new HashMap<String,String>();
			map.put("ItemTitle",id);
			map.put("ItemText",password);
			mylist.add(map);
		
		
		//获取SharedPreferences对象
//        ctx = SecondActivity.this;       
//        sp = ctx.getSharedPreferences("SPnew", SecondActivity.MODE_PRIVATE);
//		 sp = getSharedPreferences("SPnew", MODE_PRIVATE);
		//存入数据
        Editor editor = sp.edit();
        
        editor.putString("usrname"+num_of_item,id );
        editor.putString("password"+num_of_item, password);
        num_of_item++;
        editor.putInt("num", num_of_item);
        editor.commit();
        //信息的item个数加一
        Log.d("lijialong","num_of_item: "+num_of_item);
		}
        
		
		
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
    }
    //菜单
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
            	startActivity(new Intent(SecondActivity.this,AddIDandPassword.class));
                return true;
            case R.id.item2:
            	startActivity(new Intent(SecondActivity.this,GesturePassActivity.class));
                return true;
            default:
            	return false;
                //return super.onOptionsItemSelected(item);
        }
    }
    
    //长按菜单响应函数   
    @Override  
    public boolean onContextItemSelected(MenuItem item) {   
        setTitle("点击了长按菜单里面的第"+item.getItemId()+"个项目");  
        //0：修改 1：删除
        ContextMenuInfo info = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
        // 获取选中行位置
        int position = (int)contextMenuInfo.position;
        
        switch (item.getItemId()) {
		case 0:
			//弹出修改窗口
			String getName1;
			String getpassword1;
			//删除listview对应项
			Intent xiugai=new Intent(SecondActivity.this,AddIDandPassword.class);
	        xiugai.putExtra(XiuGai1, sp.getString("usrname"+(position), "chucuole"));
	        xiugai.putExtra(XiuGai2, sp.getString("password"+(position), "chucuole"));
			getName1 =sp.getString("usrname"+(position+1), "none");
        	getpassword1=sp.getString("password"+(position+1), "none");
        	Editor editor = sp.edit();
        	while(getName1!="none" || getpassword1!="none"){//覆盖前一项position
                editor.putString("usrname"+position,getName1 );
                editor.putString("password"+position, getpassword1);
                editor.commit();
    			position++;
				getName1 =sp.getString("usrname"+(position+1), "none");
        		getpassword1=sp.getString("password"+(position+1), "none");
        	}
			//删除最后一项
        	editor.putString("usrname"+position,"none" );
            editor.putString("password"+position, "none");
            num_of_item--;
            editor.putInt("num", num_of_item);
            editor.commit();
            
            startActivity(xiugai);
            //在intent中添加position对应信息，这样在更改的时候可以在原来基础上修改
			break;
		case 1:
			//删除操作
			//从删除位置开始遍历SharedPreference覆盖后刷新列表
			String getName;
			String getpassword;
			mylist.remove(position);//选择行的位置
			mSchedule.notifyDataSetChanged();
			list.invalidate();
			
			getName =sp.getString("usrname"+(position+1), "none");
        	getpassword=sp.getString("password"+(position+1), "none");
        	Editor editor1 = sp.edit();
        	while(getName!="none" || getpassword!="none"){//覆盖前一项position
        		
                editor1.putString("usrname"+position,getName );
                editor1.putString("password"+position, getpassword);
                editor1.commit();
    			position++;
				getName =sp.getString("usrname"+(position+1), "none");
        		getpassword=sp.getString("password"+(position+1), "none");
        	}
			//删除最后一项
        	editor1.putString("usrname"+position,"none" );
            editor1.putString("password"+position, "none");
            num_of_item--;
            editor1.putInt("num", num_of_item);
            editor1.commit();
			
			
			
			break;
		default:
			break;
		}
        return super.onContextItemSelected(item);   
    }
  

}



	      
