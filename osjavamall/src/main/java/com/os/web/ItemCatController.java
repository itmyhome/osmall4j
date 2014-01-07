package com.os.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.os.common.util.DataGridModel;
import com.os.entity.itemcats.ItemCat;
import com.os.entity.itemcats.ItemNode;
import com.os.service.IItemCatService;

@Controller
@RequestMapping(value = "/itemCat")
public class ItemCatController {
	@Resource
	private IItemCatService iItemCatService;

	private Log log = LogFactory.getLog(ItemCatController.class);

	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request,
			HttpServletResponse response, ItemCat itemCat) {
		return "/itemCat/main";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		return "itemCat/itemCat";
	}

	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, ItemCat itemCat)
			throws Exception {
		return iItemCatService.getList(dgm, itemCat);
	}

	@RequestMapping(value = "/operateItemCat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addItemCat(ItemCat itemCat) throws Exception {
		Map<String, Object> returnInfo = new HashMap<String, Object>();
		try {
			if (itemCat.getCid() != null) {
				iItemCatService.update(itemCat);
				returnInfo.put("message", "修改成功");
			} else {
				iItemCatService.create(itemCat);
				returnInfo.put("message", "新增成功");
			}
			
		}catch (Exception e) {
			returnInfo.put("error", "操作失败"+e.getMessage());
		}

		return returnInfo;
	}
	@RequestMapping({"/getRootItem", "/getRootItem"})
	@ResponseBody
	public Object getRootItem(@RequestParam(value="id",required=false) String id){
//		System.out.println(id);
		HashMap<String,String> paramMap = new HashMap<String,String>();
		//初始化树
		if("".equals(id)||id==null){
			id = "0";
			paramMap.put("cid", id);
			ArrayList<ItemCat> rootItemCatList = iItemCatService.findItemCatByCondition(paramMap);
			paramMap.clear();
			paramMap.put("parentCid", id);
			//查询子节点数量
			ArrayList<ItemCat> childrenItemCatList = iItemCatService.findItemCatByCondition(paramMap);
			ArrayList<ItemNode> rootItemNode = new ArrayList<ItemNode>();
			ItemNode rootItem = new ItemNode();
			if(rootItemCatList!=null && rootItemCatList.size()>0){
				for(ItemCat itemCat : rootItemCatList){
					rootItem.setId(itemCat.getCid());
					rootItem.setText(itemCat.getName());
					rootItem.setState(childrenItemCatList.size()>0 ? "closed" : "open");
					rootItemNode.add(rootItem);
				}
			}
			return rootItemNode;
		}else{
			//请求下一级的节点
			paramMap.clear();
			paramMap.put("parentCid", id);
			//子节点
			ArrayList<ItemCat> childItemNodeList = iItemCatService.findItemCatByCondition(paramMap);
			
			//保存各个节点的集合 一个map对象就是一个节点 一个节点 保存了该节点的各个属性  
		    List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		    
		    for(ItemCat node : childItemNodeList){  
		    	HashMap<String,Object> item = new HashMap<String, Object>() ;
		    	item.put("id", node.getCid());
		    	item.put("text", node.getName());
		    	paramMap.clear();
				paramMap.put("parentCid", String.valueOf(node.getCid()));
				//子节点 若有子节点则收起 延迟加载
				ArrayList<ItemCat> hasChildItemNode = iItemCatService.findItemCatByCondition(paramMap);
		    	item.put("state", hasChildItemNode.size()>0?"closed":"open");
		    	Map<String,Object> attr = new HashMap<String,Object>() ;  
	            attr.put("parentId", id); 
	            item.put("attributes", attr);
		    	items.add(item);
		    }
		    return items;
		}
	}
	@RequestMapping("/deleteItemCat")
	@ResponseBody
	public Object deleteItemCat(@RequestParam(value="ids",required=false) String ids){
		Map<String, Object> returnInfo = new HashMap<String, Object>();
		if("".equals(ids)||ids==null){
			returnInfo.put("error", "您没有选择记录");
			return returnInfo;
		}
		String[] idArr = ids.split(",");
		//检查当前所选类目下是否有子类目 有则不允许删除
		StringBuilder canNotDeleteIds = new StringBuilder();
		try{
			for(String id:idArr){
				ItemCat itemCat = new ItemCat();
				itemCat.setCid(Integer.parseInt(id));
				HashMap<String,String> paramMap = new HashMap<String,String>();
				paramMap.put("parentCid", id);
				ArrayList<ItemCat> hasChildren = iItemCatService.findItemCatByCondition(paramMap);
				if(hasChildren!=null && hasChildren.size()>0 ){
					canNotDeleteIds.append(id);
					continue;
				}
				iItemCatService.delete(itemCat);
			}
			if(canNotDeleteIds.length()>0){
				returnInfo.put("fail", "当前选择的类别下有子类别 不允许删除");
				return returnInfo;
			}
			returnInfo.put("success", "删除成功");
		}catch (Exception e) {
			returnInfo.put("error", "删除失败"+e.getMessage());
		}
		return returnInfo;
	}
	
}
