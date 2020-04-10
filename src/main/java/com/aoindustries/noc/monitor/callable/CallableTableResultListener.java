/*
 * Copyright 2012, 2020 by AO Industries, Inc.,
 * 7262 Bull Pen Cir, Mobile, Alabama, 36695, U.S.A.
 * All rights reserved.
 */
package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.TableResult;
import com.aoindustries.noc.monitor.common.TableResultListener;
import com.aoindustries.noc.monitor.wrapper.WrappedTableResultListener;
import com.aoindustries.util.WrappedException;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;

/**
 * @author  AO Industries, Inc.
 */
public class CallableTableResultListener extends WrappedTableResultListener {

	final private CallableMonitor monitor;

	protected CallableTableResultListener(CallableMonitor monitor, TableResultListener wrapped) {
		super(monitor, wrapped);
		this.monitor = monitor;
	}

	@Override
	final public void tableResultUpdated(final TableResult tableResult) throws RemoteException {
		monitor.call(
			new Callable<Void>() {
				@Override
				public Void call() throws RemoteException {
					CallableTableResultListener.super.tableResultUpdated(tableResult);
					return null;
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
						return CallableTableResultListener.super.equals(O);
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
						return CallableTableResultListener.super.hashCode();
					}
				}
			);
		} catch(RemoteException e) {
			throw new WrappedException(e);
		}
	}
}
