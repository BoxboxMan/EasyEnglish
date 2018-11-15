package org.jxnu.service.impl;

import java.util.List;

import org.jxnu.controller.Exception.CustomException;
import org.jxnu.mapper.SysCustomPermissionMapper;
import org.jxnu.mapper.SysUserCustomMapper;
import org.jxnu.mapper.SysUserMapper;
import org.jxnu.po.ActiveUser;
import org.jxnu.po.SysPermission;
import org.jxnu.po.SysUser;
import org.jxnu.po.SysUserExample;
import org.jxnu.service.LoginService;
import org.jxnu.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class LoginServiceImpl implements LoginService{

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysUserCustomMapper sysUserCustomMapper;
	
	@Autowired
	private SysCustomPermissionMapper sysCustomPermissionMapper;
	
	@Override
	public ActiveUser login(String usercode, String password) throws Exception {
		SysUser sysUser = findSysUser(usercode);
		if(sysUser==null){
			throw new CustomException("用户名不存在");
		}
		String password_db = sysUser.getPassword();
		String password_page = new MD5().getMD5ofStr(password);
		if(!password_db.equalsIgnoreCase(password_page)){
			throw new CustomException("用户名或密码错误");
		}
		//查找用户对应的菜单
		List<SysPermission> menus = sysUserCustomMapper.findMenuList(sysUser.getId());
		//查找用户队友的权限列表
		List<SysPermission> permissions = sysUserCustomMapper.findSysPermissionList(sysUser.getId());
		//对ActiveUser对象填充数据，准备放入session
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(sysUser.getId());
		activeUser.setUsercode(sysUser.getUsercode());
		activeUser.setUsername(sysUser.getUsername());
		activeUser.setMenus(menus);
		activeUser.setPermissions(permissions);
		return activeUser;
	}

	@Override
	public SysUser findSysUser(String usercode) throws Exception {
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andUsercodeEqualTo(usercode);
		List<SysUser> list = sysUserMapper.selectByExample(sysUserExample);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SysPermission> findSysPermission(String userid) {
		return sysCustomPermissionMapper.findSysPermission(userid);
	}

	@Override
	public List<SysPermission> findUserMenuList(String userid) {
		return sysCustomPermissionMapper.findUserMenuList(userid);
	}

}
