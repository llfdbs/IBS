package com.victop.ibs.handler;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.victop.android.session.ReturnDataEnum;
import com.victop.ibs.bean.UnCheckedMaterialBean;

public class MaterialunCheckHandler extends Handler {
	public static final String TAG = MaterialunCheckHandler.class
			.getSimpleName();
	private Context mContext;
	Map<String, List> dataMap;
	Handler handler;
	String state;

	public MaterialunCheckHandler(Context context, Handler handler, String state) {
		this.mContext = context;
		this.handler = handler;
		this.state = state;
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

			dataMap = (Map<String, List>) msg.obj;
			List<UnCheckedMaterialBean> mUnCheckedMaterialBean = dataMap
					.get(state+"");

			message.what = 0;
			message.obj = mUnCheckedMaterialBean;
			handler.sendMessage(message);
			if (null != mUnCheckedMaterialBean) {
				System.out.println(mUnCheckedMaterialBean.size() + "任务个数为：");
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
}
