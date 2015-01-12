/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.phoenix.schema;

import com.google.common.base.Preconditions;

import java.io.Serializable;

public class PTableKey implements Serializable {
    private final PName tenantId;
    private final String name;
    
    public PTableKey(PName tenantId, String name) {
        Preconditions.checkNotNull(name);
        this.tenantId = tenantId;
        this.name = name;
    }

    public PName getTenantId() {
        return tenantId;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name + (tenantId == null ? "" : " for " + tenantId.getString());
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tenantId == null) ? 0 : tenantId.hashCode());
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PTableKey other = (PTableKey)obj;
        if (!name.equals(other.name)) return false;
        if (tenantId == null) {
            if (other.tenantId != null) return false;
        } else if (!tenantId.equals(other.tenantId)) return false;
        return true;
    }

}
