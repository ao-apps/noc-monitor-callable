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

import com.aoindustries.noc.monitor.common.TableMultiResult;
import com.aoindustries.noc.monitor.common.TableMultiResultListener;
import com.aoindustries.noc.monitor.common.TableMultiResultNode;
import com.aoindustries.noc.monitor.wrapper.WrappedTableMultiResultNode;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author  AO Industries, Inc.
 */
public class CallableTableMultiResultNode<R extends TableMultiResult> extends WrappedTableMultiResultNode<R> {

	final private CallableMonitor monitor;

	protected CallableTableMultiResultNode(CallableMonitor monitor, TableMultiResultNode<R> wrapped) {
		super(monitor, wrapped);
		this.monitor = monitor;
	}

	@Override
	final public void addTableMultiResultListener(final TableMultiResultListener<? super R> tableMultiResultListener) throws RemoteException {
		monitor.call(
			new Callable<Void>() {
				@Override
				public Void call() throws RemoteException {
					CallableTableMultiResultNode.super.addTableMultiResultListener(tableMultiResultListener);
					return null;
				}
			}
		);
	}

	@Override
	final public void removeTableMultiResultListener(final TableMultiResultListener<? super R> tableMultiResultListener) throws RemoteException {
		monitor.call(
			new Callable<Void>() {
				@Override
				public Void call() throws RemoteException {
					CallableTableMultiResultNode.super.removeTableMultiResultListener(tableMultiResultListener);
					return null;
				}
			}
		);
	}

	@Override
	final public List<?> getColumnHeaders() throws RemoteException {
		return monitor.call(
			new Callable<List<?>>() {
				@Override
				public List<?> call() throws RemoteException {
					return CallableTableMultiResultNode.super.getColumnHeaders();
				}
			}
		);
	}

	@Override
	final public List<? extends R> getResults() throws RemoteException {
		return monitor.call(
			new Callable<List<? extends R>>() {
				@Override
				public List<? extends R> call() throws RemoteException {
					return CallableTableMultiResultNode.super.getResults();
				}
			}
		);
	}
}
