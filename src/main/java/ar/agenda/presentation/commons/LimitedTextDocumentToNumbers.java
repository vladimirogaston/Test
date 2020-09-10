package ar.agenda.presentation.commons;

import ar.agenda.controller.dto.validations.PositiveInteger;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@Slf4j
@NoArgsConstructor
public class LimitedTextDocumentToNumbers extends PlainDocument {

	private static final long serialVersionUID = -3168838677041872560L;

	@Override
	public void insertString(int offset, String str, AttributeSet attr) {
		Integer.parseInt(str);
		try {
			super.insertString(offset, str, attr);
		} catch (BadLocationException e) {
			log.warn(e.getMessage());
		}
	}
}
