package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {
	public static List<Integer> fibonacci() {
		List<Integer> listNumbers = new ArrayList<>();
		Integer maximumNumber = 350;
		int number;

		number = 0;
		while(number <= maximumNumber){
			if(number-1 <= 0){
				listNumbers.add(number);
				number+=1;
			}
			listNumbers.add(number);
			number +=listNumbers.get(listNumbers.size()-2);
			if (number > maximumNumber) listNumbers.add(number);
		}
		return listNumbers;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}