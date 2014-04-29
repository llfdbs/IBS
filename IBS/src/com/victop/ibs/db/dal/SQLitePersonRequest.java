package com.victop.ibs.db.dal;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.victop.ibs.db.base.SQLiteDALBase;
import com.victop.ibs.db.model.Person;

public class SQLitePersonRequest extends SQLiteDALBase<Person> {

	public SQLitePersonRequest(Class<Person> pModelClass) {
		super(pModelClass);
		// TODO Auto-generated constructor stub
	}

	// 查询对象
	public Person select(String pUrlHashCode, String pUrlParamHashCode,
			String pBodyParamHashCode) {
		// String _UrlIdColumnName = getFieldColumnMap().get("mID");
		String _UrlHashCodeColumnName = getFieldColumnMap()
				.get("mNameHashCode");
		String _UrlParamHashCodeColumnName = getFieldColumnMap().get(
				"mAgeHashCode");
		String _BodyParamHashCodeColumnName = getFieldColumnMap().get(
				"mHeightHashCode");
		List<Person> _ModelList = select(_UrlHashCodeColumnName + "="
				+ pUrlHashCode + " AND " + _UrlParamHashCodeColumnName + "="
				+ pUrlParamHashCode + " AND " + _BodyParamHashCodeColumnName
				+ "=" + pBodyParamHashCode, null, null, null, null);

		if (_ModelList.size() <= 0) {
			return null;
		} else {
			return _ModelList.get(0);
		}
	}

	// 查询对象
	public Person selectId(String id) {
		String _UrlHashCodeColumnName = getFieldColumnMap()
				.get("mNameHashCode");

		List<Person> _ModelList = select(_UrlHashCodeColumnName + "=" + id,
				null, null, null, null);

		if (_ModelList.size() <= 0) {
			return null;
		} else {
			return _ModelList.get(0);
		}
	}

	// 更新对象
	@Override
	public boolean update(Person pModelObject) {
		String _UrlHashCodeColumnName = getFieldColumnMap()
				.get("mNameHashCode");
		String _UrlParamHashCodeColumnName = getFieldColumnMap().get(
				"mAgeHashCode");
		String _BodyParamHashCodeColumnName = getFieldColumnMap().get(
				"mHeightHashCode");

		boolean _Result = false;

		beginTransaction();
		try {
			int _UpdateRowNumbers = update(
					pModelObject,
					_UrlHashCodeColumnName + "="
							+ pModelObject.getmNameHashCode() + " AND "
							+ _UrlParamHashCodeColumnName + "="
							+ pModelObject.getAgeHashCode() + " AND "
							+ _BodyParamHashCodeColumnName + "="
							+ pModelObject.getHeightHashCode(), null);
			if (_UpdateRowNumbers == 1) {
				setTransactionSuccessful();
				_Result = true;
			}
		} finally {
			endTransaction();
		}

		return _Result;
	}

	// 更新对象

	public boolean updateID(String id, Person pModelObject) {
		String _UrlHashCodeColumnName = getFieldColumnMap()
				.get("mNameHashCode");
		String _UrlParamHashCodeColumnName = getFieldColumnMap().get(
				"mAgeHashCode");
		String _BodyParamHashCodeColumnName = getFieldColumnMap().get(
				"mHeightHashCode");

		boolean _Result = false;

		beginTransaction();
		try {
			int _UpdateRowNumbers = update(pModelObject, _UrlHashCodeColumnName
					+ "=" + id, null);
			if (_UpdateRowNumbers == 1) {
				setTransactionSuccessful();
				_Result = true;
			}
		} finally {
			endTransaction();
		}

		return _Result;
	}

	// 添加对象或者更新对象
	@Override
	public boolean insertOrUpdateModel(Person pModelObject) {
		Log.i("bai", "insertOrUpdateModel ：" + pModelObject.toString());
		Person _ModelWebServiceGet = select(pModelObject.getmNameHashCode(),
				pModelObject.getAgeHashCode(), pModelObject.getHeightHashCode());

		if (_ModelWebServiceGet == null) {
			return insert(pModelObject);
		} else {
			return update(pModelObject);
		}
	}

	// 定义字段类型
	@Override
	protected ContentValues toContentValues(Person pModelObject) {
		Map<String, String> _FieldColumnMap = getFieldColumnMap();
		ContentValues _ContentValues = new ContentValues();

		for (String _FieldName : _FieldColumnMap.keySet()) {
			if ("mNameHashCode".equals(_FieldName)) {
				_ContentValues.put(_FieldColumnMap.get(_FieldName),
						pModelObject.getNameHashCode());
			} else if ("mAgeHashCode".equals(_FieldName)) {
				_ContentValues.put(_FieldColumnMap.get(_FieldName),
						pModelObject.getAgeHashCode());
			} else if ("mHeightHashCode".equals(_FieldName)) {
				_ContentValues.put(_FieldColumnMap.get(_FieldName),
						pModelObject.getHeightHashCode());
			}
		}

		return _ContentValues;
	}

	// 根据游标获取对象
	@Override
	protected Person toModel(Cursor pCursor) {
		Person _ModelObject = new Person();
		_ModelObject.setID(pCursor.getLong(pCursor
				.getColumnIndexOrThrow(getFieldColumnMap().get("mID"))));
		_ModelObject
				.setNameHashCode(pCursor.getString(pCursor
						.getColumnIndexOrThrow(getFieldColumnMap().get(
								"mNameHashCode"))));
		_ModelObject
				.setAgeHashCode(pCursor.getString(pCursor
						.getColumnIndexOrThrow(getFieldColumnMap().get(
								"mAgeHashCode"))));
		_ModelObject.setHeightHashCode(pCursor.getString(pCursor
				.getColumnIndexOrThrow(getFieldColumnMap().get(
						"mHeightHashCode"))));

		return _ModelObject;
	}

	@Override
	public boolean insert() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteModel() {
		// TODO Auto-generated method stub
		return false;
	}

}
