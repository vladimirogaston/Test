package ar.agenda.presentation.commons;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitedTextDocument extends PlainDocument {

	private static final long serialVersionUID = -3229812629539723604L;
	private final int max;
	
	public LimitedTextDocument(int max) {
		super();
		this.max = max;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if(str == null) return;
		if((getLength() + str.length()) <= max) super.insertString(offset, str, attr);
	}
}
