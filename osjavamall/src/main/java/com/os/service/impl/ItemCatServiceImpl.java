package com.os.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.os.common.util.DataGridModel;
import com.os.dao.IItemCatDao;
import com.os.entity.itemcats.ItemCat;
import com.os.service.IItemCatService;
@Service
public class ItemCatServiceImpl implements IItemCatService {
	@Resource
	private IItemCatDao iItemCatDao;

	public void create(ItemCat itemCat) {
		Integer itemCatId = iItemCatDao.getItemCatId();
		itemCat.setCid(itemCatId);
		iItemCatDao.insert(itemCat);
	}

	public void update(ItemCat itemCat) {
		iItemCatDao.update(itemCat);
	}

	public void delete(ItemCat itemCat) {
		iItemCatDao.delete(itemCat);
	}

	

	public ArrayList<ItemCat> findItemCatByCondition(HashMap<String,String> paramMap) {
		return iItemCatDao.getItemByConditon(paramMap);
	}

	public Map<String, Object> getList(DataGridModel dgm, ItemCat itemCat) {
		return iItemCatDao.getPageList(dgm,itemCat);
	}

}
