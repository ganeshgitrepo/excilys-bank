package com.excilys.ebi.bank.data.loader;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Loader {

	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext("context/applicationContext.xml");
	}
}
