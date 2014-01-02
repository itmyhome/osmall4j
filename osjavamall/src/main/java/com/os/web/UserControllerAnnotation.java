package com.os.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.os.entity.User;
import com.os.fastdfsutil.FastDFSFileOperator;
import com.os.fastdfsutil.StorageUnreachableException;
import com.os.pageutil.PageInfo;
import com.os.pageutil.PageInfoGrid;
import com.os.service.UserService;
@Controller
@RequestMapping(value = "/user3")
public class UserControllerAnnotation{
	@Resource
	private UserService userService;
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private FastDFSFileOperator fastDFSFileOperator;
	
	private Log log = LogFactory.getLog(UserControllerAnnotation.class);
	
//	private UserModelValidator validator= new UserModelValidator();
	@RequestMapping(value = "/create")
	public String create(HttpServletRequest request,
			HttpServletResponse response, User user, Errors errors) {
		log.info(request.getMethod());
		if ("GET".equals(request.getMethod())) {
			// 如果是get请求 我们转向 新增页面
			return "user3/create";
		}
		//validator.validate(user, errors);
		//if(errors.hasErrors()) { //2如果有错误再回到表单示页面
			//return "user3/create";
		//} 
		userService.create(user);
		// 直接重定向到列表页面
		return "redirect:list";
	}
	@RequestMapping(value = "/list") 
	public String list(HttpServletRequest request,
			HttpServletResponse response) {
		int pageNo = 1,pageSize = 5;
		if(!"".equals(request.getParameter("pageNo"))&&request.getParameter("pageNo")!=null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if(!"".equals(request.getParameter("pageSize"))&&request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		return "redirect:list/"+pageNo+"/"+pageSize;
	}
	@RequestMapping(value = "/list/{pageNo}/{pageSize}") 
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int pageNo,@PathVariable int pageSize, User user) {
		PageInfoGrid pageInfoGrid = new PageInfoGrid();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNum(pageNo);
		pageInfo.setPageSize(pageSize);
		pageInfoGrid.setPage(pageInfo);
		ModelAndView mv = new ModelAndView();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if(!"".equals(user.getUserName())&&user.getUserName()!=null){
			hashMap.put("userName", user.getUserName());
			pageInfoGrid.setSearch(hashMap);
		}
		long exeuteBefore = System.currentTimeMillis();
		log.info(System.currentTimeMillis());
		mv.addObject("userList", userService.getList(pageInfoGrid, hashMap));
		long exeuteAfter = System.currentTimeMillis();
		log.info("[executeTime]"+ (exeuteAfter - exeuteBefore));
		mv.addObject("pageInfo", pageInfoGrid);
		mv.setViewName("user3/list");
		return mv;
	}
	@RequestMapping(value = "/update") 
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, User user) {
		if ("GET".equals(request.getMethod())) {
			// 如果是get请求 我们转向更新页面
			ModelAndView mv = new ModelAndView();
			// 查询要更新的数据
			mv.addObject("user",userService.findUserByCondition(user));
			mv.setViewName("user3/update");
			return mv;
		}
		userService.update(user);
		// 直接重定向到列表页面
		return new ModelAndView("redirect:list");
	}
	@RequestMapping(value = "/delete") 
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, User user) {
		if ("GET".equals(request.getMethod())) {
			// 如果是get请求 我们转向删除页面
			ModelAndView mv = new ModelAndView();
			// 查询要删除的数据
			mv.addObject("user", userService.findUserByCondition(user));
			mv.setViewName("user3/delete");
			return mv;
		}
		userService.delete(user);
		// 直接重定向到列表页面
		return new ModelAndView("redirect:list");
	}
	
	@RequestMapping(value = "/test") 
	public void test() throws Exception{
//		Connection conn = jdbcTemplate.getDataSource().getConnection();
//	    String sql = "insert into t_user (user_id,user_name,credits,password) values(?,?,?,?)";
//	    log.info("begin test----------------");
//	    long startTime = System.currentTimeMillis();
//		PreparedStatement ps = conn.prepareStatement(sql);
//		for (int i = 0; i < 200000; i++) {
//			ps.setString(1, UUID.randomUUID().toString());
//			ps.setString(2, "linjinfeng"+i);
//			ps.setInt(3, i);
//			ps.setString(4, UUID.randomUUID().toString().substring(0, 6));
//			ps.addBatch();
//			if(i%20000==0){
//				ps.executeBatch();
//				
//			}
//		}
//		long endTime = System.currentTimeMillis();
//		log.info(endTime-startTime);
//		
//		ps.close();
//		conn.close();
//		
		
//		String[] returnInfo = fastDFSFileOperator.uploadFile(new File("D:\\gina1987\\1.jpg"));
//		for(String result:returnInfo){
//			System.out.println(result);
//		}
	System.out.println("111");
	}
	
	@RequestMapping(value = "/upload")
	public @ResponseBody String upload(HttpServletRequest request) throws StorageUnreachableException, IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
        // 获取前台传值  
        String[] userNames = multipartRequest.getParameterValues("userName");  
        String[] contents = multipartRequest.getParameterValues("content");  
        String userName = "";  
        String content = "";  
        if (userNames != null) {  
            userName = userNames[0];  
            System.out.println("userName:" + userName);  
        }  
        if (contents != null) {  
            content = contents[0];  
            System.out.println("content:" + content);  
        }  
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String originalFileName = null; 
        String[] resultInfo = {};
        StringBuffer resultJson = new StringBuffer();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {  
        	MultipartFile mf = entity.getValue();  
            originalFileName = mf.getOriginalFilename(); 
            String fileExt = originalFileName.substring(  
                    originalFileName.lastIndexOf(".") + 1).toLowerCase(); 
            resultInfo = fastDFSFileOperator.uploadFile(mf.getBytes(), fileExt);
            for(String result:resultInfo){
            	log.info(result);
            	resultJson.append(result).append("/");
            }
        }
        log.info(resultJson);
        resultJson = resultJson.delete(resultJson.length()-1,resultJson.length());
        log.info(JSON.toJSONString(resultJson));
        //fastDFSFileOperator.uploadFile(new File(""));
		return resultJson.toString();
	}
}
