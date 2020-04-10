/*
 * Copyright 2012, 2020 by AO Industries, Inc.,
 * 7262 Bull Pen Cir, Mobile, Alabama, 36695, U.S.A.
 * All rights reserved.
 */
package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.SingleResultListener;
import com.aoindustries.noc.monitor.common.SingleResultNode;
import com.aoindustries.noc.monitor.common.SingleResult;
import com.aoindustries.noc.monitor.wrapper.WrappedSingleResultNode;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;

/**
 * @author  AO Industries, Inc.
 */
public class CallableSingleResultNode extends WrappedSingleResultNode {

	final private CallableMonitor monitor;

	protected CallableSingleResultNode(CallableMonitor monitor, SingleResultNode wrapped) {
		super(monitor, wrapped);
		this.monitor = monitor;
	}

	@Override
	final public void addSingleResultListener(final SingleResultListener singleResultListener) throws RemoteException {
		monitor.call(
			new Callable<Void>() {
				@Override
				public Void call() throws RemoteException {
					CallableSingleResultNode.super.addSingleResultListener(singleResultListener);
					return null;
				}
			}
		);
	}

	@Override
	final public void removeSingleResultListener(final SingleResultListener singleResultListener) throws RemoteException {
		monitor.call(
			new Callable<Void>() {
				@Override
				public Void call() throws RemoteException {
					CallableSingleResultNode.super.removeSingleResultListener(singleResultListener);
					return null;
				}
			}
		);
	}

	@Override
	final public SingleResult getLastResult() throws RemoteException {
		return monitor.call(
			new Callable<SingleResult>() {
				@Override
				public SingleResult call() throws RemoteException {
					return CallableSingleResultNode.super.getLastResult();
				}
			}
		);
	}
}
