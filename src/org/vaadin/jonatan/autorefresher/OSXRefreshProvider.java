package org.vaadin.jonatan.autorefresher;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;
import net.contentobjects.jnotify.JNotifyListener;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.refresh.IRefreshMonitor;
import org.eclipse.core.resources.refresh.IRefreshResult;
import org.eclipse.core.resources.refresh.RefreshProvider;

public class OSXRefreshProvider extends RefreshProvider implements IRefreshMonitor, JNotifyListener {

	private final static int MASK = JNotify.FILE_ANY;
	
	private int watchId;

	private IRefreshResult result;

	private IResource resource;

	@Override
	public IRefreshMonitor installMonitor(IResource resource, IRefreshResult result) {
		this.resource = resource;
		this.result = result;
		startMonitoring(resource.getLocation().toOSString());
		return this;
	}
	
	public void startMonitoring(String path) {
		try {
			watchId = JNotify.addWatch(path, MASK, true, this);
		} catch (JNotifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unmonitor(IResource arg0) {
		try {
			JNotify.removeWatch(watchId);
		} catch (JNotifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fileCreated(int wd, String rootPath, String name) {
		result.refresh(resource);
	}

	public void fileDeleted(int wd, String rootPath, String name) {
		result.refresh(resource);
	}

	public void fileModified(int wd, String rootPath, String name) {
		result.refresh(resource);
	}

	public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
		result.refresh(resource);
	}

}
