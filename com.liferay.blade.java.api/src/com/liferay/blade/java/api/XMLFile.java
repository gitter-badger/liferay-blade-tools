package com.liferay.blade.java.api;

import java.util.Collection;

public interface  XMLFile extends SourceFile {

	Collection<SearchResult> findTag(String elementName, String elementValue);
}
