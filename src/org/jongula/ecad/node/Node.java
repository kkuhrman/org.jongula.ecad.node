/**
 * @package:	org.jongula.ecad.node
 * @file:		Node.java
 * @brief:      Base container class for state data at specific circuit point.
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

package org.jongula.ecad.node;

import java.util.ArrayList;
import java.util.Iterator;

public class Node {
	/**
	 * 
	 */
	private ArrayList<INodeSensor> sensors;
	
	/**
	 * 
	 */
	public Node() {
		super();
		sensors = new ArrayList<INodeSensor>();
	}
	
	/**
	 * 
	 * @param INodeSensor sensor
	 */
	public synchronized void addSensor(INodeSensor sensor) {
		sensors.add(sensor);
	}
	
	/**
	 * 
	 */
	public void signal() {
		for (Iterator<INodeSensor> i = sensors.iterator(); i.hasNext();)
			((INodeSensor) i.next()).signaled();
	}
	
	/**
	 * 
	 * @param sensor
	 */
	public synchronized void removeSensor(INodeSensor sensor) {
			sensors.remove(sensor);
	}
}
