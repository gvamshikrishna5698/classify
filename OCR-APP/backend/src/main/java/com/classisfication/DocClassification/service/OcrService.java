package com.classisfication.DocClassification.service;

import java.io.File;

public interface OcrService {
	public String getTextFromFile(File file);
	public String readTextFile(File file);
}
