package com.jensdriller.zeeboxzonechooser.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class DropDownHandler extends AbstractHandler {

	private static final String ZONE_PARAM = "com.jensdriller.zeeboxzonechooser.dropdown.zone";

	public DropDownHandler() {
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		final String path = SettingsHandler.loadBuildConstantsPath();
		final String zone = event.getParameter(ZONE_PARAM);

		try {
			File file = new File(path);
			final BufferedReader reader = new BufferedReader(new FileReader(
					file));
			final StringBuilder contents = new StringBuilder();
			while (reader.ready()) {
				contents.append(reader.readLine() + "\n");
			}
			reader.close();

			String stringContents = contents.toString();
			stringContents = stringContents.replaceAll("BuildType.*;",
					"BuildType." + zone + ";");
			final BufferedWriter writer = new BufferedWriter(new FileWriter(
					file));
			writer.write(stringContents);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}