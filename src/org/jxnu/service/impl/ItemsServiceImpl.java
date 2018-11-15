package org.jxnu.service.impl;

import java.util.List;

import org.jxnu.controller.Exception.CustomException;
import org.jxnu.mapper.ItemsCustomMapper;
import org.jxnu.mapper.ItemsMapper;
import org.jxnu.po.Items;
import org.jxnu.po.ItemsCustom;
import org.jxnu.po.ItemsQueryVo;
import org.jxnu.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsCustomMapper itemsCustomMapper;
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) {
		return itemsCustomMapper.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(Integer id, ItemsCustom itemsCustom) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		if(items==null){
			throw new CustomException("商品错误");
		}
		ItemsCustom itemsCustom1 = new ItemsCustom();
		BeanUtils.copyProperties(items, itemsCustom1);
		return itemsCustom1;
	}

	@Override
	public int updateItems(Integer id, ItemsCustom itemsCustom) throws Exception{
		if(id==null){
			throw new Exception();
		}
		//必须再次设置id，确保id一致
		itemsCustom.setId(id);
		//updateByPrimaryKeyWithBLOBs若字段中携带大文本类型(text)也能更新成功
		return itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

}
