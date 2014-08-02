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

package org.wmi4j.consts;


/**
 * Optional flags values.
 * Created by chenlichao on 14-7-26.
 */
public class Flags {

    /**
     * securityFlag parameter of {@linkplain org.wmi4j.SWbemLocator#connectServer(String, String, org.wmi4j.consts.Flags.SecurityFlag, org.wmi4j.SWbemNamedValueSet) SWbemLocator.connectServer()} optional values;
     */
    public enum SecurityFlag {
        /**
         * A value of 0 for this parameter causes the call to {@linkplain org.wmi4j.SWbemLocator#connectServer(String, String, org.wmi4j.consts.Flags.SecurityFlag, org.wmi4j.SWbemNamedValueSet) connectServer()}
         * to return only after the connection to the server is established.
         * This could cause your program to stop responding indefinitely if the connection cannot be established.
         */
        wbemConnectFlagWhenComplete(0x0),
        /**
         * The {@linkplain org.wmi4j.SWbemLocator#connectServer(String, String, org.wmi4j.consts.Flags.SecurityFlag, org.wmi4j.SWbemNamedValueSet) connectServer()} call is guaranteed
         * to return in 2 minutes or less. Use this flag to prevent your program from ceasing to respond indefinitely
         * if the connection cannot be established.
         */
        wbemConnectFlagUseMaxWait(0x80);

        private final int value;
        SecurityFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    /**
     * Optional values of flags parameter for
     * {@linkplain org.wmi4j.SWbemServices#get(String, org.wmi4j.SWbemNamedValueSet, Flags.GetFlag...) SWbemServices.get()} method.
     */
    public static enum GetFlag{
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
     * Optional values of flags parameter for
     * {@linkplain org.wmi4j.SWbemServices#instancesOf(String, org.wmi4j.SWbemNamedValueSet, org.wmi4j.consts.Flags.InstancesFlag...) SWbemServices.instancesOf()}
     * and {@link org.wmi4j.SWbemObject#instances(org.wmi4j.SWbemNamedValueSet, org.wmi4j.consts.Flags.InstancesFlag...) SWbemObject.instances()}
     */
    public static enum InstancesFlag {
        /**
         * 32(0x20): Causes a forward-only enumerator to be returned.
         * Forward-only enumerators are generally much faster and use less memory than conventional enumerators,
         * but they do not allow calls to {@link org.wmi4j.SWbemObject#wmiClone()}.
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
        InstancesFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Optional values of flags parameter for
     * {@linkplain org.wmi4j.SWbemServices#execQuery(String, String, org.wmi4j.SWbemNamedValueSet, ExecQueryFlag...) SWbemServices.execQuery()}
     */
    public static enum ExecQueryFlag {
        /**
         * 32(0x20): Causes a forward-only enumerator to be returned.
         * Forward-only enumerators are generally much faster and use less memory than conventional enumerators,
         * but they do not allow calls to {@link org.wmi4j.SWbemObject#wmiClone()}.
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

    /**
     *
     * Optional values of flags parameter for
     * {@linkplain org.wmi4j.SWbemServices#associatorsOf(String, String, String, String, String, Boolean, Boolean, String, String, org.wmi4j.SWbemNamedValueSet, org.wmi4j.consts.Flags.AssociatorsFlag...) SWbemServices.associatorsOf()}
     *  and {@link org.wmi4j.SWbemObject#associators(String, String, String, String, Boolean, Boolean, String, String, org.wmi4j.SWbemNamedValueSet, org.wmi4j.consts.Flags.AssociatorsFlag...) SWbemObject.associators()}.
     *
     */
    public static enum AssociatorsFlag {
        /**
         * 32(0x20): Causes a forward-only enumerator to be returned.
         * Forward-only enumerators are generally much faster and use less memory than conventional enumerators,
         * but they do not allow calls to {@link org.wmi4j.SWbemObject#wmiClone()}.
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
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);
        private int value;
        AssociatorsFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Optional values for parameter flags of
     * {@link org.wmi4j.SWbemServices#referencesTo(String, String, String, Boolean, Boolean, String, org.wmi4j.SWbemNamedValueSet, ReferenceFlag...) SWbemServices.referencesOf()}
     * and {@link org.wmi4j.SWbemObject#references(String, String, Boolean, Boolean, String, org.wmi4j.SWbemNamedValueSet, ReferenceFlag...) SWbemObject.references()}
     */
    public static enum ReferenceFlag {
        /**
         * 32(0x20): Causes a forward-only enumerator to be returned.
         * Forward-only enumerators are generally much faster and use less memory than conventional enumerators,
         * but they do not allow calls to {@link org.wmi4j.SWbemObject#wmiClone()}.
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
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);
        private int value;
        ReferenceFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     *
     * Optional values of flags parameter for
     * {@linkplain org.wmi4j.SWbemServices#subclassesOf(String, org.wmi4j.SWbemNamedValueSet, SubclassesFlag...) SWbemServices.subclassesOf()}
     * and {@link org.wmi4j.SWbemObject#subclasses(org.wmi4j.SWbemNamedValueSet, SubclassesFlag...) SWbemObject.subclasses()}
     */
    public static enum SubclassesFlag {
        /**
         * 1(0x1): Forces the enumeration to include only immediate subclasses of the specified parent class.
         */
        wbemQueryFlagShallow(0x1),

        /**
         * 0(0x0): Default value for this parameter. This value forces the enumeration to include all classes in the hierarchy.
         */
        wbemQueryFlagDeep(0x0),
        /**
         * 16(0x10): Causes the call to return immediately.
         */
        wbemFlagReturnImmediately(0x10),

        /**
         * 0(0x0): Causes this call to block until the query is complete. This flag calls the method in the synchronous mode.
         */
        wbemFlagReturnWhenComplete(0x0),

        /**
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);

        final private int value;
        SubclassesFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     *
     * Optional values of flags parameter for
     * {@link org.wmi4j.SWbemObject#compareTo(org.wmi4j.SWbemObject, CompareToFlag...) SWbemObject.compareTo()}
     *
     */
    public static enum CompareToFlag {
        /**
         * 0(0x0): Compares all properties, qualifiers, and flavors.
         */
        wbemComparisonFlagIncludeAll(0x0),

        /**
         * 2(0x2): Causes the source of the objects, namely the server and the namespace they came from,
         * to be ignored in comparison to other objects.
         */
        wbemComparisonFlagIgnoreObjectSource(0x2),
        /**
         * 1(0x1): Causes all qualifiers (including Key and Dynamic) to be ignored in comparison.
         */
        wbemComparisonFlagIgnoreQualifiers(0x1),

        /**
         * 4(0x4): Causes default values of properties to be ignored. This flag is only meaningful when comparing classes.
         */
        wbemComparisonFlagIgnoreDefaultValues(0x4),

        /**
         * 32(0x20): Causes qualifier flavors to be ignored. This flag takes qualifier values into account,
         * but ignores flavor distinctions such as propagation rules and override restrictions.
         */
        wbemComparisonFlagIgnoreFlavor(0x20),

        /**
         * 16(0x10): Compares string values in a case-insensitive manner. This applies both to strings and to qualifier values.
         * Property and qualifier names are always compared in a case-insensitive manner whether this flag is specified or not.
         */
        wbemComparisonFlagIgnoreCase(0x10),

        /**
         * 8(0x8): Instructs the system to assume that the objects being compared are instances of the same class.
         * Consequently, this flag compares instance-related information only. Use this flag to optimize performance.
         * If the objects are not of the same class, the results are undefined.
         */
        wbemComparisonFlagIgnoreClass(0x8);

        final private int value;
        CompareToFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Optional values of flags parameter of {@link org.wmi4j.SWbemObject#put(org.wmi4j.SWbemNamedValueSet, PutFlag...) SWbemObject.put()}
     */
    public static enum PutFlag {
        /**
         * 0(0x0): Allows a class to be updated if there are no derived classes and there are no instances for that class.
         * It also allows updates in all cases if the change is just to unimportant qualifiers (for example, the Description qualifier).
         * This is the default behavior for this call and is used for compatibility with previous versions of WMI.
         * If the class has instances the update fails.
         */
        wbemChangeFlagUpdateCompatible(0x0),

        /**
         * 32(0x20): Allows updates of classes even if there are child classes if the change does not cause any conflicts with child classes.
         * You can use this flag when adding a new property to a base class that was not previously mentioned in any of the child classes.
         * If the class has instances the update fails. If the class has instances the update fails.
         */
        wbemChangeFlagUpdateSafeMode(0x20),

        /**
         * 64(0x40): This flag forces updates of classes when conflicting child classes exist.
         * For example, this flag will force an update if a class qualifier was defined in a child class,
         * and the base class tries to add the same qualifier and in conflict with the existing one.
         * In force mode this conflict would be resolved by deleting the conflicting qualifier in the child class.
         * If the class has instances the update will fail.
         * <p>Using force mode to update a static class results in the deletion of all instances of that class.
         * A forced update on a provider class does not delete instances of the class.</p>
         */
        WbemChangeFlagUpdateForceMode(0x40),

        /**
         * 0(0x0): Causes the class or instance to be created if it does not exist or overwritten if it exists already.
         */
        wbemChangeFlagCreateOrUpdate(0x0),

        /**
         * 2(0x2): Used for creation only. The call fails if the class or instance already exists.
         */
        wbemChangeFlagCreateOnly(0x2),

        /**
         * 1(0x1): Causes this call to update. The class or instance must exist for the call to be successful.
         */
        wbemChangeFlagUpdateOnly(0x1),

        /**
         * 16(0x10): Causes the call to return immediately.
         */
        wbemFlagReturnImmediately(0x10),

        /**
         * 0(0x0): Causes this call to block until the query is complete. This flag calls the method in the synchronous mode.
         */
        wbemFlagReturnWhenComplete(0x0),

        /**
         * 131072(0x20000): Causes WMI to return class amendment data with the base class definition.
         */
        wbemFlagUseAmendedQualifiers(0x20000);;

        final private int value;
        PutFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
