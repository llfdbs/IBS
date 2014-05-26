package com.victop.ibs.util;

public class CreateBeanFactory {

	private static Object obj;

	private static CreateBeanFactory factory;

	private CreateBeanFactory(Object obj_) {
		obj = obj_;
	};

	// 单例获取Bean对象生产工厂
	public static CreateBeanFactory getInstance(Object obj) {
		if (null == factory) {
			synchronized (CreateBeanFactory.class) {
				if (factory == null) {
					factory = new CreateBeanFactory(obj);
				}
			}
		} else {
			setObj(obj);
		}

		return factory;
	}

	public Object getObj() {
		return obj;
	}

	private static void setObj(Object obj_) {
		obj = obj_;
	}

	// bean对象生产线
	public Object create() {
		Object obj_ = null;
		try {
			obj_ = getObj().getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj_;
	}
}
