package core;

public enum ColorEnum {
	BLACK("Black"), WHITE("White");
	
	private String name;
	
	ColorEnum(String color) {
		this.name = color;
	}
	
	public String toString() {
		return name;
	}

}
