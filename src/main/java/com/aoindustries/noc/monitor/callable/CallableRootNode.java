/*
 * noc-monitor-callable - Wrapper for implementing hooks and filters on Monitoring API.
 * Copyright (C) 2012, 2020, 2021, 2022  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of noc-monitor-callable.
 *
 * noc-monitor-callable is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * noc-monitor-callable is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with noc-monitor-callable.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.aoindustries.noc.monitor.callable;

import com.aoindustries.noc.monitor.common.MonitoringPoint;
import com.aoindustries.noc.monitor.common.NodeSnapshot;
import com.aoindustries.noc.monitor.common.RootNode;
import com.aoindustries.noc.monitor.common.TreeListener;
import com.aoindustries.noc.monitor.wrapper.WrappedRootNode;
import java.rmi.RemoteException;
import java.util.SortedSet;

/**
 * @author  AO Industries, Inc.
 */
public class CallableRootNode extends WrappedRootNode {

  private final CallableMonitor monitor;

  protected CallableRootNode(CallableMonitor monitor, RootNode wrapped) {
    super(monitor, wrapped);
    this.monitor = monitor;
  }

  @Override
  public final void addTreeListener(final TreeListener treeListener) throws RemoteException {
    monitor.run(() -> CallableRootNode.super.addTreeListener(treeListener));
  }

  @Override
  public final void removeTreeListener(final TreeListener treeListener) throws RemoteException {
    monitor.run(() -> CallableRootNode.super.removeTreeListener(treeListener));
  }

  @Override
  public final NodeSnapshot getSnapshot() throws RemoteException {
    return monitor.call(CallableRootNode.super::getSnapshot);
  }

  @Override
  public final SortedSet<MonitoringPoint> getMonitoringPoints() throws RemoteException {
    return monitor.call(CallableRootNode.super::getMonitoringPoints);
  }
}
