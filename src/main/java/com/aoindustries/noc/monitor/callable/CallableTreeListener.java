/*
 * Copyright 2012, 2020 by AO Industries, Inc.,
 * 7262 Bull Pen Cir, Mobile, Alabama, 36695, U.S.A.
 * All rights reserved.
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

	final private CallableMonitor monitor;

	protected CallableTreeListener(CallableMonitor monitor, TreeListener wrapped) {
		super(monitor, wrapped);
		this.monitor = monitor;
	}

	@Override
	final public void nodeAdded() throws RemoteException {
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
	final public void nodeRemoved() throws RemoteException {
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
	final public void nodeAlertLevelChanged(final List<AlertLevelChange> changes) throws RemoteException {
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
	final public boolean equals(final Object O) {
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
	final public int hashCode() {
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
