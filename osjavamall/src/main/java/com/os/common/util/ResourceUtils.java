package com.os.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 文件资源操作工具类
 * 
 * 
 */
public class ResourceUtils {
	private static final Logger LOGGER = Logger.getLogger(ResourceUtils.class);
	private static final String URL_DECODE_CHARSET = "UTF-8";
	private static final String CLASSPATH_PREFIX = ClassUtils.CLASSPATH_PREFIX;

	/**
	 * 从给定路径读取Properties文件
	 * 
	 * @param resourceLocation
	 * @return
	 */
	public static Properties getProperties(String resourceLocation) {
		Properties props = new Properties();

		InputStream is = getResourceAsStream(resourceLocation);

		try {
			if (is == null) {
				LOGGER.info("无法读取文件[" + resourceLocation + "]");
			} else {
				props.load(is);
			}
		} catch (IOException e) {
			LOGGER.info("无法读取文件[" + resourceLocation + "]");
		} finally {
			IOUtils.closeQuietly(is);
		}

		return props;
	}

	/**
	 * 从给定路径读取文件
	 * 
	 * @param resourceLocation
	 * @return
	 */
	public static File getFileResouce(String resourceLocation) {
		File file = null;
		if (isResouceInClasspath(resourceLocation)) {
			resourceLocation = getLocationFromClasspath(resourceLocation);
			URL url = ClassLoaderUtils.getClassLoader().getResource(
					resourceLocation);
			if (url != null) {
				String filePath = decodeUrl(url.getFile());
				file = new File(filePath);
			}

		} else {
			file = new File(resourceLocation);
		}

		if (file == null || !file.exists() || file.isDirectory()) {
			LOGGER.info("无法读取文件[" + resourceLocation + "]");
		}

		return file;
	}

	/**
	 * 从给定URL路径读取文件
	 * 
	 * @param resourceLocation
	 * @return
	 */
	public static URL getResourceURL(String resourceLocation) {
		if (isResouceInClasspath(resourceLocation)) {
			resourceLocation = getLocationFromClasspath(resourceLocation);
			URL url = ClassLoaderUtils.getClassLoader().getResource(
					resourceLocation);
			if (url != null) {
				return url;
			}

		} else {
			File file = new File(resourceLocation);
			try {
				return file.toURI().toURL();
			} catch (MalformedURLException e) {
				LOGGER.info("无法读取URL资源[" + resourceLocation + "]");
			}
		}

		LOGGER.info("无法读取文件[" + resourceLocation + "]");
		return null;
	}

	/**
	 * 从给定路径获取文件输入流
	 * 
	 * @param resourceLocation
	 * @return
	 */
	public static InputStream getResourceAsStream(String resourceLocation) {
		InputStream is = null;
		if (isResouceInClasspath(resourceLocation)) {
			resourceLocation = getLocationFromClasspath(resourceLocation);
			is = ClassLoaderUtils.getClassLoader().getResourceAsStream(
					resourceLocation);
		} else {
			File file = new File(resourceLocation);
			try {
				is = FileUtils.openInputStream(file);
			} catch (IOException e) {
				LOGGER.info("无法打开文件[" + resourceLocation + "]的输入流", e);
			}
		}

		return is;
	}

	private static String decodeUrl(String url) {
		if (url == null) {
			return null;
		}

		String result = url;

		try {
			result = new String(URLCodec.decodeUrl(url.getBytes()),
					URL_DECODE_CHARSET);
		} catch (UnsupportedEncodingException e) {
			LOGGER.info("Cannot decode url :" + url, e);
		} catch (DecoderException e) {
			LOGGER.info("Cannot decode url :" + url, e);
		}

		return result;
	}

	private static String getLocationFromClasspath(String resourceLocation) {
		String location = StringUtils.replaceOnce(resourceLocation,
				CLASSPATH_PREFIX, "");

		if (location != null && !location.startsWith("/")) {
			location = "/" + location;
		}

		return location;
	}

	private static boolean isResouceInClasspath(String resourceLocation) {
		if (StringUtils.isNotBlank(resourceLocation)) {
			if (StringUtils.startsWithIgnoreCase(resourceLocation,
					CLASSPATH_PREFIX)) {
				return true;
			}
		}

		return false;
	}
}