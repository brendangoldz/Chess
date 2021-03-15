package com.chess.core;


public enum ColorEnum {
	BLACK("Black"), WHITE("White");
	
	private String name;
	
	ColorEnum(String color) {
		this.name = color;
	}
	@Override
	public String toString() {
		return name;
	}

}
