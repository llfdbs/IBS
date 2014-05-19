package com.victop.ibs.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.victop.android.session.Constant;
import com.victop.ibs.activity.LoginActivity;
import com.victop.ibs.activity.MainActivity;
import com.victop.platform.common.json.JsonHelper;
import com.victop.platform.core.bean.ReplyMessage;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class LoginHandler extends Handler {

	public static final String TAG = "LoginHandler";

	private Context mContext;
	public LoginHandler(Context context) {
		this.mContext = context;
	}

	@Override
	public void handleMessage(Message msg) {
		LoginActivity loginActivity = (LoginActivity) mContext;
		switch (msg.what) {
		case Constant.HANDLER_CODE_1:
			loginActivity.loginProgressDialog.dismiss();
			ReplyMessage replyMessage = (ReplyMessage) msg.obj;
			if (replyMessage == null) {
				Toast.makeText(mContext, "取数不成功", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "取数不成功");
			} else {
				
				
				String tag = replyMessage.getReplyMode().name() + " "
						+ replyMessage.getReplyControl() + " "
						+ replyMessage.getReplyContent();
				Log.d(TAG, tag);

				String saas = JsonHelper.readeJsonString(
						replyMessage.getReplyContent(), "saas");
				Map saasMap = JsonHelper.readeJsonObject(saas, Map.class);
				if (null!=saasMap&&null!=saasMap.get("mobileUrl")&&null != saasMap.get(saasMap.get("mobileUrl"))) {
					String mobileUrl = saasMap.get("mobileUrl").toString();
					// Container.getInstance().getMobileUrls().add(mobileUrl);
					Log.d(TAG, "服务器插件： " + mobileUrl);
				} else {
					Log.d(TAG, "服务器插件 为空 ");
				}

				Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT)
						.show();

				// FIXME 厦门项目POS
				showCustomerPos();

				// FIXME 演示专用
//				 showCustomer();

				// FIXME 初始化门户插件的app信息
				 //initApp();
				

			}
			break;
		case Constant.HANDLER_CODE_2:
			loginActivity.loginProgressDialog.dismiss();
			Toast.makeText(mContext, "取数不成功", Toast.LENGTH_SHORT).show();
			Log.d(TAG, msg.obj.toString());
		default:
			break;
		}
	}

	private void showCustomerPos() {
		
		Intent intent = new Intent(mContext, MainActivity.class);
		mContext.startActivity(intent);
		((Activity) mContext).finish();
	}

//	private void showCustomer() {
//		User user = Container.getInstance().getUser();
//		// String url =
//		// "http://122.227.218.50:9898/read/phone/login.action?spaceId="
//		String url = "http://110.86.1.90:8084/web-toread/phone/login.action?spaceId="
//				+ user.getClientId()
//				+ "&userCode="
//				+ user.getUserCode()
//				+ "&password=" + user.getUserPwd();
//		WebUtil.openWebUrl(mContext, url);
//	}

//	private void initApp() {
//		// TODO 从服务器获得插件信息，需改写服务端
//		Container.getInstance().getMobileUrls()
//				.add("http://110.86.1.90:8084/web-toread");
//		Container.getInstance().getMobileUrls()
//				.add("http://192.168.25.144:8087/jjl");
//
//		// FIXME 暂且读取已安装的app，待服务端完成后，从服务器上下载插件信息
//		List<AppInfo> pluginApps = PackageUtil.getInstalledApp(mContext
//				.getApplicationContext());
//		//给全局容器添加当前所有插件的基本信息
//		Container.getInstance().setAppPlugins(pluginApps);
//	}
}
