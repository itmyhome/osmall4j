//package com.os.util;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.FileUpload;
//import org.apache.commons.fileupload.FileUploadBase;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.springframework.web.multipart.MaxUploadSizeExceededException;
//import org.springframework.web.multipart.MultipartException;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//
//public class CommonsMultipartResolverExt extends CommonsMultipartResolver {
//	private HttpServletRequest request;
//
//	@Override
//	protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
//		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
//		upload.setSizeMax(-1);
//		System.out.println("1111111111111111"+request);
//		if (request != null) {
//			HttpSession session = request.getSession();
//			FileUploadListener uploadProgressListener = new FileUploadListener(session);
//			upload.setProgressListener(uploadProgressListener);
//		}
//		return upload;
//	}
//
//	@Override
//	public MultipartHttpServletRequest resolveMultipart(
//			HttpServletRequest request) throws MultipartException {
//		this.request = request;// 获取到request,要用到session
//		System.out.println(request);
//		return super.resolveMultipart(request);
//	}
//	@Override
//	public MultipartParsingResult parseRequest(HttpServletRequest request)
//			throws MultipartException {
//
//		HttpSession session = request.getSession();
//
//		String encoding = "utf-8";
//		FileUpload fileUpload = prepareFileUpload(encoding);
//
//		FileUploadListener uploadProgressListener = new FileUploadListener(
//				session);
//		fileUpload.setProgressListener(uploadProgressListener);
//		try {
//			List<FileItem> fileItems = ((ServletFileUpload) fileUpload)
//					.parseRequest(request);
//			return parseFileItems(fileItems, encoding);
//		} catch (FileUploadBase.SizeLimitExceededException ex) {
//			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(),
//					ex);
//		} catch (FileUploadException ex) {
//			throw new MultipartException(
//					"Could not parse multipart servlet request", ex);
//		}
//	}
//	
//	private String excludeUrls;     
//    private String[] excludeUrlArray;  
//      
//    public String getExcludeUrls() {  
//        return excludeUrls;  
//    }  
//  
//  
//    public void setExcludeUrls(String excludeUrls) {  
//        this.excludeUrls = excludeUrls;  
//        this.excludeUrlArray = excludeUrls.split(",");  
//    }  
//  
//  
//    /** 
//     * 这里是处理Multipart http的方法。如果这个返回值为true,那么Multipart http body就会MyMultipartResolver 消耗掉.如果这里返回false 
//     * 那么就会交给后面的自己写的处理函数处理例如刚才ServletFileUpload 所在的函数 
//     * @see org.springframework.web.multipart.commons.CommonsMultipartResolver#isMultipart(javax.servlet.http.HttpServletRequest) 
//     */  
//    @Override  
//    public boolean isMultipart(HttpServletRequest request) {  
//        for (String url: excludeUrlArray) {  
//        // 这里可以自己换判断  
//        if (request.getRequestURI().contains(url)) {  
//            return false;  
//        }  
//    }  
//      
//        return super.isMultipart(request);  
//    }
//}
