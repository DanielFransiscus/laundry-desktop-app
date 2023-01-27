/*
 * Copyright (c) 2009-2011, EzWare
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.Redistributions
 * in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.Neither the name of the
 * EzWare nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior
 * written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

package org.oxbow.swingbits.dialog.task.design;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.Icon;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.oxbow.swingbits.dialog.task.CommandLink;
import org.oxbow.swingbits.dialog.task.ICommandLinkPainter;
import org.oxbow.swingbits.dialog.task.IContentDesign;
import org.oxbow.swingbits.dialog.task.TaskDialog;
import org.oxbow.swingbits.util.Markup;


public class CommandLinkButton extends JToggleButton {
    
    private static final long serialVersionUID = 1L;
    private static final Insets buttonInsets = new Insets(7, 7, 7, 15);
    
    private final CommandLink link;
    private final ICommandLinkPainter painter;

    public CommandLinkButton( CommandLink link, ICommandLinkPainter painter ) {
        super();
        this.link = link;
        this.painter = painter;

        setHorizontalAlignment(SwingConstants.LEFT);
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setVerticalAlignment(SwingConstants.TOP);
        setVerticalTextPosition(SwingConstants.TOP);
        setIconTextGap(7);

        Icon icon = link.getIcon();
        setIcon( icon == null? UIManager.getIcon( IContentDesign.ICON_COMMAND_LINK ): icon );
        setText(buildText());
        
        setMargin( buttonInsets );
        
        if ( painter != null ) painter.prepareSource(this);

        addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                setSelected(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // NOTE: if we really are de-selected is controlled by the
                // ButtonGroup.
                setSelected(false);
            }

        });
        
        addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                TaskDialog dlg = TaskDialog.getInstance((Component) e.getSource());
                if ( dlg != null ) {
                    dlg.setResult(CommandLinkButton.this.link);
                    //dlg.setResult( TaskDialog.StandardCommand.OK );
                    dlg.setVisible(false);
                }

            }

        });

    }
    
    private String buildText() {

        String txt = 
            "<html><head>" +
              "<style type='text/css'>" +
                "p.tdi { %s%s }" +
                "p.tdt { %s%s }" +
              "</style>" +
            "</head>" +
              "<p class=\"tdi\">%s</p>" +
              "<p class=\"tdt\">%s</p>" +
            "</html>";
        
        String instrColorCss = Markup.toCSS(UIManager.getColor( IContentDesign.COLOR_INSTRUCTION_FOREGROUND ));
        
        return String.format(txt,
                Markup.toCSS(UIManager.getFont( IContentDesign.FONT_INSTRUCTION )),
                instrColorCss,
                Markup.toCSS(UIManager.getFont( IContentDesign.FONT_TEXT )),
                instrColorCss,
                Markup.toHTML( link.getInstruction(), false ),
                Markup.toHTML( link.getText(), false ) );

    }

    @Override
    protected void paintComponent(Graphics g) {
        if ( painter != null ) painter.paint(g, this);
        super.paintComponent(g);
    }


}
