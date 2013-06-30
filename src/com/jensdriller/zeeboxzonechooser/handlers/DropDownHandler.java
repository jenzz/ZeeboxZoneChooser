package com.jensdriller.zeeboxzonechooser.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class DropDownHandler extends AbstractHandler {

	public DropDownHandler() {
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		String selection = event
				.getParameter("com.jensdriller.zeeboxzonechooser.dropdown.msg");
		selection = selection == null ? "zeebox" : selection;
		
		String path = SettingsHandler.loadBuildConstantsPath();
		path = path != null ? path : "Unknown";
		
		IFile myFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
		System.out.println(myFile.getProject().getName());

		MessageDialog.openInformation(HandlerUtil
				.getActiveWorkbenchWindowChecked(event).getShell(),
				"Popup Window", "You selected " + selection + ".\n"
						+ "Settings path: " + path);

		return null;
	}
}