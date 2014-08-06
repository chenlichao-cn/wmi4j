/*
 * Copyright 2014-2014 Chen Lichao
 *
 * Licensed to the Apache  Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.chenlichao.wmi4j;

import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Retrieves events from an event query in conjunction with {@link SWbemServices#execNotificationQuery(String, String, Integer, SWbemNamedValueSet) SWbemServices.execNotificationQuery()}.
 * You get an SWbemEventSource object if you make a call to
 * {@link SWbemServices#execNotificationQuery(String, String, Integer, SWbemNamedValueSet) SWbemServices.execNotificationQuery()}.
 * to make an event query.
 * You can then use the {@link #nextEvent} method to retrieve events as they arrive.
 *
 * Created by chenlichao on 14-7-23.
 */
public class SWbemEventSource extends AbstractSecurityScriptingObject {
    SWbemEventSource(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * If an event is available, the nextEvent method retrieves the event from an event query.
     * @param timeoutMs <strong>[Optional]</strong> Number of milliseconds the call waits for an event before returning a time-out error.
     *                  The default value for this parameter is -1, which directs the call to wait indefinitely.
     * @return If successful, it returns an {@link SWbemObject} object that contains the requested event.
     * If the call times out, the returned object is NULL and an error is raised.
     * @throws WMIException
     */
    public SWbemObject nextEvent(Long timeoutMs) throws WMIException {
        return callMethod(SWbemObject.class, "NextEvent",
                (timeoutMs == null) ? JIVariant.OPTIONAL_PARAM() : new JIVariant(timeoutMs));
    }
}
