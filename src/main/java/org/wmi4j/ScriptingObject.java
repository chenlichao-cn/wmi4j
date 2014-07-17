/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
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

package org.wmi4j;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by chenlichao on 14-7-17.
 */
class ScriptingObject {

    protected IJIDispatch dispatch;

    ScriptingObject(IJIDispatch dispatch) {
        this.dispatch = dispatch;
    }

    @SuppressWarnings("unchecked")
    protected <T> T callMethod(Class<? extends ScriptingObject> returnType, String methodName, Object ...params) throws WMIException {
        T retVal = null;

        try {
            JIVariant[] results = dispatch.callMethodA(methodName, params);
            IJIDispatch returnDispatch = (IJIDispatch) JIObjectFactory.narrowObject(results[0].getObjectAsComObject());
            retVal = (T) returnType.getConstructor(IJIDispatch.class).newInstance(returnDispatch);
        } catch (JIException e) {
            throw new WMIException(e);
        } catch (InvocationTargetException e) {
            throw new WMIException(0x01000001, "Bug of wmi4j, please send log to reacher-chen@163.com.", e);
        } catch (NoSuchMethodException e) {
            throw new WMIException(0x01000001, "Bug of wmi4j, please send log to reacher-chen@163.com.", e);
        } catch (InstantiationException e) {
            throw new WMIException(0x01000001, "Bug of wmi4j, please send log to reacher-chen@163.com.", e);
        } catch (IllegalAccessException e) {
            throw new WMIException(0x01000001, "Bug of wmi4j, please send log to reacher-chen@163.com.", e);
        }

        return retVal;
    }
}
