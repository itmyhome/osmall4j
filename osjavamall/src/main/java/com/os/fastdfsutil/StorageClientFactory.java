package com.os.fastdfsutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;

public class StorageClientFactory {
	private static final Logger logger = Logger.getLogger(StorageClientFactory.class);
	private static TrackerGroup trackerGroup;
	private static boolean inited = false;

	public static void init(File config) {
		try {
			if (inited) {
				return;
			}
			String file = config.getPath();
			logger.info(file);
			ClientGlobal.init(file);
			inited = true;
		} catch (FileNotFoundException e) {
			logger.error("无法读取FastDFS客户端配置文件", e);
		} catch (IOException e) {
			logger.error("无法读取FastDFS客户端配置文件", e);
		} catch (Exception e) {
			logger.error("无法读取FastDFS客户端配置文件", e);
		}
		trackerGroup = ClientGlobal.getG_tracker_group();
	}

	public static StorageClient getStorageClient(String groupName) {
		if (trackerGroup == null) {
			throw new StorageUnreachableException("获取FastDFS的StorageClient失败");
		}
		TrackerClient trackerClient = new TrackerClient(trackerGroup);

		TrackerServer trackerServer = null;
		try {
			trackerServer = trackerClient.getConnection();
		} catch (IOException e) {
			logger.error("无法连接到FastDFS的Tracker Server");
			throw new StorageUnreachableException("获取FastDFS的StorageClient失败");
		}

		StorageServer storageServer = null;
		try {
			if (StringUtils.isNotBlank(groupName)) {
				storageServer = trackerClient.getStoreStorage(trackerServer,
						groupName);
			} else {
				storageServer = trackerClient.getStoreStorage(trackerServer);
			}
		} catch (IOException e) {
			logger.error("无法连接到FastDFS的Storage Server");
			throw new StorageUnreachableException("获取FastDFS的StorageClient失败");
		}

		if (storageServer == null) {
			logger.error("无法连接到FastDFS的Storage Server");
			throw new StorageUnreachableException("获取FastDFS的StorageClient失败");
		}

		StorageClient storageClient = new StorageClient(trackerServer,
				storageServer);

		return storageClient;
	}
}
