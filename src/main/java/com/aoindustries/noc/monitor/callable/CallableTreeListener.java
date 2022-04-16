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

import com.aoindustries.noc.monitor.common.AlertLevelChange;
import com.aoindustries.noc.monitor.common.TreeListener;
import com.aoindustries.noc.monitor.wrapper.WrappedTreeListener;
import com.aoindustries.util.WrappedException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author  AO Industries, Inc.
 */
public class CallableTreeListener extends WrappedTreeListener {

	private final CallableMonitor monitor;

	protected CallableTreeListener(CallableMonitor monitor, TreeListener wrapped) {
		super(monitor, wrapped);
		this.monitor = monitor;
	}

	@Override
	public final void nodeAdded() throws RemoteException {
		monitor.run(CallableTreeListener.super:nodeAdded);
	}

	@Override
	public final void nodeRemoved() throws RemoteException {
		monitor.run(CallableTreeListener.super::nodeRemoved);
	}

	@Override
	public final void nodeAlertLevelChanged(final List<AlertLevelChange> changes) throws RemoteException {
		monitor.run(() -> CallableTreeListener.super.nodeAlertLevelChanged(changes));
	}

	@Override
	public final boolean equals(final Object obj) {
		try {
			return monitor.call(() -> CallableTreeListener.super.equals(obj));
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}

	@Override
	public final int hashCode() {
		try {
			return monitor.call(CallableTreeListener.super::hashCode);
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}
}
