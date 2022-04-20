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

import com.aoindustries.noc.monitor.common.TableResult;
import com.aoindustries.noc.monitor.common.TableResultListener;
import com.aoindustries.noc.monitor.common.TableResultNode;
import com.aoindustries.noc.monitor.wrapper.WrappedTableResultNode;
import java.rmi.RemoteException;

/**
 * @author  AO Industries, Inc.
 */
public class CallableTableResultNode extends WrappedTableResultNode {

  private final CallableMonitor monitor;

  protected CallableTableResultNode(CallableMonitor monitor, TableResultNode wrapped) {
    super(monitor, wrapped);
    this.monitor = monitor;
  }

  @Override
  public final void addTableResultListener(final TableResultListener tableResultListener) throws RemoteException {
    monitor.run(() -> CallableTableResultNode.super.addTableResultListener(tableResultListener));
  }

  @Override
  public final void removeTableResultListener(final TableResultListener tableResultListener) throws RemoteException {
    monitor.run(() -> CallableTableResultNode.super.removeTableResultListener(tableResultListener));
  }

  @Override
  public final TableResult getLastResult() throws RemoteException {
    return monitor.call(CallableTableResultNode.super::getLastResult);
  }
}
