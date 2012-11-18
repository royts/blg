package com.royts.storage;

public class StorageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StorageException(Throwable ex	) {
		super(ex);
	}

	public StorageException(String message, Throwable e) {
		super(message, e);
	}
	
}
