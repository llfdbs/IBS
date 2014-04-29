package com.victop.ibs.db.base;

/**
 * 数据库的接口
 * 
 * @author vv
 * 
 */
public interface SQliteBaseinter {
	/**
	 * 插入数据
	 * 
	 * @return
	 */
	public boolean insert();

	/**
	 * 更新数据
	 * 
	 * @return
	 */
	public boolean update();

	/**
	 * 删除数据
	 * 
	 * @return
	 */
	public boolean deleteModel();
}
