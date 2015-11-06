package com.liferay.blade.java.api;

import java.util.List;

public interface JSPFile extends JavaFile {

	List<SearchResult> findJSPTags(String tagName , String[] attrNames , String[] attrValues);

}
