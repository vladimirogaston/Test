package ar.agenda.presentation.commons;

import javax.swing.*;

public class PastelessJTextField extends JTextField {
	
	private static final long serialVersionUID = -3512892175508886375L;

	public PastelessJTextField() {
		super();
	}
	
    @Override
    public void paste() { }
}
