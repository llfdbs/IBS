package com.victop.ibs.handler;

import java.util.List;
import java.util.Map;
import com.victop.android.session.ReturnDataEnum;
import com.victop.ibs.bean.SendTaskBean;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 获取发布的任务列表handler
 * 
 * @author vv
 * 
 */
public class SendTaskHandler extends Handler {
	public static final String TAG = SendTaskHandler.class.getSimpleName();
	private Context mContext;
	Map<String, List> dataMap;
	Handler handler;

	public SendTaskHandler(Context context, Handler handler) {
		this.mContext = context;
		this.handler = handler;
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		ReturnDataEnum type = ReturnDataEnum.getReturnType(msg.what);
		switch (type) {
		case GET_DATA_FAIL:
			Log.e(TAG, "获取业务数据失败");
			break;
		case GET_DATA_SUCCESS:

			dataMap = extracted(msg);
			List<SendTaskBean> task = dataMap.get("1");

			Message message = new Message();
			message.what = 0;
			message.obj = task;
			handler.sendMessage(message);
			System.out.println(task.size() + "任务个数为：");
			for (SendTaskBean sb : task) {
				System.out.println(sb.getTaskname() + sb.getTaskstatus()
						+ "任务名称加任务状态");
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
