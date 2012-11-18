package com.royts.storage;

public class postNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public postNotFoundException(Throwable ex	) {
		super(ex);
	}

	public postNotFoundException(String message, Throwable e) {
		super(message, e);
	}
}
