package foodbook.android.model.enumerations;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RestaurantType {

	INDIAN(0), 
	CHINESE(1), 
	ITALIAN(2), 
	SERBIAN(3), 
	FRANCHE(4);
	
	private int value;
	
	private RestaurantType(int value) {
		this.value = value;
	}
	
	 @JsonValue
	 public int getValue() {
	        return value;
	 }
	 
	 public void setValue(int value) {
		 this.value = value;
	 }
	 
	 public static RestaurantType toEnum(String s) {
		 switch(s) {
		 case "INDIAN":
			 return RestaurantType.INDIAN;
		 case "ITALIAN":
			 return RestaurantType.ITALIAN; 
		 case "SERBIAN":
			 return RestaurantType.SERBIAN; 
		 case "FRANCHE":
			 return RestaurantType.FRANCHE; 
		 case "CHINESE":
		 	 return RestaurantType.CHINESE; 
		 default:
			 return null; 
		 }
	 }
}
