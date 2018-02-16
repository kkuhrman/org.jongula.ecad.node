package org.jongula.ecad.internal.test.node;

import org.jongula.ecad.node.INode;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	private TestNode node;
	private ServiceRegistration registration;
	
	@Override
	public void start(BundleContext context) throws Exception {
		node = new TestNode();
		node.startup();
		registration = context.registerService(INode.class.getName(),
					node, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		registration.unregister();
		node.shutdown();
	}

}
