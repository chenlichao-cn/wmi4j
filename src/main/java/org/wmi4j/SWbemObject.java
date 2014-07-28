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
import org.jinterop.dcom.core.JIArray;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;
import org.wmi4j.consts.Flags;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * <p>You can use the methods and properties of the SWbemObject object to represent one
 * Windows Management Instrumentation (WMI) class definition or object instance.</p>
 *
 * <p>This object supports two types of properties and methods.
 * Those defined in this section are generic properties and methods that apply to all WMI objects.
 * In addition, this object exposes the properties and methods of the underlying object as
 * dynamic automation properties and methods of SWbemObject. The names and types of these properties and methods
 * depend on the underlying WMI object.</p>
 *
 * <p>From the WMI client perspective, this object is always in-process.
 * Write operations only affect the local copy of the object, and read operations always retrieve values from the local copy.
 * Updates to WMI are performed only when entire objects are written using a call to the {@link #put(java.util.List, SWbemNamedValueSet) SWbemObject.put()}.
 * If you modify the properties or methods in an {@link org.wmi4j.SWbemObject} object, your changes are not written to WMI
 * until you call {@link #put(java.util.List, SWbemNamedValueSet) SWbemObject.put()}.</p>
 *
 * <p><strong>Note: </strong> {@link org.wmi4j.SWbemObject} cannot be created by yourself.
 * If you want to create a new, empty class use {@link org.wmi4j.SWbemServices#get(String, java.util.List, SWbemNamedValueSet) SWbemServices.get}
 * with an empty path parameter. This call returns an empty {@link org.wmi4j.SWbemObject} object that can become a class.
 * You can then supply a class name for the class property of the {@link org.wmi4j.SWbemObjectPath} object returned by the {@link SWbemObject#getPath()} call.
 * Add properties to the new class by the properties method.
 * To create an instance, call GetObject on the new class.</p>
 *
 * Created by chenlichao on 14-7-22.
 */
//TODO Implement async methods
public class SWbemObject extends AbstractSecurityScriptingObject {
    SWbemObject(IJIDispatch dispatch) {
        super(dispatch);
    }

    /**
     * Returns a collection of objects (classes or instances) that are associated with the current object.
     * These returned objects are called endpoints. This method performs the same function that the ASSOCIATORS OF WQL query performs.
     *
     * @param assocClass <strong>[Optional]</strong> String that contains an association class. If specified, this parameter indicates that
     *                   the returned endpoints must be associated with the source through the specified association class
     *                   or a class that is derived from this association class.
     * @param resultClass <strong>[Optional]</strong> String that contains a class name. If specified, this optional parameter indicates that
     *                    the returned endpoints must belong to or be derived from the class specified in this parameter.
     * @param resultRole <strong>[Optional]</strong> String that contains a property name.
     *                   If specified, this parameter indicates that the returned endpoints must play a particular
     *                   role in their association with the source object. The role is defined by the name of
     *                   a specified property (which must be a reference property) of an association.
     * @param role <strong>[Optional]</strong> String that contains a property name.
     *             If specified, this parameter indicates that the returned endpoints must participate in an association
     *             with the source object in which the source object plays a particular role. The role is defined by
     *             the name of a specified property (which must be a reference property) of an association.
     * @param classesOnly <strong>[Optional]</strong> Boolean value that indicates whether a list of class names should be returned rather than
     *                    actual instances of the classes. These are the classes to which the endpoint instances belong.
     *                    The default value for this parameter is FALSE.
     * @param schemaOnly <strong>[Optional]</strong> Boolean value that indicates whether the query applies to the schema rather than the data.
     *                   The default value for this parameter is FALSE. It can only be set to TRUE if the strObjectPath
     *                   parameter specifies the object path of a class. When set to TRUE, the set of returned
     *                   endpoints represent classes that are suitably associated with the source class in schema.
     * @param requiredAssocQualifier <strong>[Optional]</strong> String that contains a qualifier name.
     *                               If specified, this parameter indicates that the returned endpoints must be
     *                               associated with the source object through an association class that includes the
     *                               specified qualifier.
     * @param requiredQualifier <strong>[Optional]</strong> String that contains a qualifier name.
     *                          If specified, this parameter indicates that the returned endpoints must include
     *                          the specified qualifier.
     * @param flags <strong>[Optional]</strong> Integer that specifies additional flags to the operation.
     *              The default value for this parameter is {@linkplain org.wmi4j.consts.Flags.AssociatorsFlag#wbemFlagReturnImmediately wbemFlagReturnImmediately},
     *              which calls the method in the semisynchronous mode. This parameter can accept the following values.
     * @param objwbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If successful a {@link org.wmi4j.SWbemObjectSet} will be returned.
     * @throws WMIException
     */
    public SWbemObjectSet associators(String assocClass, String resultClass,
                                      String resultRole, String role, Boolean classesOnly, Boolean schemaOnly, String requiredAssocQualifier,
                                      String requiredQualifier, List<Flags.AssociatorsFlag> flags, SWbemNamedValueSet objwbemNamedValueSet) throws WMIException {
        Integer iFlags = null;
        if(flags != null) {
            for(Flags.AssociatorsFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObjectSet.class, "Associators_",
                (isEmpty(assocClass)) ? JIVariant.OPTIONAL_PARAM() : new JIString(assocClass),
                (isEmpty(resultClass)) ? JIVariant.OPTIONAL_PARAM() : new JIString(resultClass),
                (isEmpty(resultRole)) ? JIVariant.OPTIONAL_PARAM() : new JIString(resultRole),
                (isEmpty(role)) ? JIVariant.OPTIONAL_PARAM() : new JIString(role),
                (classesOnly == null) ? JIVariant.OPTIONAL_PARAM() : classesOnly,
                (schemaOnly == null) ? JIVariant.OPTIONAL_PARAM() : schemaOnly,
                (isEmpty(requiredAssocQualifier)) ? JIVariant.OPTIONAL_PARAM() : new JIString(requiredAssocQualifier),
                (isEmpty(requiredQualifier)) ? JIVariant.OPTIONAL_PARAM() : new JIString(requiredQualifier),
                (iFlags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objwbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objwbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for method {@link #associators(String, String, String, String, Boolean, Boolean, String, String, java.util.List, SWbemNamedValueSet)}.
     */
    public SWbemObjectSet associators() throws WMIException {
        return associators(null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * Returns a new object that is a clone of the current object.
     * @return A new object that is a clone of the current object.
     */
    public SWbemObject wmiClone() throws WMIException{
        return callMethod(SWbemObject.class, "Clone_");
    }

    /**
     * Compares two SWbemObject objects.
     * This comparison is subject to certain constraints based on the values specified in the flags parameter.
     * @param wbemObject This is the object with which the first object is compared. The object must be a valid SWbemObject instance.
     * @param flags <strong>[Optional] </strong>Specifies the object characteristics to consider when comparing an object
     *              with other objects. You can use {@link org.wmi4j.consts.Flags.CompareToFlag#wbemComparisonFlagIncludeAll wbemComparisonFlagIncludeAll}
     *              to consider all features (this is the default), or any combination of the following values.
     * @return This method returns the Boolean value of TRUE if the objects match. It returns FALSE if the objects do not match.
     * @throws WMIException
     */
    public boolean compareTo(SWbemObject wbemObject, List<Flags.CompareToFlag> flags) throws WMIException {
        if(wbemObject == null) {
            return false;
        }
        Integer iFlags = null;
        if(flags != null) {
            for(Flags.CompareToFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return (Boolean)callMethod(Boolean.class,  "CompareTo_", wbemObject.getDispatch(),
                (iFlags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags);
    }

    /**
     * Use default parameters for {@link #compareTo(SWbemObject, java.util.List)}
     */
    public boolean compareTo(SWbemObject wbemObject) throws WMIException {
        return compareTo(wbemObject, null);
    }

    /**
     * Deletes either the current class or the current instance.
     * If a dynamic provider supplies the class or instance,
     * it is sometimes not possible to delete this object unless the provider supports class or instance deletion.
     * @param flags Reserved and must be 0 (zero) if specified.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @throws WMIException
     */
    public void delete(Integer flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        callMethod(null, "Delete_", 0,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for {@link #delete(Integer, SWbemNamedValueSet)}
     */
    public void delete() throws WMIException {
        delete(null, null);
    }

    /**
     * Executes a method exported by a method provider.
     * This method pauses while the method that is forwarded to the appropriate provider executes.
     * The information and status are then returned. The provider rather than WMI implements the method
     *
     * @param methodName Name of the method for the object.
     * @param inParameters <strong>[Optional]</strong> An {@link org.wmi4j.SWbemObject} object that contains the input parameters for the method being executed.
     *                     By default, this parameter is undefined.
     * @param flags <strong>[Optional]</strong> Reserved. This value must be zero.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If the method is successful, an {@link org.wmi4j.SWbemObject} object is returned.
     * The returned object contains the out parameters and return value for the method that is being executed.
     * @throws WMIException
     */
    public SWbemObject execMethod(String methodName, SWbemObject inParameters, Integer flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        if(isEmpty(methodName)) { throw new IllegalArgumentException("Method name is empty."); }
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        return callMethod(SWbemObject.class, "ExecMethod_", new JIString(methodName),
                (inParameters == null) ? JIVariant.OPTIONAL_PARAM() : inParameters.getDispatch(),
                JIVariant.OPTIONAL_PARAM(),
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for {@link #execMethod(String, SWbemObject, Integer, SWbemNamedValueSet)}
     */
    public SWbemObject execMethod(String methodName, SWbemObject inParameters) throws WMIException {
        return execMethod(methodName, inParameters, null, null);
    }

    /**
     * Returns a textual rendering of the object.
     * This object can be used to display an object's contents.
     * Currently, only the MOF syntax is supported as an output format.
     * Notice that the MOF text returned does not contain all the information about the object;
     * the MOF text contains only enough information for the MOF compiler to be able to re-create the original object.
     * For instance, there is no information about the propagated qualifiers or the parent class properties.
     *
     * @param flags <strong>[Optional]</strong> Reserved. This value must be zero.
     * @return If successful, this method returns a string that contains the output text.
     * @throws WMIException
     */
    public String getObjectText(Integer flags) throws WMIException {
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        return callMethod(String.class, "GetObjectText_", JIVariant.OPTIONAL_PARAM());
    }

    /**
     * Use default parameters for {@link #getObjectText(Integer)}
     */
    public String getObjectText() throws WMIException {
        return getObjectText(null);
    }

    /**
     * Creates an enumerator that returns the instances of the current class object.
     * This method implements a simple query. More complex queries may require the use of
     * {@link org.wmi4j.SWbemServices#execQuery(String, String, java.util.List, SWbemNamedValueSet) SWbemServices.execQuery()}.
     *
     * @param flags <strong>[Optional]</strong> Integer that determines the behavior of the call.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return The instances of the current class object.
     * @throws WMIException
     */
    public SWbemObjectSet instances(List<Flags.InstancesFlag> flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        Integer iFlags = null;
        if(flags != null) {
            for(Flags.InstancesFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObjectSet.class, "Instances_",
                (flags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for {@link #instances(java.util.List, SWbemNamedValueSet)}
     */
    public SWbemObjectSet instances() throws WMIException {
        return instances(null, null);
    }

    /**
     * Creates or updates an instance or a class object to Windows Management Instrumentation (WMI).
     * You can use this method after you modify any properties or methods in an {@link org.wmi4j.SWbemObject},
     * and your changes are written to WMI.
     *
     * @param flags <strong>[Optional]</strong> Determines if the call creates or updates the class or instance and if the call returns immediately.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return {@link org.wmi4j.SWbemObjectPath} object contains the object path of the instance or class that has been successfully committed to WMI.
     * @throws WMIException
     */
    public SWbemObjectPath put(List<Flags.PutFlag> flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        Integer iFlags = null;
        if(flags != null) {
            for(Flags.PutFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObjectPath.class, "Put_",
                (flags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for {@link #put(java.util.List, SWbemNamedValueSet)}
     */
    public SWbemObjectPath put() throws WMIException {
        return put(null, null);
    }

    /**
     * Returns a collection of all association classes or instances that refer to the current object.
     * This method performs the same function as the REFERENCES OF WQL query.
     *
     * @param resultClass <strong>[Optional]</strong> String that contains a class name. If specified, this optional parameter indicates that
     *                    the returned endpoints must belong to or be derived from the class specified in this parameter.
     * @param role <strong>[Optional]</strong> String that contains a property name.
     *             If specified, this parameter indicates that the returned endpoints must participate in an association
     *             with the source object in which the source object plays a particular role. The role is defined by
     *             the name of a specified property (which must be a reference property) of an association.
     * @param classesOnly <strong>[Optional]</strong> Boolean value that indicates whether a list of class names should be returned rather than
     *                    actual instances of the classes. These are the classes to which the endpoint instances belong.
     *                    The default value for this parameter is FALSE.
     * @param schemaOnly <strong>[Optional]</strong> Boolean value that indicates whether the query applies to the schema rather than the data.
     *                   The default value for this parameter is FALSE. It can only be set to TRUE if the strObjectPath
     *                   parameter specifies the object path of a class. When set to TRUE, the set of returned
     *                   endpoints represent classes that are suitably associated with the source class in schema.
     * @param requiredQualifier <strong>[Optional]</strong> String that contains a qualifier name.
     *                          If specified, this parameter indicates that the returned endpoints must include
     *                          the specified qualifier.
     * @param flags <strong>[Optional]</strong> Integer that specifies additional flags to the operation.
     *              The default value for this parameter is {@linkplain org.wmi4j.consts.Flags.ReferenceFlag#wbemFlagReturnImmediately wbemFlagReturnImmediately},
     *              which calls the method in the semisynchronous mode. This parameter can accept the following values.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If successful a {@link org.wmi4j.SWbemObjectSet} will be returned.
     * @throws WMIException
     */
    public SWbemObjectSet references(String resultClass, String role,
                                     Boolean classesOnly, Boolean schemaOnly, String requiredQualifier,
                                     List<Flags.ReferenceFlag> flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        Integer iFlags = null;
        if(flags != null) {
            for(Flags.ReferenceFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObjectSet.class, "ReferencesTo",
                (isEmpty(resultClass)) ? JIVariant.OPTIONAL_PARAM() : new JIString(resultClass),
                (isEmpty(role)) ? JIVariant.OPTIONAL_PARAM() : new JIString(role),
                (classesOnly == null) ? JIVariant.OPTIONAL_PARAM() : classesOnly,
                (schemaOnly == null) ? JIVariant.OPTIONAL_PARAM() : schemaOnly,
                (isEmpty(requiredQualifier)) ? JIVariant.OPTIONAL_PARAM() : new JIString(requiredQualifier),
                (iFlags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for {@link #references(String, String, Boolean, Boolean, String, java.util.List, SWbemNamedValueSet)}
     */
    public SWbemObjectSet references() throws WMIException {
        return references(null, null, null, null, null, null, null);
    }

    /**
     * Create a derived class object from the current object.
     * The object must be a class definition that becomes the parent class of the spawned object.
     *
     * @param flags <strong>[Optional]</strong> Reserved. This value must be zero.
     * @return  {@link org.wmi4j.SWbemObject} object contains the new class definition object. No object returns when there is an error.
     * @throws WMIException
     */
    public SWbemObject spawnDerivedClass(Integer flags) throws WMIException {
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        return callMethod(SWbemObject.class, "SpawnDerivedClass_", JIVariant.OPTIONAL_PARAM());
    }

    /**
     * Use default parameters for {@link #spawnDerivedClass(Integer)}
     */
    public SWbemObject spawnDerivedClass() throws WMIException {
        return spawnDerivedClass(null);
    }

    /**
     * Create a new instance of a class.
     * The current object must be a class definition obtained from WMI via a method such as {@link org.wmi4j.SWbemServices#get(String, java.util.List, SWbemNamedValueSet) SWbemServices.get()}
     * or {@link org.wmi4j.SWbemServices#execQuery(String, String, java.util.List, SWbemNamedValueSet) SWbemServices.execQuery()}.
     * Then, use this class definition to create new instances.
     * Create each new instance locally within the process, and then call {@link #put(java.util.List, SWbemNamedValueSet) SWbemObject.put()}
     * to actually create the instance within WMI.
     * <p><strong>Note: </strong>Spawning an instance from an instance is supported, but the returned instance is empty.</p>
     *
     * @param flags <strong>[Optional]</strong> Reserved. This value must be zero.
     * @return Returns an {@link org.wmi4j.SWbemObject} object that contains a new instance of the class.
     * @throws WMIException
     */
    public SWbemObject spawnInstance(Integer flags) throws WMIException {
        if(flags != null && flags != 0) {
            throw new IllegalArgumentException("Flags must be zero.");
        }
        return callMethod(SWbemObject.class, "SpawnInstance_", JIVariant.OPTIONAL_PARAM());
    }

    /**
     * Use default parameters for {@link #spawnInstance(Integer)}
     */
    public SWbemObject spawnInstance() throws WMIException {
        return spawnDerivedClass(null);
    }

    /**
     * Returns an SWbemObjectSet object.
     * This object is a collection of subclasses of the current object, which must be a class.
     * Items in the returned collection can be obtained using standard collection methods.
     *
     * @param flags <strong>[Optional]</strong> Determines how detailed the call enumerates.
     *              The default values for this parameter are {@link org.wmi4j.consts.Flags.SubclassesFlag#wbemFlagReturnImmediately wbemFlagReturnImmediately}
     *              and {@link org.wmi4j.consts.Flags.SubclassesFlag#wbemQueryFlagDeep wbemQueryFlagDeep}.
     * @param objWbemNamedValueSet <strong>[Optional]</strong> Typically, this is undefined.
     *                             Otherwise, this is an {@link org.wmi4j.SWbemNamedValueSet} object whose elements represent the context
     *                             information that can be used by the provider that is servicing the request.
     *                             A provider that supports or requires such information must document the recognized
     *                             value names, data type of the value, allowed values, and semantics.
     * @return If successful a {@link org.wmi4j.SWbemObjectSet} contains subclasses of the current object will be returned.
     * @throws WMIException
     */
    public SWbemObjectSet subclasses(List<Flags.SubclassesFlag> flags, SWbemNamedValueSet objWbemNamedValueSet) throws WMIException {
        Integer iFlags = null;
        if(flags != null) {
            for(Flags.SubclassesFlag flag : flags) {
                if(iFlags == null) {
                    iFlags = flag.getValue();
                } else {
                    iFlags += flag.getValue();
                }
            }
        }
        return callMethod(SWbemObjectSet.class, "Subclasses_",
                (iFlags == null) ? JIVariant.OPTIONAL_PARAM() : iFlags,
                (objWbemNamedValueSet == null) ? JIVariant.OPTIONAL_PARAM() : objWbemNamedValueSet.getDispatch());
    }

    /**
     * Use default parameters for {@link #subclasses(java.util.List, SWbemNamedValueSet)}
     */
    public SWbemObjectSet subclasses() throws WMIException {
        return subclasses(null, null);
    }

    /**
     * An array of strings that describe the class derivation hierarchy for the instance being referenced.
     * The first element in the array defines the parent class and the last element defines the dynasty class.
     * <p>This property is read-only.</p>
     *
     * @return An array of strings that describe the class derivation hierarchy for the instance being referenced.
     * @throws WMIException
     */
    public List<String> getDerivation() throws WMIException {
        try {
            JIVariant derivation_ = dispatch.get("Derivation_");
            List<String> result = new ArrayList<String>();
            JIArray jiArray = derivation_.getObjectAsArray();
            JIVariant[] variants = (JIVariant[])jiArray.getArrayInstance();
            for(JIVariant v : variants) {
                result.add(v.getObjectAsString2());
            }
            return result;
        } catch (JIException e) {
            throw new WMIException(e);
        }
    }

    /**
     * Returns an SWbemMethodSet object that is a collection of the methods for the current class or instance.
     * This property is read-only.
     * @return A collection of the methods for the current class or instance.
     * @throws WMIException
     */
    public SWbemMethodSet getMethods() throws WMIException {
        return getProperty(SWbemMethodSet.class, "Methods_");
    }

    /**
     * Get an {@link org.wmi4j.SWbemObject} object whose properties define the input parameters for the specified method.
     * @param methodName Name of the method for the in parameters.
     * @return An {@link org.wmi4j.SWbemObject} object whose properties define the input parameters for the specified method.
     * @throws WMIException
     */
    public SWbemObject getMethodInParameters(String methodName) throws WMIException {
        return getMethods().item(methodName).getInParameters();
    }

    /**
     * Returns an {@link org.wmi4j.SWbemObjectPath} object that represents the object path of the current class or instance.
     * This property can be passed as a parameter to methods that require an object path.
     *
     * <h3>Remark</h3>
     *
     * <p>Only the class property of the returned {@link org.wmi4j.SWbemObjectPath} instance can be modified.
     * If you try to modify any other property, or try to call the methods {@link SWbemObjectPath#setAsClass()} or {@link SWbemObjectPath#setAsSingleton()}
     * you will get a {@link org.wmi4j.WMIException}.</p>
     * <p>Because of this, you cannot modify the {@link org.wmi4j.SWbemNamedValueSet} object
     * that is the value of the keys property of the returned {@link org.wmi4j.SWbemObjectPath} instance.
     * If you try to call the Add, Remove, or DeleteAll methods on this value, you will get a {@link org.wmi4j.WMIException}.
     * Furthermore, you cannot modify any {@link org.wmi4j.SWbemNamedValue} obtained from this collection.
     * Attempts to modify the value property return the same error.</p>
     * <p>However, if you call {@link SWbemObject#wmiClone()} to create a copy,
     * the SWbemObjectPath.path property of the copy is fully modifiable.</p>
     *
     * @return {@link org.wmi4j.SWbemObjectPath} object that represents the object path of the current class or instance.
     * @throws WMIException
     */
    public SWbemObjectPath getPath() throws WMIException {
        return getProperty(SWbemObjectPath.class, "Path_");
    }

    /**
     * Returns an {@link org.wmi4j.SWbemPropertySet} object that is a collection of the properties for the current class or instance.
     * This property is read-only.
     *
     * @return An {@link org.wmi4j.SWbemPropertySet} object that is a collection of the properties for the current class or instance.
     * @throws WMIException
     */
    public SWbemPropertySet getProperties() throws WMIException {
        return getProperty(SWbemPropertySet.class, "Properties_");
    }

    /**
     * Get the variant value of the specified WMI property.
     * @param propertyName Name of the property.
     * @return The variant value of the specified WMI property.
     * @throws WMIException
     */
    public WMIVariant getPropertyByName(String propertyName) throws WMIException {
        return getProperties().item(propertyName).getValue();
    }

    /**
     * Returns an {@link org.wmi4j.SWbemQualifierSet} object that is a collection of the qualifiers for the current class or instance.
     * This property is read-only.
     * @return An {@link org.wmi4j.SWbemQualifierSet} object that is a collection of the qualifiers for the current class or instance.
     * @throws WMIException
     */
    public SWbemQualifierSet getQualifiers() throws WMIException {
        return getProperty(SWbemQualifierSet.class, "Qualifiers_");
    }
}
