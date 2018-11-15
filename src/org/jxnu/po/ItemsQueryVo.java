package org.jxnu.po;

import java.util.List;

public class ItemsQueryVo {

	private Items items;
	
	private ItemsCustom itemsCustom;
	
	private List<ItemsCustom> listItemsCustom;
	
	
	public List<ItemsCustom> getListItemsCustom() {
		return listItemsCustom;
	}

	public void setListItemsCustom(List<ItemsCustom> listItemsCustom) {
		this.listItemsCustom = listItemsCustom;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	
	
	
}
