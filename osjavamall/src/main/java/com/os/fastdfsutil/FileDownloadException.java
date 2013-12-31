package com.os.fastdfsutil;

public class FileDownloadException extends RuntimeException {
	private static final long serialVersionUID = 1796824303307189534L;

	public FileDownloadException() {
		super();
	}

	public FileDownloadException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileDownloadException(String message) {
		super(message);
	}

	public FileDownloadException(Throwable cause) {
		super(cause);
	}
}
