package org.jxnu.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jxnu.po.ItemsCustom;
import org.jxnu.po.ItemsQueryVo;
import org.jxnu.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/items")
public class ItemsController{

	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping(value="/listItems")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response,ItemsQueryVo itemsQueryVo) throws Exception {
		ModelAndView modelAndView = new ModelAndView("items/items_list");
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		modelAndView.addObject("itemsList", itemsList);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/findItems",method={RequestMethod.POST,RequestMethod.GET})
	@RequiresPermissions("items:query")
	public ModelAndView findItems(@RequestParam(value="id")Integer id, ItemsCustom itemsCustom)throws Exception{
		ModelAndView modelAndView = new ModelAndView("items/items_edit");
		modelAndView.addObject("item", itemsService.findItemsById(id, itemsCustom));
		return modelAndView;
	}
	
	/**
	 * 根据商品id更新
	 * @param id 
	 * @param itemsCustom pojo类
	 * @param result 校验信息存储器
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateItems")
	public ModelAndView updateItems(@RequestParam(value="id",required=true)Integer id, ItemsCustom itemsCustom,MultipartFile picc )throws Exception{
		ModelAndView modelAndView = new ModelAndView("redirect:listItems.action");
		//获得上传文件的原名
		String fileName = picc.getOriginalFilename();
		if(picc!=null && fileName.length()>0){
			//获得后缀名
			String picSuf = fileName.substring(fileName.lastIndexOf('.'));
			//文件新的名字
			String fileNewName = UUID.randomUUID().toString()+picSuf;
			//创建文件
			File upload_pic = new File("E:\\study\\Oracle\\pictrue\\"+fileNewName);
			//写入文件中
			picc.transferTo(upload_pic);
			itemsCustom.setPic(fileNewName);
		}
		itemsService.updateItems(id, itemsCustom);
		return modelAndView;
	}
	
	@RequestMapping(value="/queryUpdate")
	public ModelAndView queryUpdate()throws Exception{
		ModelAndView modelAndView = new ModelAndView("items/items_queryUpdate");
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		modelAndView.addObject("itemsList", itemsList);
		return modelAndView;
	}
	
	@RequestMapping(value="/queryUpdateSubmit")
	public String queryUpdateSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
		//业务进行批量更新
				return "redirect:listItems.action";
	}
	
	@RequestMapping(value="/queryDelete")
	public String queryDelete(@RequestParam(value="items_id")Integer[] items_id) throws Exception{
		//业务执行批量删除
		return "redirect:listItems.action";
	}
	
	


}
