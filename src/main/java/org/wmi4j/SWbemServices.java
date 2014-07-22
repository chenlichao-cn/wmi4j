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

import org.apache.commons.lang3.StringUtils;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * <p>You can use the methods of an SWbemServices object to perform operations against a namespace
 * on either a local host or a remote host. SWbemServices object can be initialized by call
 * {@linkplain org.wmi4j.SWbemLocator#connectServer(String, String, org.wmi4j.SWbemLocator.SecurityFlag, SWbemNamedValueSet) SWbemLocator.connectServer()}
 * method only.</p>
 * Created by chenlichao on 14-7-17.
 */
public class SWbemServices extends ScriptingObject{

    SWbemServices(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Retrieves an object, that is either a class definition or an instance, based on the object path.
     * This method retrieves only objects from the namespace that is associated with the current SWbemServices object.
     *
     * @param objectPath <strong>[Optional]</strong> String that contains the object path of the object to retrieve.
     *                   If this value is empty, the empty object that is returned can become a new class.
     * @param flags <strong>[Optional]</strong> Integer that determines the behavior of the query.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If successful, this method returns an {@link org.wmi4j.SWbemObject} object that represents the requested object.
     * @throws WMIException
     */
    public SWbemObject get(String objectPath, GetFlag flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException{
        return callMethod(SWbemObject.class, "Get",
                StringUtils.isEmpty(objectPath) ? JIVariant.OPTIONAL_PARAM() : new JIString(objectPath),
                (flags == null) ? JIVariant.OPTIONAL_PARAM() : flags.getValue(),
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Creates an enumerator that
     * returns the instances of a specified class according to the user-specified selection criteria.
     * This method implements a simple query. More complex queries may require the use of
     * {@linkplain #execQuery(String, String, org.wmi4j.SWbemServices.ExecQueryFlag, SWbemNamedValueSet) SWbemService.execQuery} method.
     * @param className String that contains the name of the class for which instances are desired. This parameter cannot be blank.
     * @param flags This parameter determines how detailed the call enumerates and if this call returns immediately.
     *              The default value for this parameter is {@linkplain org.wmi4j.SWbemServices.InstancesOfFlag#wbemFlagReturnImmediately wbemFlagReturnImmediately}.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return
     * @throws WMIException
     */
    public SWbemObjectSet instancesOf(String className, InstancesOfFlag flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        if(StringUtils.isEmpty(className)) {
            throw new IllegalArgumentException("Class name is empty.");
        }
        return callMethod(SWbemObjectSet.class, "InstancesOf",
                new JIString(className),
                (flags == null) ? JIVariant.OPTIONAL_PARAM() : flags.getValue(),
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Executes a query to retrieve objects. These objects are available through the returned
     * {@link org.wmi4j.SWbemObjectSet} collection.
     * @param queryString String that contains the text of the query. This parameter cannot be blank.
     * @param queryLanguage <strong>[Optional]</strong> String that contains the query language to be used. If specified, this value must be "WQL".
     * @param flags <strong>[Optional]</strong> Integer that determines the behavior of the query and determines whether this call returns immediately.
     *              The default value for this parameter is {@linkplain org.wmi4j.SWbemServices.ExecQueryFlag#wbemFlagReturnImmediately wbemFlagReturnImmediately}.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If no error occurs, this method returns an {@link org.wmi4j.SWbemObjectSet} object. This is an object collection that contains the result set of the query.
     * @throws WMIException
     */
    public SWbemObjectSet execQuery(String queryString, String queryLanguage, ExecQueryFlag flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        if(StringUtils.isEmpty(queryString)) {
            throw new IllegalArgumentException("QueryString is empty.");
        }
        if(!"WQL".equals(queryLanguage)) {
            throw new IllegalArgumentException("QueryLanguage must be \"WQL\".");
        }
        return callMethod(SWbemObjectSet.class, "ExecQuery",
                new JIString(queryString), JIVariant.OPTIONAL_PARAM(),
                (flags == null) ? JIVariant.OPTIONAL_PARAM() : flags.getValue(),
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Optional values for flags parameter of
     * {@linkplain #get(String, org.wmi4j.SWbemServices.GetFlag, SWbemNamedValueSet) SWbemServices.get()} method.
     */
    public enum GetFlag{
        /**
         * Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);

        private int value;
        GetFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Optional values for flags parameter of
     * {@linkplain #instancesOf(String, InstancesOfFlag, SWbemNamedValueSet) SWbemServices.instancesOf()} method.
     */
    public enum InstancesOfFlag {
        /**
         * 32(0x20): Causes a forward-only enumerator to be returned.
         * Forward-only enumerators are generally much faster and use less memory than conventional enumerators,
         * but they do not allow calls to {@link SWbemObject#clone()}.
         */
        wbemFlagForwardOnly(0x20),

        /**
         * 0(0x0): Causes WMI to retain pointers to objects of the enumeration until the client releases the enumerator.
         */
        wbemFlagBidirectional(0x0),

        /**
         * 16(0x10): Causes the call to return immediately.
         */
        wbemFlagReturnImmediately(0x10),

        /**
         * 0(0x0): Causes this call to block until the query is complete. This flag calls the method in the synchronous mode.
         */
        wbemFlagReturnWhenComplete(0x0),

        /**
         * 1(0x1): Forces the enumeration to include only immediate subclasses of the specified parent class.
         */
        wbemQueryFlagShallow(0x1),

        /**
         * 0(0x0): Default value for this parameter. This value forces the enumeration to include all classes in the hierarchy.
         */
        wbemQueryFlagDeep(0x0),

        /**
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);;

        private int value;
        InstancesOfFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Optional values for flags parameter of
     * {@linkplain #execQuery(String, String, ExecQueryFlag, SWbemNamedValueSet) SWbemServices.execQuery()} method.
     */
    public enum ExecQueryFlag {
        /**
         * 32(0x20): Causes a forward-only enumerator to be returned.
         * Forward-only enumerators are generally much faster and use less memory than conventional enumerators,
         * but they do not allow calls to {@link SWbemObject#clone()}.
         */
        wbemFlagForwardOnly(0x20),

        /**
         * 0(0x0): Causes WMI to retain pointers to objects of the enumeration until the client releases the enumerator.
         */
        wbemFlagBidirectional(0x0),

        /**
         * 16(0x10): Causes the call to return immediately.
         */
        wbemFlagReturnImmediately(0x10),

        /**
         * 0(0x0): Causes this call to block until the query is complete. This flag calls the method in the synchronous mode.
         */
        wbemFlagReturnWhenComplete(0x0),

        /**
         * 2(0x2): Used for prototyping. This flag stops the query from happening and returns an object that looks like a typical result object.
         */
        wbemQueryFlagPrototype(0x2),

        /**
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);

        private int value;
        ExecQueryFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
