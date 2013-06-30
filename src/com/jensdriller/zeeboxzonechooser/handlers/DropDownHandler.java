package com.jensdriller.zeeboxzonechooser.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

public class DropDownHandler extends AbstractHandler {

	public DropDownHandler() {
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);

		String selection = event
				.getParameter("com.jensdriller.zeeboxzonechooser.dropdown.msg");
		selection = selection == null ? "zeebox" : selection;

		String path = SettingsHandler.loadBuildConstantsPath();
		path = path != null ? path : "Unknown";

		MessageDialog.openInformation(window.getShell(), "Popup Window",
				"You selected " + selection + ".\n" + "Settings path: " + path);

		return null;
	}
}