package org.opennms.serviceregistration;

import java.lang.ClassNotFoundException;
import org.opennms.serviceregistration.strategies.AppleStrategy;
import org.opennms.serviceregistration.strategies.NullStrategy;

public class ServiceRegistrationFactory {
	private static ServiceRegistrationStrategy s;
	
	private ServiceRegistrationFactory() {
	}

	public static synchronized ServiceRegistrationStrategy getStrategy() throws Exception {
		if (s == null) {
			try {
				s = new AppleStrategy();
			} catch (ClassNotFoundException e) {
				// we try the next thing then
			}
		}
		if (s == null) {
			s = new NullStrategy();
		}
		return s;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singletons cannot be cloned.");
	}

}