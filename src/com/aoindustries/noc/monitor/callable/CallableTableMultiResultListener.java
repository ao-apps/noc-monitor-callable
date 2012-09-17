/*
 * Copyright 2012 by AO Industries, Inc.,
 * 7262 Bull Pen Cir, Mobile, Alabama, 36695, U.S.A.
 * All rights reserved.
 */
package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.TableMultiResult;
import com.aoindustries.noc.monitor.common.TableMultiResultListener;
import com.aoindustries.noc.monitor.wrapper.WrappedTableMultiResultListener;
import com.aoindustries.util.WrappedException;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;

/**
 * @author  AO Industries, Inc.
 */
public class CallableTableMultiResultListener<R extends TableMultiResult> extends WrappedTableMultiResultListener<R> {

    final private CallableMonitor monitor;

    protected CallableTableMultiResultListener(CallableMonitor monitor, TableMultiResultListener<R> wrapped) {
        super(monitor, wrapped);
        this.monitor = monitor;
    }

    @Override
    final public void tableMultiResultAdded(final R multiTableResult) throws RemoteException {
        monitor.call(
            new Callable<Void>() {
                @Override
                public Void call() throws RemoteException {
                    CallableTableMultiResultListener.super.tableMultiResultAdded(multiTableResult);
                    return null;
                }
            }
        );
    }

    @Override
    final public void tableMultiResultRemoved(final R multiTableResult) throws RemoteException {
        monitor.call(
            new Callable<Void>() {
                @Override
                public Void call() throws RemoteException {
                    CallableTableMultiResultListener.super.tableMultiResultRemoved(multiTableResult);
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
                        return CallableTableMultiResultListener.super.equals(O);
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
                        return CallableTableMultiResultListener.super.hashCode();
                    }
                }
            );
        } catch(RemoteException e) {
            throw new WrappedException(e);
        }
    }
}
