/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.hammer.validation.issues;


import org.junit.Before;

import junit.framework.JUnit4TestAdapter;


@SuppressWarnings("nls")
public class AdviceTypeTests extends AbstractIssueTypeTests {
    
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(AdviceTypeTests.class);
    }
    
    @Before
    public void runOnceBeforeEachTest() {
        issueType = new AdviceType("advice", "description", "explanation", null);
    }
    
}
