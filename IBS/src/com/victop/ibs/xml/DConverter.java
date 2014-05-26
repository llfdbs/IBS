package com.victop.ibs.xml;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.victop.ibs.util.CreateBeanFactory;

public class DConverter implements Converter {
	Class cla;
	CreateBeanFactory bean;

	public void setBean(CreateBeanFactory bean) {
		this.bean = bean;
	}

	public DConverter(Class mClass) {
		this.cla = mClass;
	}

	@Override
	public boolean canConvert(Class arg0) {
		// TODO Auto-generated method stub
		if (null != cla) {
			return arg0.equals(cla);
		}
		return false;
	}

	@Override
	public void marshal(Object javaBean, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		// TODO Auto-generated method stub
		Iterator<Entry<String, String>> interator = bean2interator(javaBean);
		while (interator.hasNext()) {
			Entry<String, String> entry = interator.next();
			writer.addAttribute(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		// TODO Auto-generated method stub
		// M m = bean.create();
		Object obj =null;
		if(null!=bean){
			 obj =bean.create();
		}else {
			return false;
		}
		
		Iterator<Entry<String, String>> interator = null;
		HashMap<String, String> data = new HashMap<String, String>();
		if (null != obj) {

			interator = bean2interator(obj);
			while (interator.hasNext()) {
				Entry<String, String> entry = interator.next();
				String value = reader.getAttribute(entry.getKey());
				data.put(entry.getKey(), value);
			}
		} else {
			return  bean.create();

		}

		Method[] methods = obj.getClass().getDeclaredMethods();
		for (Method method : methods) {
			try {

				if (method.getName().startsWith("set")) {
					String field = method.getName();
					field = field.substring(field.indexOf("set") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					method.invoke(obj, new Object[] { data.get(field) });

				}
			} catch (Exception e) {
			}
		}
		return obj;

	}

	public Iterator<Entry<String, String>> bean2interator(Object javaBean) {
		Map<String, String> result = new HashMap<String, String>();
		Method[] methods = javaBean.getClass().getDeclaredMethods();

		for (Method method : methods) {
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);

					Object value = method.invoke(javaBean, (Object[]) null);
					result.put(field, null == value ? "" : value.toString());
				}
			} catch (Exception e) {

			}
		}
		Set<Map.Entry<String, String>> set = result.entrySet();
		Iterator<Entry<String, String>> interator = set.iterator();
		return interator;
	}
}