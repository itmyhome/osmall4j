package com.os.fastdfsutil;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;

import com.os.common.util.Assert;
import com.os.common.util.ResourceUtils;

/**
 * 文件上传下载操作类
 * 
 * @author gina
 * 
 */
public class FastDFSFileOperator {
	private static final String DEFUALT_CONFIG_PATH = "classpath:/myclient.conf";
	private static final String DEFUALT_IMAGE_GROUPS = "group1";

	private String[] imageGroupNames;
	private int groups = -1;
	private File configFile;
	
	public void setImageGroupNames(String imageGroupNames) {
		parseImageGroupNames(imageGroupNames);
	}
	
	public void setConfig(String config) {
		initStorageClientFactory(config);
	}

	private void getImageGroupNames() {
		if(imageGroupNames == null && groups == -1) {
			parseImageGroupNames(DEFUALT_IMAGE_GROUPS);
		}
	}
	
	private void parseImageGroupNames(String groupNames) {
		imageGroupNames = StringUtils.split(groupNames, ",");
		this.groups = imageGroupNames.length;
	}
	
	private void initStorageConfig() {
		if(configFile == null) {
			initStorageClientFactory(DEFUALT_CONFIG_PATH);
		}
	}
	
	private void initStorageClientFactory(String config) {
		configFile = ResourceUtils.getFileResouce(config);
		StorageClientFactory.init(configFile);
	}

	public String getRandomImageGroup() {
		getImageGroupNames();
		int num = (new Random()).nextInt(groups);
		return imageGroupNames[num];
	}

	public String getImageGroup(String remoteFilename) {
		String[] pair = parseFilename(remoteFilename);

		return pair[0];
	}
	
	public String[] parseFilename(String remoteFilename) {
		String[] pair = new String[2];
		
		if(remoteFilename.charAt(0) == '/') {
			remoteFilename = remoteFilename.substring(1);
		}
		
		int index = remoteFilename.indexOf("/");
		
		pair[0] = remoteFilename.substring(0, index);
		pair[1] = remoteFilename.substring(index + 1);
		
		return pair;
	}
	
	public String getRemouteFile(String remoteFilename) {
		String[] pair = parseFilename(remoteFilename);

		return pair[1];
	}

	/**
	 * 
	 * @param file
	 * @return results[0]: the group name to store the file<br/>
	 *         results[1]: the new created filename
	 * @throws Exception
	 */
	public String[] uploadFile(File file) throws StorageUnreachableException {
		String filePath = file.getPath();
		String ext = FilenameUtils.getExtension(filePath);

		return uploadFile(file, ext);
	}

	/**
	 * 
	 * @param file
	 * @param ext
	 * @return results[0]: the group name to store the file<br/>
	 *         results[1]: the new created filename
	 * @throws Exception
	 */
	public String[] uploadFile(File file, String ext) throws StorageUnreachableException {
		Assert.notNull(file, "file为null,无法上传");
		Assert.isTrue(file.isFile(), "file为目录，无法上传");

		initStorageConfig();
		StorageClient client = StorageClientFactory.getStorageClient(getRandomImageGroup());

		String filePath = file.getPath();

		try {
			return client.upload_file(filePath, ext, null);
		} catch (IOException e) {
			throw new FileStoreException(e);
		} catch (MyException e) {
			throw new FileStoreException(e);
		}
	}

	/**
	 * 
	 * @param file
	 * @param ext
	 * 
	 * @return results[0]: the group name to store the file<br/>
	 *         results[1]: the new created filename
	 * @throws Exception
	 */
	public String[] uploadFile(byte[] file, String ext) throws StorageUnreachableException {
		initStorageConfig();
		StorageClient client = StorageClientFactory.getStorageClient(getRandomImageGroup());

		try {
			return client.upload_file(file, ext, null);
		} catch (IOException e) {
			throw new FileStoreException(e);
		} catch (MyException e) {
			throw new FileStoreException(e);
		}

	}

	/**
	 * 
	 * @param remoteFile
	 * @param localFile
	 * @throws Exception
	 */
	public void downloadFile(String imageGroupName, String remoteFile, String localFile)
			throws StorageUnreachableException {
		Assert.notNull(imageGroupName, "imageGroupName参数不能为空");
		
		initStorageConfig();
		StorageClient client = StorageClientFactory.getStorageClient(imageGroupName);

		int result;

		try {
			result = client.download_file(imageGroupName, remoteFile, localFile);
		} catch (IOException e) {
			throw new FileDownloadException(e);
		} catch (MyException e) {
			throw new FileDownloadException(e);
		}

		if (result != 0) {
			throw new FileDownloadException("下载FastDFS下的文件[" + remoteFile + "]失败");
		}
	}

	/**
	 * 
	 * @param remoteFile
	 * @return fileData
	 * @throws Exception
	 */
	public byte[] downloadFile(String imageGroupName, String remoteFile) throws StorageUnreachableException {
		Assert.notNull(imageGroupName, "imageGroupName参数不能为空");
		
		initStorageConfig();
		StorageClient client = StorageClientFactory.getStorageClient(imageGroupName);

		byte[] result = null;
		try {
			result = client.download_file(imageGroupName, remoteFile);
		} catch (IOException e) {
			throw new FileDownloadException(e);
		} catch (MyException e) {
			throw new FileDownloadException(e);
		}

		if (result == null || result.length == 0) {
			throw new FileDownloadException("下载FastDFS下的文件[" + remoteFile + "]失败");
		}

		return result;
	}
}
