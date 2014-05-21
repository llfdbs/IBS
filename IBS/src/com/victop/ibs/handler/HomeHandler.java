package com.victop.ibs.handler;

import java.util.List;
import java.util.Map;
import com.victop.android.session.ReturnDataEnum;
import com.victop.ibs.bean.CheckedMaterailCountBean;
import com.victop.ibs.bean.GetTaskCountBean;
import com.victop.ibs.bean.MaterialCountBean;
import com.victop.ibs.bean.SendTaskCountBean;
import com.victop.ibs.bean.UnCheckedMaterialCountBean;
import com.victop.ibs.bean.UnfinishedMaterialCountBean;
import com.victop.ibs.bean.UserMessageBean;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



public class HomeHandler extends Handler{
	public static final String TAG = HomeHandler.class.getSimpleName(); 
	private Context mContext;
	public HomeHandler(Context context){
		this.mContext = mContext;
	}
	public void handleMessage(Message msg) {
		ReturnDataEnum type = ReturnDataEnum.getReturnType(msg.what);
		switch (type) {
		case GET_DATA_FAIL:
			Log.e(TAG, "获取业务数据失败");
			break;
		case GET_DATA_SUCCESS:		
			Log.d(TAG, "获取业务数据成功");
			System.out.println(msg.obj.toString()+"获取业务数据成功");
			Map<String,List> dataMap = (Map<String, List>) msg.obj;
			
			
			List<UserMessageBean> userMessage = dataMap.get("10");
			for(UserMessageBean ub : userMessage){
				System.out.println(ub.getHrname()+ub.getHeadimage()+"姓名===========");
				
			}
			
			List<MaterialCountBean> materialCount = dataMap.get("3");
			for(MaterialCountBean mb:materialCount){
				System.out.println(mb.getSummaterialid()+"素材个数=================");
			}
			
			List<UnfinishedMaterialCountBean> unfinishedMaterialCount = dataMap.get("13");
			for(UnfinishedMaterialCountBean ub :unfinishedMaterialCount){
				System.out.println(ub.getSummaterialid()+"未完成核素材个数");
			}
			
			List<CheckedMaterailCountBean> checkedMaterialCount = dataMap.get("11");
			for(CheckedMaterailCountBean cb:checkedMaterialCount){
				System.out.println(cb.getSummaterialid()+"已审核素材个数");
			}
			 
			List<UnCheckedMaterialCountBean> uncheckedMaterialCount = dataMap.get("12");
			for(UnCheckedMaterialCountBean ub:uncheckedMaterialCount){
				System.out.println(ub.getSummaterialid()+"未审核素材个数");
			}
			
			List<GetTaskCountBean> getTaskCount = dataMap.get("8");
			for(GetTaskCountBean gb:getTaskCount){ 
				Log.d(TAG,gb.toString());
				System.out.println(gb.getSumtaskid()+"接受的任务个数================");
			}
			
			
			List<SendTaskCountBean> sendTaskCount = dataMap.get("9");
			for(SendTaskCountBean sb:sendTaskCount){
				Log.d(TAG,sb.toString());
				System.out.println(sb.getSumtaskid()+"发布的任务个数================");
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
