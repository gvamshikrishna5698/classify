package com.classisfication.DocClassification.constants;

import java.util.ArrayList;

public class RequestConstants {

	public static final String REQUEST_OPEN = "CREATED";
	
	public static final String QUICK_RESPONSE = "QUICK_RESPONSE";
	
	public static final String ROBOT_NAME = "ROBOT";
	
	public static final ArrayList<String> OCR_SUPPORTED_FORMATS = new ArrayList<String>() {/**
		 * 
		 */
		private static final long serialVersionUID = 7912424983113941617L;
	{
		add("TIFF");
		add("JPEG");
		add("GIFF");
		add("PNG");
		add("BMP");
		add("png");
		add("jpeg");
		add("png");
		add("pdf");
		add("jpg");
	}};
}
