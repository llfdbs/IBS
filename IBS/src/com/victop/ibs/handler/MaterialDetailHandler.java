package com.victop.ibs.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.victop.android.session.ReturnDataEnum;
import com.victop.ibs.bean.MaterialDetailHistoryBean;
import com.victop.ibs.bean.MaterialDetailMessageBean;

/**
 * 素材详情handler
 * 
 * @author vv
 * 
 */
public class MaterialDetailHandler extends Handler {
	public static final String TAG = MaterialDetailHandler.class
			.getSimpleName();
	private Context mContext;
	Map<String, List> dataMap;
	Handler handler;

	public MaterialDetailHandler(Context context, Handler handler) {
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
//			List<MaterialDetailHistoryBean> mMaterialHistoryList = dataMap
//					.get("5");
//			List<MaterialDetailMessageBean> materialDetailMessageBeans = dataMap
//					.get("1");
//			System.out.println(materialDetailMessageBeans.size() + "....."
//					+ mMaterialHistoryList.size()
//					+ "--------------------------");
//			if (null == mMaterialHistoryList) {
//				mMaterialHistoryList = new ArrayList<MaterialDetailHistoryBean>();
//			}
//			for (MaterialDetailHistoryBean task : mMaterialHistoryList) {
//				System.out.println(task.getMaterialid() + task.getModifyman()
//						+ task.getModifydate() + "+++++++++++++++++++");
//			}
			Message message = new Message();
			message.what = 0;
			message.obj = dataMap;
			handler.sendMessage(message);
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
