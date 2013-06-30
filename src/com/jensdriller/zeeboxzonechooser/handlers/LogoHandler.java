package com.jensdriller.zeeboxzonechooser.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class LogoHandler extends AbstractHandler {

	public LogoHandler() {
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		String path = SettingsHandler.loadBuildConstantsPath();

		String message = null;
		if (path != null) {
			message = "You're all set! :-)" //
					+ "\n\n" //
					+ "Your current BuildConstants.java:" //
					+ "\n" //
					+ path //
					+ "\n\n" //
					+ "Choose any TVC from the dropdown menu to launch the app.";
		} else {
			message = "Oops, you haven't told me where to find your BuildConstants.java yet." //
					+ "\n\n" //
					+ "Open the settings and choose the correct file.";
		}

		MessageDialog.openInformation(HandlerUtil
				.getActiveWorkbenchWindowChecked(event).getShell(),
				"Zeebox Zone Chooser", message);

		return null;
	}
}