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

import com.aoindustries.noc.monitor.common.SingleResult;
import com.aoindustries.noc.monitor.common.SingleResultListener;
import com.aoindustries.noc.monitor.common.SingleResultNode;
import com.aoindustries.noc.monitor.wrapper.WrappedSingleResultNode;
import java.rmi.RemoteException;

/**
 * @author  AO Industries, Inc.
 */
public class CallableSingleResultNode extends WrappedSingleResultNode {

  private final CallableMonitor monitor;

  protected CallableSingleResultNode(CallableMonitor monitor, SingleResultNode wrapped) {
    super(monitor, wrapped);
    this.monitor = monitor;
  }

  @Override
  public final void addSingleResultListener(final SingleResultListener singleResultListener) throws RemoteException {
    monitor.run(() -> CallableSingleResultNode.super.addSingleResultListener(singleResultListener));
  }

  @Override
  public final void removeSingleResultListener(final SingleResultListener singleResultListener) throws RemoteException {
    monitor.run(() -> CallableSingleResultNode.super.removeSingleResultListener(singleResultListener));
  }

  @Override
  public final SingleResult getLastResult() throws RemoteException {
    return monitor.call(CallableSingleResultNode.super::getLastResult);
  }
}
