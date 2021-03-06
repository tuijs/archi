/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.archimatetool.model.IArchimateDiagramModel;
import com.archimatetool.model.IArchimateFactory;
import com.archimatetool.model.IDiagramModelComponent;


@SuppressWarnings("nls")
public abstract class DiagramModelComponentTests {
    
    protected IDiagramModelComponent component;
    protected IArchimateDiagramModel dm;
    
    @Before
    public void runBeforeEachDiagramModelComponentTest() {
        component = getComponent();
        dm = IArchimateFactory.eINSTANCE.createArchimateDiagramModel();
    }
    
    protected abstract IDiagramModelComponent getComponent();
    
    @Test
    public void testGetName() {
        CommonTests.testGetName(component);
    }
 
    @Test
    public void testGetAdapter() {
        CommonTests.testGetAdapter(component);
    }
    
    @Test
    public void testGetCopy() {
        component.setName("name");
        IDiagramModelComponent copy = (IDiagramModelComponent)component.getCopy();
        assertNotSame(component, copy);
        assertNull(copy.getId());
        assertEquals(component.getName(), copy.getName());
    }
}
