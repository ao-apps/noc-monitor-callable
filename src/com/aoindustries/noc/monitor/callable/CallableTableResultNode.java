/*
 * Copyright 2012 by AO Industries, Inc.,
 * 7262 Bull Pen Cir, Mobile, Alabama, 36695, U.S.A.
 * All rights reserved.
 */
package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.TableResult;
import com.aoindustries.noc.monitor.common.TableResultListener;
import com.aoindustries.noc.monitor.common.TableResultNode;
import com.aoindustries.noc.monitor.wrapper.WrappedTableResultNode;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;

/**
 * @author  AO Industries, Inc.
 */
public class CallableTableResultNode extends WrappedTableResultNode {

    final private CallableMonitor monitor;

    protected CallableTableResultNode(CallableMonitor monitor, TableResultNode wrapped) {
        super(monitor, wrapped);
        this.monitor = monitor;
    }

    @Override
    final public void addTableResultListener(final TableResultListener tableResultListener) throws RemoteException {
        monitor.call(
            new Callable<Void>() {
                @Override
                public Void call() throws RemoteException {
                    CallableTableResultNode.super.addTableResultListener(tableResultListener);
                    return null;
                }
            }
        );
    }

    @Override
    final public void removeTableResultListener(final TableResultListener tableResultListener) throws RemoteException {
        monitor.call(
            new Callable<Void>() {
                @Override
                public Void call() throws RemoteException {
                    CallableTableResultNode.super.removeTableResultListener(tableResultListener);
                    return null;
                }
            }
        );
    }

    @Override
    final public TableResult getLastResult() throws RemoteException {
        return monitor.call(
            new Callable<TableResult>() {
                @Override
                public TableResult call() throws RemoteException {
                    return CallableTableResultNode.super.getLastResult();
                }
            }
        );
    }
}
