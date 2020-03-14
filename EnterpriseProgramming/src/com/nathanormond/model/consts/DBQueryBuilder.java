package com.nathanormond.model.consts;

/**
 * 
 * @author Nathan Ormond
 *
 */
public class DBQueryBuilder {
	
	// **ALWAYS LEAVE SPACE AT END OF STATEMENT SO MULTIPLE STRINGS CAN BE CONCATENATED

	public static final String SELECT_ALL(String table) {
		return String.format("SELECT * FROM %s ", table);
	}

	public static final String WHERE_REGEX(String columnName, String value) {
		return String.format("WHERE %s LIKE %%s% ", columnName, value);
	}

	public static final String WHERE_EQUALS(String columnName, String value) {
		return String.format("WHERE %s=\"%s\" ", columnName, value);
	}

	public static final String AND(String condition) {
		return String.format("AND %s ", condition);
	}
	
	public static final String OR(String condition) {
		return String.format("OR %s ", condition);
	}

	public static final String MAX(String tableName, String columnName) { 
		return String.format("SELECT MAX( %s ) FROM %s ", columnName, tableName);
	}
	
	public static final String GET_COUNT(String tableName, String columnName, String value) { 
		return String.format("SELECT count(*) AS count FROM %s %s", tableName, DBQueryBuilder.WHERE_EQUALS(columnName, value));
	}
	
	public static final String PUT_VALUE_IN_QUOTES(String value) { 
		return String.format("\"%s\"", value);
	}
	
	public static String DELETE(String tableName, String columnName, String value) { 
		return String.format("DELETE FROM %s %s", tableName, DBQueryBuilder.WHERE_EQUALS(columnName, value));
	}
	
	/**
	 * 
	 * Wraps values for insert statement into 
	 * syntactically correct and safe string
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @return insert string
	 */
	public static final String INSERT(String tableName, String[] columns, String[] values) { 
		String columnsString = " (";
		String valuesString = " (";
		
		String appendValue = "";
		
		for(int index = 0; index < columns.length; index++) {
			
			if(isInteger(values[index], 10)) { 
				appendValue = values[index];
			} else { 
				appendValue = PUT_VALUE_IN_QUOTES(values[index]);
			}
			
			if(index != (columns.length -1)) { 
				columnsString += " " + columns[index] + ",";
				valuesString += " " + appendValue + ",";	
			} else { 
				columnsString += " " + columns[index] + ") ";
				valuesString += " " + appendValue + ") ";
			}
		}
		return String.format("INSERT INTO %s %s VALUES %s ", tableName, columnsString, valuesString);
	}
	
	
	/**
	 * 
	 * credit to Stack Overflow : https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
	 * 
	 * @param string
	 * @param radix
	 * @return TRUE if string is an integer
	 */
	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}

}
