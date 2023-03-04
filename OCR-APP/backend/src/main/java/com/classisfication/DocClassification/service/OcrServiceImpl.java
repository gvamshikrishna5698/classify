package com.classisfication.DocClassification.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class OcrServiceImpl implements OcrService {

	@Override
	public String getTextFromFile(File file) {
		
		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("C:\\Projects\\ocr_classify\\wp\\");
		String fullText;
		try {
			fullText = tesseract.doOCR(file);
			fullText = fullText.replaceAll("[^\\x20-\\x7e]", "");
			fullText = fullText.replaceAll("[^\\u0000-\\uFFFF]", "");
			return fullText.toUpperCase();
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		return "hello";
	}
	
	
	public String readTextFile(File file) {
		
		StringBuffer sb = new StringBuffer();
		BufferedReader readFile;
		
		try {
			readFile = new BufferedReader(new FileReader(file));
			String readFilerow;
			while ((readFilerow = readFile.readLine()) != null) 
				sb.append(readFilerow);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
    	return sb.toString().toUpperCase();
	}

}
