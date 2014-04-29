package com.victop.ibs.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.thoughtworks.xstream.XStream;
import com.victop.ibs.app.ibsApplication;

public class DatabaseConfig {
	private static Database mDatabase;

	static {
		Context _ContextApp = ibsApplication.getInstance();

		XStream _XStream = new XStream();
		_XStream.processAnnotations(Database.class);
		try {
			InputStream _InputStream = _ContextApp.getAssets().open(
					"database.xml");
			mDatabase = (Database) _XStream.fromXML(_InputStream);
			_InputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private DatabaseConfig() {
	}

	public static String getDatabaseName() {
		return mDatabase.getDatabaseName();
	}

	public static int getVersion() {
		return mDatabase.getVersion();
	}

	public static List<String> getCreatDatabaseSql() {
		return mDatabase.getCreatDatabaseSql();
	}

	public static List<String> getUpgrateDatabaseSql(int pOldVersion,
			int pNewVersion) {
		return mDatabase.getUpgrateDatabaseSql(pOldVersion, pNewVersion);
	}

	public static String getTableName(Class<?> pModelClass) {
		return mDatabase.getTableName(pModelClass.getName());
	}

	public static List<String> getAllColumnName(Class<?> pModelClass) {
		String _TableName = getTableName(pModelClass);
		return mDatabase.getAllColumnName(_TableName);
	}

	public static Map<String, String> getFieldColumnMap(Class<?> pModelClass) {
		String _TableName = getTableName(pModelClass);
		return mDatabase.getFieldColumnMap(_TableName);
	}

	public static Map<String, String> getColumnFieldMap(Class<?> pModelClass) {
		String _TableName = getTableName(pModelClass);
		return mDatabase.getColumnFieldMap(_TableName);
	}

}