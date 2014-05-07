package com.victop.ibs.db;

import java.util.List;

import com.victop.ibs.app.IBSApplication;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteOpenAssistant extends SQLiteOpenHelper
{
	private SQLiteOpenAssistant()
	{
		super(IBSApplication.getInstance(), DatabaseConfig.getDatabaseName(), null, DatabaseConfig.getVersion());
	}
	
    private static class SQLiteOpenAssistantHolder {  
    	private static SQLiteOpenAssistant INSTANCE = new SQLiteOpenAssistant();  
    }  
  
    public static SQLiteOpenAssistant getInstance()
    {
        return SQLiteOpenAssistantHolder.INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase pSqLiteDatabase)
    {
        List<String> _SqlList = DatabaseConfig.getCreatDatabaseSql();

        for (String _Sql : _SqlList)
        {
            pSqLiteDatabase.execSQL(_Sql);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase pSqLiteDatabase, int pOldVersion, int pNewVersion)
    {
        List<String> _SqlList = DatabaseConfig.getUpgrateDatabaseSql(pOldVersion, pNewVersion);

        for (String _Sql : _SqlList)
        {
            pSqLiteDatabase.execSQL(_Sql);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase pSqLiteDatabase)
    {
        if (!pSqLiteDatabase.isReadOnly()) {
            pSqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

}
