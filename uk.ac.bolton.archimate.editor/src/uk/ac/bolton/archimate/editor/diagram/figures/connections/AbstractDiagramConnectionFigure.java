/*******************************************************************************
 * Copyright (c) 2010-11 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.figures.connections;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import uk.ac.bolton.archimate.editor.diagram.util.AnimationUtil;
import uk.ac.bolton.archimate.editor.ui.ColorFactory;
import uk.ac.bolton.archimate.editor.ui.FontFactory;
import uk.ac.bolton.archimate.editor.utils.PlatformUtils;
import uk.ac.bolton.archimate.model.IDiagramModelConnection;


/**
 * Abstract implementation of a connection figure.  Subclasses should decide how to draw the line
 * 
 * @author Phillip Beauvoir
 */
public abstract class AbstractDiagramConnectionFigure
extends PolylineConnection implements IDiagramConnectionFigure {

    protected Label fConnectionLabel;
    
    protected int fTextPosition = -1;
    
    private IDiagramModelConnection fDiagramModelConnection;

    protected Color fFontColor;
    protected Color fLineColor;
    
	public AbstractDiagramConnectionFigure(IDiagramModelConnection connection) {
	    fDiagramModelConnection = connection;

	    setFigureProperties();
		
		// Have to add this if we want Animation to work!
		AnimationUtil.addConnectionForRoutingAnimation(this);
	}
	
	public IDiagramModelConnection getModelConnection() {
	    return fDiagramModelConnection;
	}
	
	protected void setFigureProperties() {
		setTargetDecoration(new PolygonDecoration()); // arrow at target endpoint
	}
	
    public void refreshVisuals() {
        // If the text position has been changed by user update it
        if(fDiagramModelConnection.getTextPosition() != fTextPosition) {
            fTextPosition = fDiagramModelConnection.getTextPosition();
            setLabelLocator(fTextPosition);
        }
        
        setLabelFont();
        
        setLabelFontColor();
        
        setLineColor();
        
        setConnectionText();
        
        setLineWidth();
        
        setToolTip();
    }

    /**
     * @param copy
     * @return True if the user clicked on the Relationship edit label
     */
    public boolean didClickConnectionLabel(Point requestLoc) {
        Label label = getConnectionLabel();
        label.translateToRelative(requestLoc);
        return label.containsPoint(requestLoc);
    }

    public Label getConnectionLabel() {
        if(fConnectionLabel == null) {
            fConnectionLabel = new Label(""); //$NON-NLS-1$
            add(fConnectionLabel);
        }
        return fConnectionLabel;
    }

    private void setLabelLocator(int position) {
        Locator locator = null;

        switch(position) {
            case IDiagramModelConnection.TEXT_POSITION_SOURCE:
                locator = new ConnectionEndpointLocator(this, false);
                break;
            case IDiagramModelConnection.TEXT_POSITION_MID:
                locator = new ConnectionLocator(this, ConnectionLocator.MIDDLE);
                break;
            case IDiagramModelConnection.TEXT_POSITION_TARGET:
                locator = new ConnectionEndpointLocator(this, true);
                break;
        }
        
        setConstraint(getConnectionLabel(), locator);
    }
    
    protected void setConnectionText() {
        getConnectionLabel().setText(fDiagramModelConnection.getText());
    }

    /**
     * Set the font in the label to that in the model, or failing that, as per user's default
     */
    protected void setLabelFont() {
        String fontName = fDiagramModelConnection.getFont();
        Font font = FontFactory.get(fontName);
        
        // Adjust for Windows DPI
        if(PlatformUtils.isWindows()) {
            font = FontFactory.getAdjustedWindowsFont(font);
        }

        fConnectionLabel.setFont(font);
    }

    /**
     * Set the font color to that in the model, or failing that, as per default
     */
    protected void setLabelFontColor() {
        String val = fDiagramModelConnection.getFontColor();
        Color c = ColorFactory.get(val);
        if(c == null) {
            c = ColorConstants.black; // have to set default color otherwise it inherits line color
        }
        if(c != fFontColor) {
            fFontColor = c;
            fConnectionLabel.setForegroundColor(c);
        }
    }
    
    /**
     * Set the line color to that in the model, or failing that, as per default
     */
    protected void setLineColor() {
        String val = fDiagramModelConnection.getLineColor();
        Color c = ColorFactory.get(val);
        if(c != fLineColor) {
            fLineColor = c;
            setForegroundColor(c);
        }
    }
    
    protected void setLineWidth() {
        setLineWidth(fDiagramModelConnection.getLineWidth());
    }
    
    /**
     * Highlight this connection
     */
    public void highlight(boolean set) {
    }
    
    /**
     * Set the tooltip
     */
    protected void setToolTip() {
    }
    
    public void dispose() {
    }
}