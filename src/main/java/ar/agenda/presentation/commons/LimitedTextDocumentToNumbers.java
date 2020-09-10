package ar.agenda.presentation.commons;

import lombok.NoArgsConstructor;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

@NoArgsConstructor
public class LimitedTextDocumentToNumbers extends PlainDocument {

	private static final long serialVersionUID = -3168838677041872560L;

	@Override
	public void insertString(int offset, String str, AttributeSet attr) {
		if(str != null) {
			try {
				Integer.parseInt(str);
				super.insertString(offset, str, attr);
			}catch(Exception e) {
				return;
			}
		}
	}
}
