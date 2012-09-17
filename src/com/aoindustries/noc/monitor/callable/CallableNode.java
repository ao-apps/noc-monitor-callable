/*
 * Copyright 2012 by AO Industries, Inc.,
 * 7262 Bull Pen Cir, Mobile, Alabama, 36695, U.S.A.
 * All rights reserved.
 */
package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.AlertLevel;
import com.aoindustries.noc.monitor.common.Node;
import com.aoindustries.noc.monitor.wrapper.WrappedNode;
import com.aoindustries.util.WrappedException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author  AO Industries, Inc.
 */
public class CallableNode extends WrappedNode {

    final private CallableMonitor monitor;

    protected CallableNode(CallableMonitor monitor, Node wrapped) {
        super(monitor, wrapped);
        this.monitor = monitor;
    }

    @Override
    final public CallableNode getParent() throws RemoteException {
        return monitor.call(
            new Callable<CallableNode>() {
                @Override
                public CallableNode call() throws RemoteException {
                    return (CallableNode)CallableNode.super.getParent();
                }
            }
        );
    }

    @Override
    final public List<? extends CallableNode> getChildren() throws RemoteException {
        return monitor.call(
            new Callable<List<? extends CallableNode>>() {
                @Override
                @SuppressWarnings("unchecked")
                public List<? extends CallableNode> call() throws RemoteException {
                    return (List<? extends CallableNode>)CallableNode.super.getChildren();
                }
            }
        );
    }

    @Override
    final public AlertLevel getAlertLevel() throws RemoteException {
        return monitor.call(
            new Callable<AlertLevel>() {
                @Override
                public AlertLevel call() throws RemoteException {
                    return CallableNode.super.getAlertLevel();
                }
            }
        );
    }

    @Override
    final public String getAlertMessage() throws RemoteException {
        return monitor.call(
            new Callable<String>() {
                @Override
                public String call() throws RemoteException {
                    return CallableNode.super.getAlertMessage();
                }
            }
        );
    }

    @Override
    final public boolean getAllowsChildren() throws RemoteException {
        return monitor.call(
            new Callable<Boolean>() {
                @Override
                public Boolean call() throws RemoteException {
                    return CallableNode.super.getAllowsChildren();
                }
            }
        );
    }

    @Override
    final public String getId() throws RemoteException {
        return monitor.call(
            new Callable<String>() {
                @Override
                public String call() throws RemoteException {
                    return CallableNode.super.getId();
                }
            }
        );
    }

    @Override
    final public String getLabel() throws RemoteException {
        return monitor.call(
            new Callable<String>() {
                @Override
                public String call() throws RemoteException {
                    return CallableNode.super.getLabel();
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
                        return CallableNode.super.equals(O);
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
                        return CallableNode.super.hashCode();
                    }
                }
            );
        } catch(RemoteException e) {
            throw new WrappedException(e);
        }
    }
}
