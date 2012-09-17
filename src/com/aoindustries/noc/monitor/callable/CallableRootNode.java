/*
 * Copyright 2012 by AO Industries, Inc.,
 * 7262 Bull Pen Cir, Mobile, Alabama, 36695, U.S.A.
 * All rights reserved.
 */
package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.MonitoringPoint;
import com.aoindustries.noc.monitor.common.NodeSnapshot;
import com.aoindustries.noc.monitor.common.RootNode;
import com.aoindustries.noc.monitor.common.TreeListener;
import com.aoindustries.noc.monitor.wrapper.WrappedRootNode;
import java.rmi.RemoteException;
import java.util.SortedSet;
import java.util.concurrent.Callable;

/**
 * @author  AO Industries, Inc.
 */
public class CallableRootNode extends WrappedRootNode {

    final private CallableMonitor monitor;

    protected CallableRootNode(CallableMonitor monitor, RootNode wrapped) {
        super(monitor, wrapped);
        this.monitor = monitor;
    }

    @Override
    final public void addTreeListener(final TreeListener treeListener) throws RemoteException {
        monitor.call(
            new Callable<Void>() {
                @Override
                public Void call() throws RemoteException {
                    CallableRootNode.super.addTreeListener(treeListener);
                    return null;
                }
            }
        );
    }

    @Override
    final public void removeTreeListener(final TreeListener treeListener) throws RemoteException {
        monitor.call(
            new Callable<Void>() {
                @Override
                public Void call() throws RemoteException {
                    CallableRootNode.super.removeTreeListener(treeListener);
                    return null;
                }
            }
        );
    }

    @Override
    final public NodeSnapshot getSnapshot() throws RemoteException {
        return monitor.call(
            new Callable<NodeSnapshot>() {
                @Override
                public NodeSnapshot call() throws RemoteException {
                    return CallableRootNode.super.getSnapshot();
                }
            }
        );
    }

    @Override
    final public SortedSet<MonitoringPoint> getMonitoringPoints() throws RemoteException {
        return monitor.call(
            new Callable<SortedSet<MonitoringPoint>>() {
                @Override
                public SortedSet<MonitoringPoint> call() throws RemoteException {
                    return CallableRootNode.super.getMonitoringPoints();
                }
            }
        );
    }
}
