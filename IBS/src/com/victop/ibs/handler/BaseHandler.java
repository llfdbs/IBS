package com.victop.ibs.handler;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.victop.android.session.ReturnDataEnum;

public class BaseHandler extends Handler {
	private String TAG;
	private Context mContext;
	Handler handler;

	public BaseHandler(Context context, Handler handler) {
		this.mContext = context;
		this.handler = handler;
		TAG = mContext.getPackageName();
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		ReturnDataEnum type = ReturnDataEnum.getReturnType(msg.what);
		Message message = new Message();
		switch (type) {
		case GET_DATA_FAIL:
			Log.e(TAG, "获取业务数据失败");
			message.what = 1;
			message.obj = null;
			handler.sendMessage(message);
			break;
		case GET_DATA_SUCCESS:
			message.what = 0;
			message.obj = msg.obj;
			handler.sendMessage(message);
			break;
		case SAVE_DATA_FAIL:   //Map<String, List> dataMap;
			message.what = 3;
			message.obj = "保存业务数据失败！";
			handler.sendMessage(message);
			Log.e(TAG, "保存业务数据失败");
			break;
		case SAVE_DATA_SUCCESS:
			message.what = 1;
			message.obj = "保存业务数据成功！";
			handler.sendMessage(message);
			Log.d(TAG, "保存业务数据成功！");
			break;
		case SESSION_INVALID:
			message.what = 2;
			message.obj = "session失效！";
			handler.sendMessage(message);
			Log.e(TAG, "session失效");
			break;
		default:
			break;
		}

	}
}