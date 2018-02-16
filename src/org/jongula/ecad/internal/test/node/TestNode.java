/**
 * @package:	org.jongula.ecad.node
 * @file:		TestNode.java
 * @brief:      Testing class for state data at specific circuit point.
 * 
 * @copyright:	Copyright (C) 2018 Kuhrman Technology Solutions LLC
 * @license:	GPLv3+: GNU GPL version 3
 * @authors:	Karl Kuhrman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jongula.ecad.internal.test.node;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.jongula.ecad.node.INode;
import org.jongula.ecad.node.INodeSensor;

public class TestNode implements INode {
	/**
	 * 
	 */
	private ArrayList<INodeSensor> sensors;
	
	/**
	 * 
	 */
	private Job job;
	
	/**
	 * 
	 */
	private boolean isRunning;
	
	/**
	 * 
	 */
	public TestNode() {
		super();
		sensors = new ArrayList<INodeSensor>();
	}
	
	/* (non-Javadoc)
	 * @see org.jongula.ecad.node.INode#addSensor(org.jongula.ecad.node.INodeSensor)
	 */
	@Override
	public synchronized void addSensor(INodeSensor sensor) {
		sensors.add(sensor);
	}
	
	/* (non-Javadoc)
	 * @see org.jongula.ecad.node.INode#removeSensor(org.jongula.ecad.node.INodeSensor)
	 */
	@Override
	public synchronized void removeSensor(INodeSensor sensor) {
			sensors.remove(sensor);
	}
	
	/*
	 * 
	 */
	public synchronized void shutdown() {
		isRunning = false;
		job.cancel();
		try {
			job.join();
		}
		catch (InterruptedException e) {
			// shutting down safe to ignore
		}
	}
	
	/*
	 * 
	 */
	public synchronized void startup() {
		isRunning = true;
		job = new Job("TestNode") {
			protected IStatus run(IProgressMonitor monitor) {
				signal();
				if (isRunning)
					schedule(5000);
				return Status.OK_STATUS;
			}
		};
		job.schedule(5000);
	}
	/*
	 * A private testing method signals node at given interval.
	 */
	private synchronized void signal() {
		for (Iterator<INodeSensor> i = sensors.iterator(); i.hasNext();)
			((INodeSensor) i.next()).signaled();
	}
}
