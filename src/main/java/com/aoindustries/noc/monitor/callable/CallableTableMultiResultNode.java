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

import com.aoindustries.noc.monitor.common.TableMultiResult;
import com.aoindustries.noc.monitor.common.TableMultiResultListener;
import com.aoindustries.noc.monitor.common.TableMultiResultNode;
import com.aoindustries.noc.monitor.wrapper.WrappedTableMultiResultNode;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author  AO Industries, Inc.
 */
public class CallableTableMultiResultNode<R extends TableMultiResult> extends WrappedTableMultiResultNode<R> {

  private final CallableMonitor monitor;

  protected CallableTableMultiResultNode(CallableMonitor monitor, TableMultiResultNode<R> wrapped) {
    super(monitor, wrapped);
    this.monitor = monitor;
  }

  @Override
  public final void addTableMultiResultListener(final TableMultiResultListener<? super R> tableMultiResultListener) throws RemoteException {
    monitor.run(() -> CallableTableMultiResultNode.super.addTableMultiResultListener(tableMultiResultListener));
  }

  @Override
  public final void removeTableMultiResultListener(final TableMultiResultListener<? super R> tableMultiResultListener) throws RemoteException {
    monitor.run(() -> CallableTableMultiResultNode.super.removeTableMultiResultListener(tableMultiResultListener));
  }

  @Override
  public final List<?> getColumnHeaders() throws RemoteException {
    return monitor.call(CallableTableMultiResultNode.super::getColumnHeaders);
  }

  @Override
  public final List<? extends R> getResults() throws RemoteException {
    return monitor.call(CallableTableMultiResultNode.super::getResults);
  }
}
