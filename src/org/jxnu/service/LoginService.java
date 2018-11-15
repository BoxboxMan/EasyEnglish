package org.jxnu.service;

import java.util.List;

import org.jxnu.po.ActiveUser;
import org.jxnu.po.SysPermission;
import org.jxnu.po.SysUser;
import org.springframework.stereotype.Service;

/**
 * 对用户的登陆登出做处理
 * @author MR.S
 *
 */

public interface LoginService {

	/**
	 * 用户登陆校验用户编号、密码
	 * @param usercode	用户编号
	 * @param password	用户密码
	 * @return
	 * @throws Exception	抛出异常
	 */
	public ActiveUser login(String usercode,String password)throws Exception;
	
	/**
	 * 根据用户编码查询用户信息，查询不到则返回null
	 */
	public SysUser findSysUser(String usercode) throws Exception;
	
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
