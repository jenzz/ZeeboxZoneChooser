package com.jensdriller.zeeboxzonechooser.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.jensdriller.zeeboxzonechooser.Activator;

public class SettingsHandler extends AbstractHandler {

	public SettingsHandler() {
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IResource buildConstants = getWorkspaceResourceElement();
		if (buildConstants != null) {
			System.out.println(buildConstants.getFullPath().toString());
		}

		return null;
	}

	public static IResource getWorkspaceResourceElement() {
		IResource resource = null;

		ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				new WorkbenchLabelProvider(),
				new BaseWorkbenchContentProvider());
		dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
		dialog.setTitle("BuildConstants.java");
		dialog.setMessage("Select the BuildConstants.java file from your zeebox project:");
		dialog.setValidator(new ISelectionStatusValidator() {
			public IStatus validate(Object[] selection) {
				if (selection.length == 1 && !(selection[0] instanceof IFile)) {
					return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0,
							"Please select a file!", null);
				}
				return new Status(IStatus.OK, Activator.PLUGIN_ID, 0, "", null);
			}
		});

		int buttonId = dialog.open();
		if (buttonId == IDialogConstants.OK_ID) {
			resource = (IResource) dialog.getFirstResult();
			if (!resource.isAccessible()) {
				return null;
			}
		}
		return resource;
	}

}