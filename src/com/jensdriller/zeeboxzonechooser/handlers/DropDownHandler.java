package com.jensdriller.zeeboxzonechooser.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

public class DropDownHandler extends AbstractHandler {

	public DropDownHandler() {
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		String path = SettingsHandler.loadBuildConstantsPath();
		IFile myFile = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(path));
		System.out.println(myFile.getProject().getName());

		return null;
	}
}