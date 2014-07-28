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

package org.wmi4j.consts;

/**
 * The WbemPrivilegeEnum constants define privileges.
 * These constants are used with {@link org.wmi4j.SWbemSecurity} to grant the privileges required for some operations.
 *
 * Created by chenlichao on 14-7-28.
 */
public enum WbemPrivilegeEnum {
    /** 1(0x1), SeCreateTokenPrivilege:  Required to create a primary token. */
    CreateToken(0x1, "SeCreateTokenPrivilege"),
    /** 2(0x2), SePrimaryTokenPrivilege:  Required to assign the primary token of a process. */
    PrimaryToken(0x2, "SePrimaryTokenPrivilege"),
    /** 3(0x3), SeLockMemoryPrivilege:  Required to lock physical pages in memory. */
    LockMemory(0x3, "SeLockMemoryPrivilege"),
    /** 4(0x4), SeIncreaseQuotaPrivilege:  Required to increase the quota assigned to a process. */
    IncreaseQuota(0x4, "SeIncreaseQuotaPrivilege"),
    /** 5(0x5), SeMachineAccountPrivilege:  Required to create a machine account. */
    MachineAccount(0x5, "SeMachineAccountPrivilege"),
    /** 6(0x6), SeTcbPrivilege:  Identifies its holder as part of the trusted computer base. Some trusted, protected subsystems are granted this privilege. */
    Tcb(0x6, "SeTcbPrivilege"),
    /** 7(0x7), SeSecurityPrivilege: Required to perform a number of security-related functions, such as controlling and viewing audit messages.
     * This privilege identifies its holder as a security operator. */
    Security(0x7, "SeSecurityPrivilege"),
    /** 8(0x8), SeTakeOwnershipPrivilege: Required to take ownership of an object without being granted discretionary access.
     * This privilege allows the owner value to be set only to those values
     * that the holder may legitimately assign as the owner of an object. */
    TakeOwnership(0x8, "SeTakeOwnershipPrivilege"),
    /** 9(0x9), SeLoadDriverPrivilege:  Required to load or unload a device driver. */
    LoadDriver(0x9, "SeLoadDriverPrivilege"),
    /** 10(0xA), SeSystemProfilePrivilege:  Required to gather profiling information for the entire system. */
    SystemProfile(0xA, "SeSystemProfilePrivilege"),
    /** 11(0xB), SeSystemtimePrivilege:  Required to modify the system time. */
    Systemtime(0xB, "SeSystemtimePrivilege"),
    /** 12(0xC), SeProfileSingleProcessPrivilege:  Required to gather profiling information for a single process. */
    ProfileSingleProcess(0xC, "SeProfileSingleProcessPrivilege"),
    /** 13(0xD), SeIncreaseBasePriorityPrivilege:  Required to increase the base priority of a process. */
    IncreaseBasePriority(0xD, "SeIncreaseBasePriorityPrivilege"),
    /** 14(0xE), SeCreatePagefilePrivilege:  Required to create a paging file. */
    CreatePagefile(0xE, "SeCreatePagefilePrivilege"),
    /** 15(0xF), SeCreatePermanentPrivilege:  Required to create a permanent object. */
    CreatePermanent(0xF, "SeCreatePermanentPrivilege"),
    /** 16(0x10), SeBackupPrivilege:  Required to perform backup operations. */
    Backup(0x10, "SeBackupPrivilege"),
    /** 17(0x11), SeRestorePrivilege: Required to perform restore operations.
     * This privilege enables you to set any valid user or group security identifier (SID) as the owner of an object. */
    Restore(0x11, "SeRestorePrivilege"),
    /** 18(0x12), SeShutdownPrivilege:  Required to shut down a local system. */
    Shutdown(0x12, "SeShutdownPrivilege"),
    /** 19(0x13), SeDebugPrivilege:  Required to debug a process. */
    Debug(0x13, "SeDebugPrivilege"),
    /** 20(0x14), SeAuditPrivilege:  Required to generate audit-log entries. */
    Audit(0x14, "SeAuditPrivilege"),
    /** 21(0x15), SeSystemEnvironmentPrivilege:  Required to modify the nonvolatile RAM of systems that use this type of memory to store configuration information. */
    SystemEnvironment(0x15, "SeSystemEnvironmentPrivilege"),
    /** 22(0x16), SeChangeNotifyPrivilege: Required to receive notifications of changes to files or directories.
     * This privilege also causes the system to skip all traversal access checks. It is enabled by default for all users. */
    ChangeNotify(0x16, "SeChangeNotifyPrivilege"),
    /** 23(0x17), SeRemoteShutdownPrivilege:  Required to shut down a system using a network request. */
    RemoteShutdown(0x17, "SeRemoteShutdownPrivilege"),
    /** 24(0x18), SeUndockPrivilege:  Required to remove a computer from a docking station. */
    Undock(0x18, "SeUndockPrivilege"),
    /** 25(0x19), SeSyncAgentPrivilege:  Required to synchronize directory service data. */
    SyncAgent(0x19, "SeSyncAgentPrivilege"),
    /** 26(0x1A), SeEnableDelegationPrivilege:  Required to enable computer and user accounts to be trusted for delegation. */
    EnableDelegation(0x1A, "SeEnableDelegationPrivilege"),
    /** 27(0x1B), SeManageVolumePrivilege:  Required to perform volume maintenance tasks. */
    ManageVolume(0x1B, "SeManageVolumePrivilege");

    final private int value;
    final private String strValue;
    WbemPrivilegeEnum(int value, String strValue) {
        this.value = value;
        this.strValue = strValue;
    }

    public int getValue() {
        return value;
    }

    public String getStrValue() {
        return strValue;
    }

    public static WbemPrivilegeEnum parse(int value) {
        for(WbemPrivilegeEnum e : WbemPrivilegeEnum.values()) {
            if(e.getValue() == value) {
                return e;
            }
        }
        throw new IllegalArgumentException("WbemCimTypeEnum has no constant with the specified value");
    }

    public static WbemPrivilegeEnum parse(String strValue) {
        for(WbemPrivilegeEnum e : WbemPrivilegeEnum.values()) {
            if(e.getStrValue().equals(strValue)) {
                return e;
            }
        }
        throw new IllegalArgumentException("WbemCimTypeEnum has no constant with the specified value");
    }
}
