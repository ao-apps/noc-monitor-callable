/*
 * Copyright 2012 by AO Industries, Inc.,
 * 7262 Bull Pen Cir, Mobile, Alabama, 36695, U.S.A.
 * All rights reserved.
 */
package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.Monitor;
import com.aoindustries.noc.monitor.common.Node;
import com.aoindustries.noc.monitor.common.RootNode;
import com.aoindustries.noc.monitor.common.SingleResultListener;
import com.aoindustries.noc.monitor.common.SingleResultNode;
import com.aoindustries.noc.monitor.common.TableMultiResult;
import com.aoindustries.noc.monitor.common.TableMultiResultListener;
import com.aoindustries.noc.monitor.common.TableMultiResultNode;
import com.aoindustries.noc.monitor.common.TableResultListener;
import com.aoindustries.noc.monitor.common.TableResultNode;
import com.aoindustries.noc.monitor.common.TreeListener;
import com.aoindustries.noc.monitor.wrapper.WrappedMonitor;
import com.aoindustries.util.WrappedException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.concurrent.Callable;

/**
 * Wraps a monitor, provides a single call method that all calls are sent through.
 *
 * @author  AO Industries, Inc.
 */
public class CallableMonitor extends WrappedMonitor {

    public CallableMonitor(Monitor wrapped) {
        super(wrapped);
    }

    public CallableMonitor() {
        super();
    }

    // <editor-fold defaultstate="collapsed" desc="Callable">
    /**
     * Performs the call on the wrapped object, allowing retry.
     */
    final protected <T> T call(Callable<T> callable) throws RemoteException {
        return call(callable, true);
    }

    /**
     * Performs the call on the wrapped object.  This is the main hook to intercept requests
     * for features like auto-reconnects, timeouts, and retries.
     */
    protected <T> T call(Callable<T> callable, boolean allowRetry) throws RemoteException {
        try {
            return callable.call();
        } catch(RemoteException err) {
            throw err;
        } catch(Exception err) {
            throw new RuntimeException(err.getMessage(), err);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Monitor">
    /**
     * Gets the root node for the given locale, username, and password.  May
     * reuse existing root nodes.
     */
    @Override
    final public CallableRootNode login(final Locale locale, final String username, final String password) throws RemoteException, IOException, SQLException {
        try {
            return call(
                new Callable<CallableRootNode>() {
                    @Override
                    public CallableRootNode call() throws RemoteException {
                        try {
                            return (CallableRootNode)CallableMonitor.super.login(locale, username, password);
                        } catch(IOException e) {
                            throw new WrappedException(e);
                        } catch(SQLException e) {
                            throw new WrappedException(e);
                        }
                    }
                }
            );
        } catch(WrappedException e) {
            Throwable cause = e.getCause();
            if(cause instanceof IOException) throw (IOException)cause;
            if(cause instanceof SQLException) throw (SQLException)cause;
            throw e;
        }
    }
    // </editor-fold>

    @Override
    protected CallableNode newWrappedNode(Node node) throws RemoteException {
        return new CallableNode(this, node);
    }

    @Override
    protected CallableRootNode newWrappedRootNode(RootNode node) throws RemoteException {
        return new CallableRootNode(this, node);
    }

    @Override
    protected CallableSingleResultNode newWrappedSingleResultNode(SingleResultNode node) throws RemoteException {
        return new CallableSingleResultNode(this, node);
    }

    @Override
    protected <R extends TableMultiResult> CallableTableMultiResultNode<R> newWrappedTableMultiResultNode(TableMultiResultNode<R> node) throws RemoteException {
        return new CallableTableMultiResultNode<R>(this, node);
    }

    @Override
    protected CallableTableResultNode newWrappedTableResultNode(TableResultNode node) throws RemoteException {
        return new CallableTableResultNode(this, node);
    }

    @Override
    protected CallableTreeListener newWrappedTreeListener(TreeListener treeListener) throws RemoteException {
        return new CallableTreeListener(this, treeListener);
    }

    @Override
    protected CallableSingleResultListener newWrappedSingleResultListener(SingleResultListener singleResultListener) throws RemoteException {
        return new CallableSingleResultListener(this, singleResultListener);
    }

    @Override
    protected <R extends TableMultiResult> CallableTableMultiResultListener<R> newWrappedTableMultiResultListener(TableMultiResultListener<R> tableMultiResultListener) throws RemoteException {
        return new CallableTableMultiResultListener<R>(this, tableMultiResultListener);
    }

    @Override
    protected CallableTableResultListener newWrappedTableResultListener(TableResultListener tableResultListener) throws RemoteException {
        return new CallableTableResultListener(this, tableResultListener);
    }
}
