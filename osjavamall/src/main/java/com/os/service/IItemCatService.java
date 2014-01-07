package com.os.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.os.common.util.DataGridModel;
import com.os.entity.itemcats.ItemCat;

public interface IItemCatService {
	
	void create(ItemCat itemCat);
	void update(ItemCat itemCat);
	void delete(ItemCat itemCat);
	Map<String, Object> getList(DataGridModel dgm,ItemCat itemCat);
	ArrayList<ItemCat> findItemCatByCondition(HashMap<String,String> paramMap);

}
