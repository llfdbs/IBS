package com.victop.ibs.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.victop.android.session.ReturnDataEnum;
import com.victop.ibs.bean.GetTaskBean;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 获取接受的任务列表handler
 * 
 * @author vv
 * 
 */
public class GetTaskHandler extends Handler {
	public static final String TAG = GetTaskHandler.class.getSimpleName();
	private Context mContext;
	Map<String, List> dataMap;
	Handler handler;

	public GetTaskHandler(Context context, Handler handler) {
		this.mContext = mContext;
		this.handler = handler;
	}

	public void handleMessage(Message msg) {
		ReturnDataEnum type = ReturnDataEnum.getReturnType(msg.what);
		switch (type) {
		case GET_DATA_FAIL:
			Log.e(TAG, "获取业务数据失败");
			Message message_error = new Message();
			message_error.what = 1;
			handler.sendMessage(message_error);
			break;
		case GET_DATA_SUCCESS:

			dataMap = extracted(msg);
			List<GetTaskBean> task = dataMap.get("1");

			if(null==task){
				task= new ArrayList<GetTaskBean>();
			}
			Message message = new Message();
			message.what = 0;
			message.obj = task;
			handler.sendMessage(message);
//			System.out.println(task.size() + "任务个数为：");
//			for (GetTaskBean gb : task) {
//				System.out.println(gb.getTaskname() + gb.getTaskstatus()
//						+ "任务名称加任务状态");
//			}

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
