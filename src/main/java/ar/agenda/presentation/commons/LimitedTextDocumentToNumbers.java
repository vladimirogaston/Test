package ar.agenda.presentation.commons;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitedTextDocumentToNumbers extends PlainDocument {

	private static final long serialVersionUID = -3168838677041872560L;

	public LimitedTextDocumentToNumbers() {
		super();
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if(str == null) return;
		try {
			Integer.parseInt(str);
			super.insertString(offset, str, attr);
		}catch(Exception e) {
			return;
		}
	}
}
