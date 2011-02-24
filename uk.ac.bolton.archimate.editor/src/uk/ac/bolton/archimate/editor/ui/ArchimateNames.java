/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.ui;

import org.eclipse.emf.ecore.EClass;

import uk.ac.bolton.archimate.model.IArchimatePackage;



/**
 * Archimate Names
 * 
 * @author Phillip Beauvoir
 */
public final class ArchimateNames {
    
    public static final String getDefaultName(EClass eClass) {
        return getDefaultName(eClass, false);
    }
    
    public static final String getDefaultShortName(EClass eClass) {
        return getDefaultName(eClass, true);
    }
    
    private static final String getDefaultName(EClass eClass, boolean shortName) {
        switch(eClass.getClassifierID()) {
            // Business
            case IArchimatePackage.BUSINESS_ACTIVITY:
                return shortName ? "Activity" : "Business Activity";
            case IArchimatePackage.BUSINESS_ACTOR:
                return shortName ? "Actor" : "Business Actor";
            case IArchimatePackage.BUSINESS_COLLABORATION:
                return shortName ? "Collaboration" : "Business Collaboration";
            case IArchimatePackage.CONTRACT:
                return "Contract";
            case IArchimatePackage.BUSINESS_EVENT:
                return shortName ? "Event" : "Business Event";
            case IArchimatePackage.BUSINESS_FUNCTION:
                return shortName ? "Function" : "Business Function";
            case IArchimatePackage.BUSINESS_INTERACTION:
                return shortName ? "Interaction" : "Business Interaction";
            case IArchimatePackage.BUSINESS_INTERFACE:
                return shortName ? "Interface" : "Business Interface";
            case IArchimatePackage.MEANING:
                return "Meaning";
            case IArchimatePackage.BUSINESS_OBJECT:
                return shortName ? "Object" : "Business Object";
            case IArchimatePackage.BUSINESS_PROCESS:
                return shortName ? "Process" : "Business Process";
            case IArchimatePackage.PRODUCT:
                return "Product";
            case IArchimatePackage.REPRESENTATION:
                return "Representation";
            case IArchimatePackage.BUSINESS_ROLE:
                return shortName ? "Role" : "Business Role";
            case IArchimatePackage.BUSINESS_SERVICE:
                return shortName ? "Service" : "Business Service";
            case IArchimatePackage.VALUE:
                return "Value";
                
            // Application
            case IArchimatePackage.APPLICATION_COLLABORATION:
                return shortName ? "Collaboration" : "Application Collaboration";
            case IArchimatePackage.APPLICATION_COMPONENT:
                return shortName ? "Component" : "Application Component";
            case IArchimatePackage.APPLICATION_FUNCTION:
                return shortName ? "Function" : "Application Function";
            case IArchimatePackage.APPLICATION_INTERACTION:
                return shortName ? "Interaction" : "Application Interaction";
            case IArchimatePackage.APPLICATION_INTERFACE:
                return shortName ? "Interface" : "Application Interface";
            case IArchimatePackage.DATA_OBJECT:
                return "Data Object";
            case IArchimatePackage.APPLICATION_SERVICE:
                return shortName ? "Service" : "Application Service";
                
            // Technology
            case IArchimatePackage.ARTIFACT:
                return "Artifact";
            case IArchimatePackage.COMMUNICATION_PATH:
                return "Communication Path";
            case IArchimatePackage.NETWORK:
                return "Network";
            case IArchimatePackage.INFRASTRUCTURE_INTERFACE:
                return "Infrastructure Interface";
            case IArchimatePackage.INFRASTRUCTURE_SERVICE:
                return "Infrastructure Service";
            case IArchimatePackage.NODE:
                return "Node";
            case IArchimatePackage.SYSTEM_SOFTWARE:
                return "System Software";
            case IArchimatePackage.DEVICE:
                return "Device";
                
            // Junctions
            case IArchimatePackage.JUNCTION:
                return "Junction";
            case IArchimatePackage.AND_JUNCTION:
                return "And Junction";
            case IArchimatePackage.OR_JUNCTION:
                return "Or Junction";
                
            // Relationships
            case IArchimatePackage.ACCESS_RELATIONSHIP:
                return "Access relation";
            case IArchimatePackage.AGGREGATION_RELATIONSHIP:
                return "Aggregation relation";
            case IArchimatePackage.ASSIGNMENT_RELATIONSHIP:
                return "Assignment relation";
            case IArchimatePackage.ASSOCIATION_RELATIONSHIP:
                return "Association relation";
            case IArchimatePackage.COMPOSITION_RELATIONSHIP:
                return "Composition relation";
            case IArchimatePackage.FLOW_RELATIONSHIP:
                return "Flow relation";
            case IArchimatePackage.REALISATION_RELATIONSHIP:
                return "Realisation relation";
            case IArchimatePackage.SPECIALISATION_RELATIONSHIP:
                return "Specialisation relation";
            case IArchimatePackage.TRIGGERING_RELATIONSHIP:
                return "Triggering relation";
            case IArchimatePackage.USED_BY_RELATIONSHIP:
                return "Used By relation";
                

        }
        return "";
    }
}