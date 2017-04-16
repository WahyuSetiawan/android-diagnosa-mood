package menu.pakar.entity;

public class Rule {
	String trueString;
	String falseString;
	String current;

	public String getTrueString() {
		return trueString;
	}

	public void setTrueString(String trueString) {
		if (trueString.equals("") || trueString.equals("null")){
			trueString = "NULL";
		}
		this.trueString = trueString;
	}

	public String getFalseString() {
		return falseString;
	}

	public void setFalseString(String falseString) {
		if (falseString.equals("") || falseString.equals("null")){
			falseString = "NULL";
		}
		this.falseString = falseString;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public Rule(String current, String trueString, String falseString) {
		super();
		this.trueString = trueString;
		this.falseString = falseString;
		this.current = current;
	}

	public Rule() {
		// TODO Auto-generated constructor stub
	}

}
