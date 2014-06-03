package com.victop.ibs.handler;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.victop.android.session.ReturnDataEnum;



public class HomeHandler extends Handler{
	public static final String TAG = HomeHandler.class.getSimpleName(); 
	private Context mContext;
	private Handler handler;
	Map<String, List> dataMap;
	public HomeHandler(Context context,Handler handler){
		this.mContext = mContext;
		this.handler = handler;
	}
	public void handleMessage(Message msg) {
		ReturnDataEnum type = ReturnDataEnum.getReturnType(msg.what);
		switch (type) {
		case GET_DATA_FAIL:
			Log.e(TAG, "获取业务数据失败");
			break;
		case GET_DATA_SUCCESS:		
			Log.d(TAG, "获取业务数据成功");
			 dataMap = extracted(msg);
			 if(null!=dataMap){
			 Message message = new Message();
			 message.what = 0;
			 message.obj = dataMap;
			 handler.sendMessage(message);
			 }
			break;
		case SAVE_DATA_FAIL:
			Log.e(TAG, "保存业务数据失败");
			break;
		case SAVE_DATA_SUCCESS:
			Log.d(TAG, "保存业务数据成功！");
			break;
		case SESSION_INVALID:
			Log.e(TAG, "session失效");
			break;
		default:
			break;
		}
	}
	private Map<String, List> extracted(Message msg) {
		return (Map<String, List>) msg.obj;
	}
}
