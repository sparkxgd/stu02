package demo;

import com.jfinal.core.JFinal;

public class Start {
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8000, "/", 5);
	}
}
