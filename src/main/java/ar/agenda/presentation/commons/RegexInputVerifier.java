package ar.agenda.presentation.commons;

import javax.swing.*;
import java.util.regex.Pattern;

public class RegexInputVerifier extends InputVerifier {
	
	String regex;
	
	public RegexInputVerifier(String regex) {
		assert regex != null;
		this.regex = regex;
	}
	 
	@Override 
	public boolean verify(JComponent aComponent) {
		JTextField textComponent = (JTextField)aComponent;
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(textComponent.getText()).matches();
	}
}