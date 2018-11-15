package org.jxnu.service;

import java.util.List;

import org.jxnu.po.ItemsCustom;
import org.jxnu.po.ItemsQueryVo;



public interface ItemsService {

	/**
	 * 根据商品信息查询商品
	 * @param itemsQueryVo
	 * @return
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo);
	
	/**
	 * 根据商品id查询商品
	 * @param id	商品的id
	 * @param itemsCustom	
	 * @return
	 */
	public ItemsCustom findItemsById(Integer id,ItemsCustom itemsCustom) throws Exception;
	
	/**
	 * 根据商品id更新商品
	 * @param id 商品id
	 * @param itemsCustom
	 * @return
	 */
	public int updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;
	
}
