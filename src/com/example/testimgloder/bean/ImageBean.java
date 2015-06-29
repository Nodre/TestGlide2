package com.example.testimgloder.bean;

import java.io.Serializable;

public class ImageBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ImageBean(String src, String size){
		this.imageSrc = src;
		this.imageSize = size;
	}
	
	public String imageSrc;
	public String imageSize;
}
