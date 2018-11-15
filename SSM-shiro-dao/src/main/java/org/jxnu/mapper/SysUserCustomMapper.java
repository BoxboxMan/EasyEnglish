package org.jxnu.mapper;

import java.util.List;

import org.jxnu.po.SysPermission;

public interface SysUserCustomMapper {

	/**
	 * 根据用户id查询用户的权限
	 * @param userid 用户id
	 * @return 
	 */
	public List<SysPermission> findSysPermissionList(String userid);
	
	/**
	 * 根据用户id查找用户对应的菜单
	 * @param userid
	 * @return
	 */
	public List<SysPermission> findMenuList(String userid);
	
}
