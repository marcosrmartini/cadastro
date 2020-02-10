package br.com.mmartini.cadastro.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Util {

	public static int calculaIdade(java.util.Date dataNasc) {
		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.setTime(dataNasc);

		Calendar today = Calendar.getInstance();

		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		dateOfBirth.add(Calendar.YEAR, age);
		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;
	}

}
