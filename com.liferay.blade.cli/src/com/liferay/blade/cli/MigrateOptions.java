package com.liferay.blade.cli;

import com.liferay.blade.cli.cmds.Format;

import aQute.lib.getopt.Arguments;
import aQute.lib.getopt.Description;
import aQute.lib.getopt.Options;

@Arguments(arg = {"projectDir", "[reportFile]"})
public interface MigrateOptions extends Options {

	@Description("Determines if the report format will be short or long")
	public boolean detailed();

	public Format format();

}