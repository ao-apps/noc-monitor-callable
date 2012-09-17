/*
 * Copyright 2012 by AO Industries, Inc.,
 * 7262 Bull Pen Cir, Mobile, Alabama, 36695, U.S.A.
 * All rights reserved.
 */
package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.SingleResult;
import com.aoindustries.noc.monitor.common.SingleResultListener;
import com.aoindustries.noc.monitor.wrapper.WrappedSingleResultListener;
import com.aoindustries.util.WrappedException;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;

/**
 * @author  AO Industries, Inc.
 */
public class CallableSingleResultListener extends WrappedSingleResultListener {

    final private CallableMonitor monitor;

    protected CallableSingleResultListener(CallableMonitor monitor, SingleResultListener wrapped) {
        super(monitor, wrapped);
        this.monitor = monitor;
    }

    @Override
    final public void singleResultUpdated(final SingleResult singleResult) throws RemoteException {
        monitor.call(
            new Callable<Void>() {
                @Override
                public Void call() throws RemoteException {
                    CallableSingleResultListener.super.singleResultUpdated(singleResult);
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
                        return CallableSingleResultListener.super.equals(O);
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
                        return CallableSingleResultListener.super.hashCode();
                    }
                }
            );
        } catch(RemoteException e) {
            throw new WrappedException(e);
        }
    }
}
