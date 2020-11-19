package lexer;
public class Keyword {
	
	public String descript;
	public String key;

	
	public Keyword(String k) {
		key = k;
		setDescript(k);
	}
	
	public void setDescript(String k) {
		switch(k){
			case "is":
				descript= "";
				break;
			case "buncha":
				descript = "Array";
				break;
			case "as if":
				descript = "If";
				break;
			case "unless..":
				descript = "Else if";
				break;
			case "but whatever":
				descript = "Else statement";
				break;
			case "do i hafta":
				descript = "Do-while loop";
				break;
			case "yech, still":
				descript = "while loop";
				break;
			case "mumble":
				descript = "print statement";
				break;
			case "NO!":
				descript = "'not' operator (!)";
				break;
			case "anxious":
				descript = "Random number";
				break;
			case "my":
				descript = "'private'";
				break;
			case "anybodies":
				descript = "'public'";
				break;
			case "maybe":
				descript = "try";
				break;
			case "or not.":
				descript = "catch";
				break;
			case "throws shade":
				descript = "Throws exception";
				break;
			case "string":
				descript = "String";
				break;
			case "number":
				descript = "pos/neg integer";
				break;
			case "should be":
				descript = "assignment (=)";
				break;
			case "jack all":
				descript = "NULL or 'nothing'";
				break;
			case "bool":
				descript = "boolean";
				break;
			case "thats deep.":
				descript = "end function";
				break;
			case "hear me out..":
				descript = "start function";
			default:
					break;
		}
		
	}
	
	public String getDescript(){
		return descript;
	}

	public String getKey() {
		return key;
	}

}
