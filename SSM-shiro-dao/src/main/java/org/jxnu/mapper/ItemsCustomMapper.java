package org.jxnu.mapper;

import java.util.List;

import org.jxnu.po.ItemsCustom;
import org.jxnu.po.ItemsQueryVo;



public interface ItemsCustomMapper {

	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo);
	
}
