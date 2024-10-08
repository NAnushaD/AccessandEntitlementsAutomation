/****************************************************************************
 * 
 * 
 * 
 *  
 *   
****************************************************************************/

package com.intel.ebsqa.draco.helperclass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;

public class TimestampFileAppender extends FileAppender {
	private static final String DOT = ".";
	private static final String UNDERSCORE = "_";

	/**
	 * The date pattern with a format from {@link SimpleDateFormat}. By default,
	 * yyyyMMdd-HH'h'mm'm'ss's'.
	 */
	protected String datePattern = "yyyy-MM-dd-HH-mm-ss";

	/**
	 * The default constructor does not do anything.
	 */
	public TimestampFileAppender() {
	}

	/**
	 * Instantiate a FileAppender and open the file designated by
	 * <code>filename</code>. The opened filename will become the output destination
	 * for this appender.
	 */
	public TimestampFileAppender(Layout layout, String filename, String datePattern) throws IOException {
		super(layout, filename);
		this.datePattern = datePattern;
	}

	/**
	 * The <b>DatePattern</b> takes a string in the same format as expected by
	 * {@link SimpleDateFormat}.
	 */
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	/** Returns the value of the <b>DatePattern</b> option. */
	public String getDatePattern() {
		return datePattern;
	}

	/**
	 * @{inheritedDoc}
	 */
	@Override
	public void activateOptions() {
		if (fileName != null) {
			fileName = insertTimestampInFile(fileName, datePattern);
			super.activateOptions();
		} else {
			LogLog.warn("File option not set for appender [" + name + "].");
			LogLog.warn("Are you using TimestampFileAppender instead of ConsoleAppender?");
		}
	}

	/**
	 * Insert a timestamp based on the given date pattern at the end of the given
	 * filename. Insert it before the extension if present.
	 */
	private static String insertTimestampInFile(String filename, String datePattern) {
		final String timestamp = new SimpleDateFormat(datePattern).format(Calendar.getInstance().getTime());
		StringBuilder builder = new StringBuilder();

		// if the provided filename has an extension, we need to insert the timestamp
		// before the extension.
		final int index1 = filename.lastIndexOf(File.separator);
		final int index2 = filename.lastIndexOf(DOT);
		if (index2 > index1) { // there is an extension, cut before it
			builder.append(filename.substring(0, index2));
		} else {
			builder.append(filename);
		}
		builder.append(UNDERSCORE);
		builder.append(timestamp);
		if (index2 > index1) { // there is an extension, re-append it at the end
			builder.append(filename.substring(index2));
		}
		return builder.toString();
	}

}
