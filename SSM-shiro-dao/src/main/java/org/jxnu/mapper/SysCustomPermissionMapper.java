package org.jxnu.mapper;

import java.util.List;

import org.jxnu.po.SysPermission;

public interface SysCustomPermissionMapper {

	/**
	 * 根据用户id查询用户的权限列表
	 * @param userid	用户id
	 * @return	用户权限列表
	 */
	public List<SysPermission> findSysPermission(String userid);
	
	/**
	 * 根据用户id查询用户的菜单权限
	 * @param userid	用户id
	 * @return
	 */
	public List<SysPermission> findUserMenuList(String userid);
	
}
