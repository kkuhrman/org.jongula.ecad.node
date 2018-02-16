package org.jongula.ecad.node;

public interface INode {

	/**
	 * 
	 * @param INodeSensor sensor
	 */
	void addSensor(INodeSensor sensor);

	/**
	 * 
	 * @param sensor
	 */
	void removeSensor(INodeSensor sensor);

}