package com.liferay.blade.migration.liferay70.apichanges;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.blade.java.api.JavaFile;
import com.liferay.blade.java.api.SearchResult;
import com.liferay.blade.migration.api.FileMigrator;
import com.liferay.blade.migration.liferay70.JavaFileMigrator;

@Component(
	property = {
		"file.extensions=java,jsp,jspf",
		"problem.title=Removed Trash Logic from DLAppHelperLocalService Methods",
		"problem.section=#removed-trash-logic-from-dlapphelperlocalservice-methods",
		"problem.summary=Removed Trash Logic from DLAppHelperLocalService Methods",
		"problem.tickets=LPS-47508",
	},
	service = FileMigrator.class
)
public class DLAppHelperLocalServiceUtilInvocation extends JavaFileMigrator {

    @Override
    protected List<SearchResult> searchFile(File file, JavaFile javaFileChecker) {
        final List<SearchResult> result = new ArrayList<SearchResult>();

        result.addAll(
            javaFileChecker.findMethodInvocations(
                null, "DLAppHelperLocalServiceUtil", "deleteFileEntry", null));

        result.addAll(
            javaFileChecker.findMethodInvocations(
                null, "DLAppHelperLocalServiceUtil", "deleteFolder", null) );

        result.addAll(
            javaFileChecker.findMethodInvocations(
                null, "DLAppHelperLocalServiceUtil", "moveFileEntry", null));

        result.addAll(
            javaFileChecker.findMethodInvocations(
                null, "DLAppHelperLocalServiceUtil", "moveFolder", null) );

        result.addAll(
            javaFileChecker.findMethodInvocations(
                null, "DLAppHelperLocalServiceUtil", "addFileEntry", null) );

        return result;
    }

}
