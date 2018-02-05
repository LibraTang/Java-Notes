package exception;

import java.util.Scanner;

class OpenException extends Exception{
	
}

class CloseException extends OpenException{
	
}

public class ArrayIndex {

	public static int open() {
		return -1;
	}
	
	public static void readFile() throws OpenException {
		if(open() == -1) {
			throw new OpenException();
		}
	}
	public static void main(String[] args) {
		try {
			readFile();
		} catch (OpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
