package com.os.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.os.common.SecDaoSupport;
import com.os.common.util.DataGridModel;
import com.os.dao.IItemCatDao;
import com.os.entity.itemcats.ItemCat;
import com.os.pageutil.PageInfoGrid;
@Component
@SuppressWarnings("unchecked")
public class ItemCatDaoImpl extends SecDaoSupport implements IItemCatDao {

	public void insert(ItemCat bean) {
		ibatisPersistence.update("ItemCat.addItemCat", bean);
	}

	public void update(ItemCat bean) {
		ibatisPersistence.update("ItemCat.updateItemCat", bean);
	}

	public void delete(ItemCat bean) {
		ibatisPersistence.update("ItemCat.deleteItemCat", bean);
	}

	public Integer getCount(PageInfoGrid grid) {
		Map<String, Object> paramMap = grid.getSearch();
		Integer count = (Integer)ibatisPersistence.findObject("ItemCat.getCountItemCat",paramMap);
		return count;
	}

	public ArrayList<ItemCat> getPageList(PageInfoGrid grid) {
		Map<String, Object> paramMap = grid.getSearch();
		 
		ArrayList<ItemCat> list=( ArrayList<ItemCat>) ibatisPersistence.findList(
		 "ItemCat.getPageListItemCat", paramMap,  grid.getPage().getStartRowNum(), grid.getPage().getPageSize());
		return list;
	}

	public ItemCat getByCondition(String condition) {
		return null;
	}

	public Integer getItemCatId() {
		return (Integer) ibatisPersistence.findObject("ItemCat.getItemCatId", "");
	}

	public Map<String, Object> getPageList(DataGridModel dgm, ItemCat itemCat) {
		Map<String, Object> result = new HashMap<String, Object>(); 
		Integer count = (Integer)ibatisPersistence.findObject("ItemCat.getCountItemCat",itemCat);
		ArrayList<ItemCat> list=( ArrayList<ItemCat>) ibatisPersistence.findList("ItemCat.getPageListItemCat",itemCat,(dgm.getPage()-1)*dgm.getRows(),dgm.getRows());
		result.put("total", count);
		result.put("rows", list);
		return result;
	}

	@Override
	public ArrayList<ItemCat> getItemByConditon(HashMap<String, String> paramMap) {
		ArrayList<ItemCat> list=( ArrayList<ItemCat>) ibatisPersistence.findList("ItemCat.findItemCatByCondition", paramMap);
		return list;
	}

}
