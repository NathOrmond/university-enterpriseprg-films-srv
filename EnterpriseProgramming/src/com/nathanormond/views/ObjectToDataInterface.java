package com.nathanormond.views;

import java.util.List;

/**
 * 
 * @author Nathan Ormond
 *
 * @param <T> Data type which is being acted upon
 */
public interface ObjectToDataInterface<T> {

	public abstract String toXMLString(List<T> datas);
	public abstract String toXMLString(T datas);
	public abstract String toJSONString(List<T> datas);
	public abstract String toJSONString(T datas);
	public abstract String toCSVString(List<T> datas);
	public abstract String toCSVString(T datas);
	
}
