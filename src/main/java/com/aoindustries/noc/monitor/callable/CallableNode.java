/*
 * noc-monitor-callable - Wrapper for implementing hooks and filters on Monitoring API.
 * Copyright (C) 2012, 2020  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of noc-monitor-callable.
 *
 * noc-monitor-callable is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * noc-monitor-callable is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with noc-monitor-callable.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.AlertLevel;
import com.aoindustries.noc.monitor.common.Node;
import com.aoindustries.noc.monitor.wrapper.WrappedNode;
import com.aoindustries.util.WrappedException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author  AO Industries, Inc.
 */
public class CallableNode extends WrappedNode {

	final private CallableMonitor monitor;

	protected CallableNode(CallableMonitor monitor, Node wrapped) {
		super(monitor, wrapped);
		this.monitor = monitor;
	}

	@Override
	final public CallableNode getParent() throws RemoteException {
		return monitor.call(
			new Callable<CallableNode>() {
				@Override
				public CallableNode call() throws RemoteException {
					return (CallableNode)CallableNode.super.getParent();
				}
			}
		);
	}

	@Override
	final public List<? extends CallableNode> getChildren() throws RemoteException {
		return monitor.call(
			new Callable<List<? extends CallableNode>>() {
				@Override
				@SuppressWarnings("unchecked")
				public List<? extends CallableNode> call() throws RemoteException {
					return (List<? extends CallableNode>)CallableNode.super.getChildren();
				}
			}
		);
	}

	@Override
	final public AlertLevel getAlertLevel() throws RemoteException {
		return monitor.call(
			new Callable<AlertLevel>() {
				@Override
				public AlertLevel call() throws RemoteException {
					return CallableNode.super.getAlertLevel();
				}
			}
		);
	}

	@Override
	final public String getAlertMessage() throws RemoteException {
		return monitor.call(
			new Callable<String>() {
				@Override
				public String call() throws RemoteException {
					return CallableNode.super.getAlertMessage();
				}
			}
		);
	}

	@Override
	final public boolean getAllowsChildren() throws RemoteException {
		return monitor.call(
			new Callable<Boolean>() {
				@Override
				public Boolean call() throws RemoteException {
					return CallableNode.super.getAllowsChildren();
				}
			}
		);
	}

	@Override
	final public String getId() throws RemoteException {
		return monitor.call(
			new Callable<String>() {
				@Override
				public String call() throws RemoteException {
					return CallableNode.super.getId();
				}
			}
		);
	}

	@Override
	final public String getLabel() throws RemoteException {
		return monitor.call(
			new Callable<String>() {
				@Override
				public String call() throws RemoteException {
					return CallableNode.super.getLabel();
				}
			}
		);
	}

	@Override
	final public UUID getUuid() throws RemoteException {
		return monitor.call(
			new Callable<UUID>() {
				@Override
				public UUID call() throws RemoteException {
					return CallableNode.super.getUuid();
				}
			}
		);
	}

	@Override
	final public boolean equals(final Object O) {
		try {
			return monitor.call(
				new Callable<Boolean>() {
					@Override
					public Boolean call() {
						return CallableNode.super.equals(O);
					}
				}
			);
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}

	@Override
	final public int hashCode() {
		try {
			return monitor.call(
				new Callable<Integer>() {
					@Override
					public Integer call() {
						return CallableNode.super.hashCode();
					}
				}
			);
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}

	@Override
	final public String toString() {
		try {
			return monitor.call(
				new Callable<String>() {
					@Override
					public String call() {
						return CallableNode.super.toString();
					}
				}
			);
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}
}
