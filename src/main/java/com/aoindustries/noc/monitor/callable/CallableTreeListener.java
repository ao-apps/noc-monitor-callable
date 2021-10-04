/*
 * noc-monitor-callable - Wrapper for implementing hooks and filters on Monitoring API.
 * Copyright (C) 2012, 2020, 2021  AO Industries, Inc.
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

import com.aoindustries.noc.monitor.common.AlertLevelChange;
import com.aoindustries.noc.monitor.common.TreeListener;
import com.aoindustries.noc.monitor.wrapper.WrappedTreeListener;
import com.aoindustries.util.WrappedException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.Callable;

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
		monitor.call(
			new Callable<Void>() {
				@Override
				public Void call() throws RemoteException {
					CallableTreeListener.super.nodeAdded();
					return null;
				}
			}
		);
	}

	@Override
	public final void nodeRemoved() throws RemoteException {
		monitor.call(
			new Callable<Void>() {
				@Override
				public Void call() throws RemoteException {
					CallableTreeListener.super.nodeRemoved();
					return null;
				}
			}
		);
	}

	@Override
	public final void nodeAlertLevelChanged(final List<AlertLevelChange> changes) throws RemoteException {
		monitor.call(
			new Callable<Void>() {
				@Override
				public Void call() throws RemoteException {
					CallableTreeListener.super.nodeAlertLevelChanged(changes);
					return null;
				}
			}
		);
	}

	@Override
	public final boolean equals(final Object O) {
		try {
			return monitor.call(
				new Callable<Boolean>() {
					@Override
					public Boolean call() {
						return CallableTreeListener.super.equals(O);
					}
				}
			);
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}

	@Override
	public final int hashCode() {
		try {
			return monitor.call(
				new Callable<Integer>() {
					@Override
					public Integer call() {
						return CallableTreeListener.super.hashCode();
					}
				}
			);
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}
}
