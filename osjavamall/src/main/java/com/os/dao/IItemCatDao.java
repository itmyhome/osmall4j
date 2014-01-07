package com.os.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.os.common.util.DataGridModel;
import com.os.entity.itemcats.ItemCat;

public interface IItemCatDao extends IBaseDao<ItemCat>{
	Integer getItemCatId();
	Map<String, Object> getPageList(DataGridModel dgm,ItemCat itemCat);
	ArrayList<ItemCat> getItemByConditon(HashMap<String, String> paramMap);
}
