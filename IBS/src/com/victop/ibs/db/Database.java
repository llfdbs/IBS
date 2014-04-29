package com.victop.ibs.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Database")
public class Database {
	
	@XStreamAsAttribute
	@XStreamAlias("DatabaseName")
	private String mDatabaseName;
	
	@XStreamAsAttribute
	@XStreamAlias("Version")
	private int mVersion;
	
	@XStreamImplicit
	private List<Table> mTableList;
	
	@XStreamImplicit
	private List<DatabaseUpgrate> mUpgrateSqlList;
	
	@XStreamImplicit
	@XStreamAlias("InitSql")
	private List<String> mInitSqlList;
	
	private Database() {
		
	}
	
	public String getDatabaseName() {
		return mDatabaseName;
	}

	public int getVersion() {
		return mVersion;
	}
	
	public String getTableName(String pModelClassName) {
		return getModelTableByModelClassName(pModelClassName).getTableName();
	}
	
	public String getModelClassName(String pTableName) {
		return getModelTableByTableName(pTableName).getModelClassName();
	}
	
	public List<String> getAllTableName() {
		List<String> _AllTableNames = new ArrayList<String>();
		
		for (Table _ModelTable : mTableList) {
			_AllTableNames.add(_ModelTable.getTableName());
		}
		
		return _AllTableNames;
	}
	
	public List<String> getAllColumnName(String pTableName) {
		return getModelTableByTableName(pTableName).getAllColumnName();
	}
	
	public Map<String, String> getAllColumnExtra(String pTableName) {
		return getModelTableByTableName(pTableName).getAllColumnExtra();
	}

	
	public Map<String, String> getFieldColumnMap(String pTableName) {
		return getModelTableByTableName(pTableName).getFieldColumnMap();
	}
	
	public Map<String, String> getColumnFieldMap(String pTableName) {
		return getModelTableByTableName(pTableName).getColumnFieldMap();
	}
	
	public List<String> getCreatDatabaseSql()
	{
		List<String> _SqlList = new ArrayList<String>();
		
		for (Table _Table : mTableList)
		{
			_SqlList.add(_Table.getCreatTableSql());
		}
		
		if (mInitSqlList!=null && mInitSqlList.size()>0)
		{
			for (String _String : mInitSqlList)
			{
				_SqlList.add(_String);
			}
		}
		
		return _SqlList;
	}
	
	public List<String> getUpgrateDatabaseSql(int pOldVersion, int pNewVersion)
  {
	  List<String> _UpgrateSqlList = new ArrayList<String>();
	  
	  while (pOldVersion != pNewVersion)
    {
	    for (DatabaseUpgrate _DatabaseUpgrate : mUpgrateSqlList)
	    {
	      if (pOldVersion == _DatabaseUpgrate.getOldVersion())
	      {
	        _UpgrateSqlList.addAll(_DatabaseUpgrate.getSqlList());
	        pOldVersion = _DatabaseUpgrate.getNewVersion();
	        break;
	      }
	    }
      
    }
	  
	  return _UpgrateSqlList;
  }
	
	private Table getModelTableByTableName(String pTableName) {
		Table _ModelTable = null;
		
		for (Table _tmpModelTable : mTableList) {
			if (pTableName.equals(_tmpModelTable.getTableName())) {
				_ModelTable = _tmpModelTable;
				break;
			} 
		}
		
		return _ModelTable;
	}
	
	private Table getModelTableByModelClassName(String pModelClassName) {
		Table _ModelTable = null;
		
		for (Table _tmpModelTable : mTableList) {
			if (pModelClassName.equals(_tmpModelTable.getModelClassName())) {
				_ModelTable = _tmpModelTable;
				break;
			} 
		}
		
		return _ModelTable;
	}

	
}
