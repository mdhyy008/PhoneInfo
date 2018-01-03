package com.dabai.AndroidVer;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.telephony.*;
import android.content.*;
import android.view.*;

public class MainActivity extends Activity 
{

	String shar=null;
	private ListView mainListView1;
    @Override

	protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		final String[] data={
			"设备品牌：" + Build.BRAND,
			"MODEL：" + Build.MODEL,
			"设备型号：" + Build.DEVICE,
			"IMEI：" + getIMEI(getApplicationContext()),
			"Android版本：" + Build.VERSION.RELEASE,
			"Android API级别：" + Build.VERSION.SDK,
			"Emoji示例：😃🤒👽👾💏😈😺🐹🐱🐷🐶🐔🙈🌚🌝💖💘",
			"设备制造商：" + Build.MANUFACTURER,
			"产品型号：" + Build.PRODUCT,
			"主板型号：" + Build.BOARD,
			"Bootloader版本：" + Build.BOOTLOADER,
		    "CPU ABI：" + Build.CPU_ABI,
			"CPU ABI2：" + Build.CPU_ABI2,
			"设备显示屏信息：" + Build.DISPLAY,
			"设备唯一编号：" + Build.FINGERPRINT,
			"HOST值：" + Build.HOST,
			"ID：" + Build.ID,
			"设备序列号：" + Build.SERIAL,
			"Build TAGS：" + Build.TAGS,
			"系统构建时间：" + Build.TIME,
			"系统构建类型：" + Build.TYPE,
			"系统构建名称：" + Build.USER
			
		};



		ArrayAdapter<String> adr = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);	
		mainListView1 = (ListView)findViewById(R.id.mainListView1);
		mainListView1.setAdapter(adr);


		mainListView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){ 
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{

					ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					cm.setText(data[position]);
					Toast.makeText(getApplicationContext(), data[position] + "\n已复制", Toast.LENGTH_LONG).show();


				} }); 


		for (int i=1;i < data.length;i++)
		{

			shar = shar + data[i] + "\n";

		};}


	public static String getIMEI(Context ctx)
	{  
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);  
        if (tm != null)
		{  
            return tm.getDeviceId();  
        }  
        return null;  
    }  



	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.share:


				Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, shar);
                startActivity(Intent.createChooser(textIntent, "分享设备信息"));


				break;

			case R.id.copyall:


				ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				cm.setText(shar);
				Toast.makeText(this, "全部信息已复制", Toast.LENGTH_LONG).show();

				break;

		}
		return false;
	}






}
