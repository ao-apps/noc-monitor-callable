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
import com.aoindustries.noc.monitor.wrapper.WrappedSingleResultListener;
import com.aoindustries.util.WrappedException;
import java.rmi.RemoteException;

/**
 * @author  AO Industries, Inc.
 */
public class CallableSingleResultListener extends WrappedSingleResultListener {

  private final CallableMonitor monitor;

  protected CallableSingleResultListener(CallableMonitor monitor, SingleResultListener wrapped) {
    super(monitor, wrapped);
    this.monitor = monitor;
  }

  @Override
  public final void singleResultUpdated(final SingleResult singleResult) throws RemoteException {
    monitor.run(() -> CallableSingleResultListener.super.singleResultUpdated(singleResult));
  }

  @Override
  public final boolean equals(final Object obj) {
    try {
      return monitor.call(() -> CallableSingleResultListener.super.equals(obj));
    } catch (RemoteException e) {
      throw new WrappedException(e);
    }
  }

  @Override
  public final int hashCode() {
    try {
      return monitor.call(CallableSingleResultListener.super::hashCode);
    } catch (RemoteException e) {
      throw new WrappedException(e);
    }
  }
}
