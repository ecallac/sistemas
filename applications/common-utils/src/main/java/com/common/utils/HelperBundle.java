package com.common.utils;

import java.util.ResourceBundle;

public final class HelperBundle {
	
	private static final String BUNDLE_PATH_CONFIG = "com/common/resource/security-property";
	
	private HelperBundle() {
	 }

	
	public static String getProperty(final String key, final String bundle) {
		final ResourceBundle resource = ResourceBundle.getBundle(bundle);
		return (String) resource.getObject(key);
	}
	
	public static String getServicesConfig(String key) {
		return getProperty(key, BUNDLE_PATH_CONFIG);
	}
}
