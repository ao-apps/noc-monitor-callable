/*
 * Copyright 2012 by AO Industries, Inc.,
 * 7262 Bull Pen Cir, Mobile, Alabama, 36695, U.S.A.
 * All rights reserved.
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
