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

import com.aoindustries.noc.monitor.common.TableMultiResult;
import com.aoindustries.noc.monitor.common.TableMultiResultListener;
import com.aoindustries.noc.monitor.wrapper.WrappedTableMultiResultListener;
import com.aoindustries.util.WrappedException;
import java.rmi.RemoteException;

/**
 * @author  AO Industries, Inc.
 */
public class CallableTableMultiResultListener<R extends TableMultiResult> extends WrappedTableMultiResultListener<R> {

	private final CallableMonitor monitor;

	protected CallableTableMultiResultListener(CallableMonitor monitor, TableMultiResultListener<R> wrapped) {
		super(monitor, wrapped);
		this.monitor = monitor;
	}

	@Override
	public final void tableMultiResultAdded(final R multiTableResult) throws RemoteException {
		monitor.run(() -> CallableTableMultiResultListener.super.tableMultiResultAdded(multiTableResult));
	}

	@Override
	public final void tableMultiResultRemoved(final R multiTableResult) throws RemoteException {
		monitor.run(() -> CallableTableMultiResultListener.super.tableMultiResultRemoved(multiTableResult));
	}

	@Override
	public final boolean equals(final Object obj) {
		try {
			return monitor.call(() -> CallableTableMultiResultListener.super.equals(obj));
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}

	@Override
	public final int hashCode() {
		try {
			return monitor.call(CallableTableMultiResultListener.super::hashCode);
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}
}
