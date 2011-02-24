/*******************************************************************************
 * Copyright (c) 2010 Bolton University, UK.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 *******************************************************************************/
package uk.ac.bolton.archimate.editor.diagram.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.IWorkbenchPart;

import uk.ac.bolton.archimate.editor.diagram.commands.FontCompoundCommand;
import uk.ac.bolton.archimate.editor.diagram.editparts.ITextEditPart;
import uk.ac.bolton.archimate.editor.diagram.editparts.connections.IDiagramConnectionEditPart;
import uk.ac.bolton.archimate.editor.ui.ColorFactory;
import uk.ac.bolton.archimate.editor.ui.FontFactory;
import uk.ac.bolton.archimate.model.IFontAttribute;


/**
 * Font Action
 * 
 * @author Phillip Beauvoir
 */
public class FontAction extends SelectionAction {
    
    public static final String ID = "FontAction";
    public static final String TEXT = "Font...";
    
    public FontAction(IWorkbenchPart part) {
        super(part);
        setText(TEXT);
        setId(ID);
    }

    @Override
    protected boolean calculateEnabled() {
        return getFirstSelectedFontEditPart(getSelectedObjects()) != null;
    }

    private EditPart getFirstSelectedFontEditPart(List<?> selection) {
        for(Object object : getSelectedObjects()) {
            if(isValidEditPart(object)) {
                return (EditPart)object;
            }
        }
        
        return null;
    }
    
    private boolean isValidEditPart(Object object) {
        return object instanceof ITextEditPart || object instanceof IDiagramConnectionEditPart;
    }
    
    @Override
    public void run() {
        List<?> selection = getSelectedObjects();
        
        // Set default font on first selected object
        FontData fontData = FontFactory.getDefaultUserViewFontData();
        String rgbValue = null;
        
        EditPart firstPart = getFirstSelectedFontEditPart(selection);
        if(firstPart != null) {
            Object model = firstPart.getModel();
            if(model instanceof IFontAttribute) {
                rgbValue = ((IFontAttribute)model).getFontColor();
                String fontValue = ((IFontAttribute)model).getFont();
                if(fontValue != null) {
                    try {
                        fontData = new FontData(fontValue);
                    }
                    catch(Exception ex) {
                        //ex.printStackTrace();
                    }
                }
            }
        }
        
        FontDialog dialog = new FontDialog(getWorkbenchPart().getSite().getShell());
        dialog.setText("Select Font");
        dialog.setFontList(new FontData[] { fontData } );
        dialog.setRGB(ColorFactory.convertStringToRGB(rgbValue));
        
        FontData selectedFontData = dialog.open();
        if(selectedFontData != null) {
            execute(createCommand(selection, selectedFontData, dialog.getRGB()));
        }
    }
    
    private Command createCommand(List<?> selection, FontData selectedFontData, RGB newColor) {
        CompoundCommand result = new CompoundCommand("Change font");
        
        for(Object object : selection) {
            if(isValidEditPart(object)) {
                EditPart editPart = (EditPart)object;
                Command cmd = new FontCompoundCommand((IFontAttribute)editPart.getModel(), selectedFontData.toString(), newColor);
                if(cmd.canExecute()) {
                    result.add(cmd);
                }
            }
        }
        
        return result.unwrap();
    }
}