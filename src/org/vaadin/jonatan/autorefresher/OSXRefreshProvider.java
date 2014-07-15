package org.vaadin.jonatan.autorefresher;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.refresh.IRefreshMonitor;
import org.eclipse.core.resources.refresh.IRefreshResult;
import org.eclipse.core.resources.refresh.RefreshProvider;
import org.eclipse.core.runtime.CoreException;
import org.vaadin.jonatan.nativefsevents.NativeFSEvents;
import org.vaadin.jonatan.nativefsevents.NativeFSEvents.NativeFSEventListener;

public class OSXRefreshProvider extends RefreshProvider {

	@Override
	public IRefreshMonitor installMonitor(IResource resource,
			IRefreshResult result) {
		return new Monitor(resource, result);
	}

	private class Monitor implements IRefreshMonitor, NativeFSEventListener {
		private IRefreshResult result;
		private IResource resource;
		private NativeFSEvents fsEvents;

		public Monitor(IResource resource, IRefreshResult result) {
			this.resource = resource;
			this.result = result;
			start();
		}

		private void start() {
			String path = resource.getLocation().toOSString();
			fsEvents = new NativeFSEvents(path, this);
			fsEvents.startMonitoring();
		}

		public void unmonitor(IResource arg0) {
			if (fsEvents != null) {
				fsEvents.stopMonitoring();
				fsEvents = null;
			}
		}

		public void pathModified(String path) {
			try {
				resource.refreshLocal(IResource.DEPTH_INFINITE, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
}
