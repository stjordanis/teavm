/*
 *  Copyright 2014 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.callgraph;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.teavm.model.FieldReference;
import org.teavm.model.InstructionLocation;
import org.teavm.model.MethodReference;

/**
 *
 * @author Alexey Andreev
 */
public class DefaultCallGraphNode implements CallGraphNode {
    private DefaultCallGraph graph;
    private MethodReference method;
    private Set<DefaultCallSite> callSites;
    private transient Set<DefaultCallSite> safeCallSites;
    private List<DefaultCallSite> callerCallSites;
    private transient List<DefaultCallSite> safeCallersCallSites;
    private Set<DefaultFieldAccessSite> fieldAccessSites;
    private transient Set<DefaultFieldAccessSite> safeFieldAccessSites;
    private Set<DefaultClassAccessSite> classAccessSites;
    private transient Set<DefaultClassAccessSite> safeClassAccessSites;

    DefaultCallGraphNode(DefaultCallGraph graph) {
        this.graph = graph;
    }

    @Override
    public DefaultCallGraph getGraph() {
        return graph;
    }

    @Override
    public MethodReference getMethod() {
        return method;
    }

    @Override
    public Collection<DefaultCallSite> getCallSites() {
        if (safeCallSites == null) {
            safeCallSites = Collections.unmodifiableSet(callSites);
        }
        return safeCallSites;
    }

    @Override
    public Collection<DefaultCallSite> getCallerCallSites() {
        if (safeCallersCallSites == null) {
            safeCallersCallSites = Collections.unmodifiableList(callerCallSites);
        }
        return safeCallersCallSites;
    }

    public void addCallSite(MethodReference method, InstructionLocation location) {
        DefaultCallGraphNode callee = graph.getNode(method);
        DefaultCallSite callSite = new DefaultCallSite(location, callee, this);
        if (callSites.add(callSite)) {
            callee.callerCallSites.add(callSite);
        }
    }

    public void addCallSite(MethodReference method) {
        addCallSite(method, null);
    }

    @Override
    public Collection<DefaultFieldAccessSite> getFieldAccessSites() {
        if (safeFieldAccessSites == null) {
            safeFieldAccessSites = Collections.unmodifiableSet(fieldAccessSites);
        }
        return safeFieldAccessSites;
    }

    public boolean addFieldAccess(FieldReference field, InstructionLocation location) {
        DefaultFieldAccessSite site = new DefaultFieldAccessSite(location, this, field);
        if (fieldAccessSites.add(site)) {
            graph.addFieldAccess(site);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection<? extends ClassAccessSite> getClassAccessSites() {
        if (safeClassAccessSites == null) {
            safeClassAccessSites = Collections.unmodifiableSet(classAccessSites);
        }
        return safeClassAccessSites;
    }

    public boolean addClassAccess(String className, InstructionLocation location) {
        DefaultClassAccessSite site = new DefaultClassAccessSite(location, this, className);
        if (classAccessSites.add(site)) {
            graph.addClassAccess(site);
            return true;
        } else {
            return false;
        }
    }
}
