/*
 * noc-monitor-callable - Wrapper for implementing hooks and filters on Monitoring API.
 * Copyright (C) 2012, 2020, 2021, 2022  AO Industries, Inc.
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
 * along with noc-monitor-callable.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.AlertLevel;
import com.aoindustries.noc.monitor.common.Node;
import com.aoindustries.noc.monitor.wrapper.WrappedNode;
import com.aoindustries.util.WrappedException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

/**
 * @author  AO Industries, Inc.
 */
public class CallableNode extends WrappedNode {

	private final CallableMonitor monitor;

	protected CallableNode(CallableMonitor monitor, Node wrapped) {
		super(monitor, wrapped);
		this.monitor = monitor;
	}

	@Override
	public final CallableNode getParent() throws RemoteException {
		return monitor.call(() -> (CallableNode)CallableNode.super.getParent());
	}

	@Override
	public final List<? extends CallableNode> getChildren() throws RemoteException {
		return monitor.call(() -> (List<? extends CallableNode>)CallableNode.super.getChildren());
	}

	@Override
	public final AlertLevel getAlertLevel() throws RemoteException {
		return monitor.call(CallableNode.super::getAlertLevel);
	}

	@Override
	public final String getAlertMessage() throws RemoteException {
		return monitor.call(CallableNode.super::getAlertMessage);
	}

	@Override
	public final boolean getAllowsChildren() throws RemoteException {
		return monitor.call(CallableNode.super::getAllowsChildren);
	}

	@Override
	public final String getId() throws RemoteException {
		return monitor.call(CallableNode.super::getId);
	}

	@Override
	public final String getLabel() throws RemoteException {
		return monitor.call(() -> CallableNode.super::getLabel);
	}

	@Override
	public final UUID getUuid() throws RemoteException {
		return monitor.call(CallableNode.super::getUuid);
	}

	@Override
	public final boolean equals(final Object obj) {
		try {
			return monitor.call(() -> CallableNode.super.equals(obj));
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}

	@Override
	public final int hashCode() {
		try {
			return monitor.call(CallableNode.super::hashCode);
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}

	@Override
	public final String toString() {
		try {
			return monitor.call(CallableNode.super::toString);
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}
}
