/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.figures.junctions;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;

import uk.ac.bolton.archimate.editor.diagram.figures.IDiagramModelObjectFigure;
import uk.ac.bolton.archimate.editor.diagram.figures.ToolTipFigure;
import uk.ac.bolton.archimate.editor.preferences.Preferences;
import uk.ac.bolton.archimate.editor.ui.ArchimateNames;
import uk.ac.bolton.archimate.editor.utils.StringUtils;
import uk.ac.bolton.archimate.model.IArchimateElement;
import uk.ac.bolton.archimate.model.IDiagramModelArchimateObject;


/**
 * Junction Figure
 * 
 * @author Phillip Beauvoir
 */
public class JunctionFigure extends Ellipse implements IDiagramModelObjectFigure {
    
    private static final Dimension SIZE = new Dimension(15, 15);
    
    private IDiagramModelArchimateObject fDiagramModelObject;
    
    public JunctionFigure(IDiagramModelArchimateObject diagramModelObject) {
        fDiagramModelObject = diagramModelObject;
        setBackgroundColor(ColorConstants.black);
    }

    @Override
    public Dimension getPreferredSize(int wHint, int hHint) {
        return SIZE;
    }
    
    @Override
    public void paintFigure(Graphics graphics) {
        graphics.setAntialias(SWT.ON);
        super.paintFigure(graphics);
    }

    @Override
    public void refreshVisuals() {
        setToolTip();
    }
    
    protected void setToolTip() {
        if(!Preferences.doShowViewTooltips()) {
            setToolTip(null); // clear it in case user changed Prefs
            return;
        }
        
        String text = StringUtils.safeString(fDiagramModelObject.getName());
        
        if(getToolTip() == null) {
            setToolTip(new ToolTipFigure());
        }
        
        ((ToolTipFigure)getToolTip()).setText(text);
        IArchimateElement element = fDiagramModelObject.getArchimateElement();
        String type = ArchimateNames.getDefaultName(element.eClass());
        ((ToolTipFigure)getToolTip()).setType("Type: " + type);
    }

    @Override
    public IFigure getTextControl() {
        return null;
    }

    @Override
    public void dispose() {
    }

    @Override
    public Dimension getDefaultSize() {
        return SIZE;
    }
}