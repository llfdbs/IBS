package com.victop.ibs.db;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("DatabaseUpgrate")
public class DatabaseUpgrate
{
  @XStreamAsAttribute
  @XStreamAlias("OldVersion")
  private int          mOldVersion;

  @XStreamAsAttribute
  @XStreamAlias("NewVersion")
  private int          mNewVersion;

  @XStreamImplicit
  @XStreamAlias("Sql")
  private List<String> mSqlList;

  private DatabaseUpgrate()
  {

  }

  public int getOldVersion()
  {
    return mOldVersion;
  }

  public int getNewVersion()
  {
    return mNewVersion;
  }

  public List<String> getSqlList()
  {
    return mSqlList;
  }

}
