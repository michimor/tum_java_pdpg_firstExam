package w02.p;

public class Calculator {

	public static void main(String[] args) {
		while(true) {
			System.out.println("Wählen Sie eine Operation:");
			System.out.println("1) +");
			System.out.println("2) -");
			System.out.println("3) *");
			System.out.println("4) /");
			System.out.println("5) %");
			System.out.println("6) Programm beenden");
			int input = PinguLib.readInt("");
			if (input >= 1 && input <= 6) {
				if (input == 6) {
					break;
				}
				else {
					System.out.println("Ersten Operanden eingeben:");
					int operand1 = PinguLib.readInt("");
					System.out.println("Zweiten Operanden eingeben:");
					int operand2 = PinguLib.readInt("");
					switch (input) {
						case 1: {
							int sum = operand1 + operand2;
							System.out.println(sum);
							break;
						}
						case 2: {
							int diff = operand1 - operand2;
							System.out.println(diff);
							break;
						}
						case 3: {
							int prod = operand1 * operand2;
							System.out.println(prod);
							break;
						}
						case 4: {
							if (operand2 == 0) {
								System.out.println("Fehler: Division durch 0!");
							}
							else {
								int div = operand1 / operand2;
								System.out.println(div);
							}
							break;
						}
						case 5: {
							if (operand2 == 0) {
								System.out.println("Fehler: Division durch 0!");
							}
							else {
								int mod = operand1 % operand2;
								System.out.println(mod);
							}
							break;
						}
					}
				}
			}
		}
	}
}