/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.hammer.validation.checkers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import com.archimatetool.hammer.validation.issues.AdviceType;
import com.archimatetool.hammer.validation.issues.IIssue;
import com.archimatetool.model.IAccessRelationship;
import com.archimatetool.model.IAggregationRelationship;
import com.archimatetool.model.IArchimateDiagramModel;
import com.archimatetool.model.IArchimateRelationship;
import com.archimatetool.model.IAssignmentRelationship;
import com.archimatetool.model.ICompositionRelationship;
import com.archimatetool.model.IDiagramModelArchimateObject;
import com.archimatetool.model.IDiagramModelObject;
import com.archimatetool.model.IRealizationRelationship;
import com.archimatetool.model.ISpecializationRelationship;


/**
 * Checks for problems with nested elements in Views
 * 
 * @author Phillip Beauvoir
 */
public class NestedElementsChecker implements IChecker {
    
    final String fName = Messages.NestedElementsChecker_0;
    final String fDescription = Messages.NestedElementsChecker_1;
    final String fExplanation = Messages.NestedElementsChecker_2 +
                                      Messages.NestedElementsChecker_3;
    
    private List<IArchimateDiagramModel> fViews;
    
    public NestedElementsChecker(List<IArchimateDiagramModel> views) {
        fViews = views;
    }

    public List<IIssue> getIssues() {
        return findWrongNestedElements();
    }
    
    // Nested diagram elements without correct relationships
    List<IIssue> findWrongNestedElements() {
        List<IIssue> issues = new ArrayList<IIssue>();
        
        for(IArchimateDiagramModel dm : fViews) {
            for(Iterator<EObject> iter = dm.eAllContents(); iter.hasNext();) {
                EObject eObject = iter.next();
                
                if(eObject instanceof IDiagramModelArchimateObject) {
                    IDiagramModelArchimateObject parent = (IDiagramModelArchimateObject)eObject;
                    
                    for(IDiagramModelObject dmoChild : parent.getChildren()) {
                        if(dmoChild instanceof IDiagramModelArchimateObject) {
                            IDiagramModelArchimateObject child = (IDiagramModelArchimateObject)dmoChild;
                            
                            if(!hasValidNestedRelation(parent, child)) {
                                String description =  NLS.bind(fDescription, new Object[] {
                                        child.getName(),
                                        parent.getName()
                                });
                                
                                IIssue issue = new AdviceType(fName, description, fExplanation, child);
                                issues.add(issue);
                            }
                        }
                    }
                }
            }
        }
        
        return issues;
    }

    boolean hasValidNestedRelation(IDiagramModelArchimateObject parent, IDiagramModelArchimateObject child) {
        for(IArchimateRelationship relation : parent.getArchimateElement().getSourceRelationships()) {
            
            if(relation.getTarget() == child.getArchimateElement()) {
                if(relation instanceof ICompositionRelationship || relation instanceof IAggregationRelationship
                        || relation instanceof IAssignmentRelationship || relation instanceof ISpecializationRelationship
                        || relation instanceof IRealizationRelationship || relation instanceof IAccessRelationship) {
                    return true;
                }
            }
            
        }
        
        return false;
    }
}
