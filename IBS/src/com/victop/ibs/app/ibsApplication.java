package com.victop.ibs.app;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * app首先启动类，本类包含全局的一些变量，基础方法
 * 
 * @author vv
 * 
 */
public class IBSApplication extends Application {
	private List<WeakReference<Activity>> mActivityList = new ArrayList<WeakReference<Activity>>();
	private static IBSApplication mSingleton;
	
	 

	@Override
	public void onCreate() {
		super.onCreate();
		mSingleton = this;
		initImageLoader(getApplicationContext());
	}

	public static IBSApplication getInstance() {
		return mSingleton;
	}

	/**
	 * 图片加载设置属性
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	 

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		WeakReference<Activity> _WeakReference = new WeakReference<Activity>(
				activity);
		mActivityList.add(_WeakReference);
	}

	// 遍历所有的Activty并退出程序
	public void exit() {
		for (int i = mActivityList.size() - 1; i >= 0; i--) {
			WeakReference<Activity> _Tem = mActivityList.get(i);
			if (_Tem != null) {
				Activity _Activity = _Tem.get();
				if (_Activity != null) {
					_Activity.finish();
				}
			}
		}
		// System.exit(0);
	}
}
