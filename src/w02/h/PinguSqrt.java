package w02.h;
import java.text.DecimalFormat;

public class PinguSqrt {

	public static void sqrt(double n) {
		String result = "";
		if (n >= 0) {
			System.out.println("Wurzel aus "+n);
//			gets string before and after comma
			String[] n_strings;
//			double E-format-String
			if (String.valueOf(n).contains("E")) {
				DecimalFormat df = new DecimalFormat("#");
				df.setMaximumFractionDigits(4);
				n_strings = df.format(n).split("\\.");
			}
//			normal string
			else {
				n_strings = String.valueOf(n).split("\\.");
			}

//			adds zeros
			if (n_strings[0].length() % 2 != 0) {
				n_strings[0] = '0' + n_strings[0];
			}
			try {
				if (n_strings[1].length() % 2 != 0) {
					n_strings[1] = n_strings[1] + '0';
				}
			} catch (IndexOutOfBoundsException e) {
				n_strings = new String[]{n_strings[0], "00"};
			}

//			first round
			int n_index = 0;
			int part = Integer.parseInt(n_strings[0].substring(n_index*2, (n_index+1)*2));
			System.out.println("\n"+part);
			System.out.println("--------");
			int[] part_result = calcPart(part, "0");
			result = result + part_result[1];
			System.out.println("--------");
			System.out.println("Rest: "+part_result[0]);
			System.out.println("neue Ergebnis Ziffer: "+part_result[1]);
//			calc before .
			while ((n_index+1)*2 < n_strings[0].length()) {
				n_index++;
				part = Integer.parseInt(part_result[0] + n_strings[0].substring(n_index*2, (n_index+1)*2));
				System.out.println("\n"+part);
				System.out.println("--------");
				part_result = calcPart(part, result);
				result = result + part_result[1];
				System.out.println("--------");
				System.out.println("Rest: "+part_result[0]);
				System.out.println("neue Ergebnis Ziffer: "+part_result[1]);
			}
//			calc after .
//			save index of fraction point
			int frac_index = n_index;
			n_index = -1;
			while ((n_index+1)*2 < n_strings[1].length() && n_index < 1) {
				n_index++;
				part = Integer.parseInt(part_result[0] + n_strings[1].substring(n_index*2, (n_index+1)*2));
				System.out.println("\n"+part);
				System.out.println("--------");
				part_result = calcPart(part, result);
				result = result + part_result[1];
				System.out.println("--------");
				System.out.println("Rest: "+part_result[0]);
				System.out.println("neue Ergebnis Ziffer: "+part_result[1]);
			}
//			calc as long remainder != 0 or not enough numbers after .
			while((part_result[0] != 0) && n_index < 1) {
				part = part_result[0] * 100;
				System.out.println("\n"+part);
				System.out.println("--------");
				part_result = calcPart(part, result);
				result = result + part_result[1];
				System.out.println("--------");
				System.out.println("Rest: "+part_result[0]);
				System.out.println("neue Ergebnis Ziffer: "+part_result[1]);
				n_index++;
			}
//			Die Methode soll das exakte Ergebnis auf 2 Nachkommastellen berechnen
			result = result.substring(0, frac_index+1) + "." + result.substring(frac_index+1);
			String[] result_split = result.split("\\.");
			if (result_split[1].length() > 2) {
				System.out.println("\nErgebnis: "+result_split[0] +"."+ result_split[1].substring(0,2));
			}
			else {
				if (Integer.parseInt(result_split[1]) == 0) {
					System.out.println("\nErgebnis: "+result_split[0]);
				}
				else {
					if (result_split[1].length() == 2  && result_split[1].charAt(1) == '0') {
						System.out.println("\nErgebnis: "+result_split[0]+"."+result_split[1].charAt(0));
					}
					else {
						System.out.println("\nErgebnis: "+result);
					}
				}
			}
		}
		else {
			System.out.println("Keine negativen Wurzeln!");
		}
	}

	/**
	 * calculates a part of the sqrt
	 * @param part, integer (length=2)
	 * @param result, String, result of whole calculation
	 * @return rest, result
	 */
	private static int[] calcPart(int part, String result) {
		int subtrahend;
		if (result.equals("0")) {
			subtrahend = 1;
		}
		else {
			subtrahend = Integer.parseInt((int)(Double.parseDouble(result) * 2) +"1");
		}
		int counter = 0;
		while (part > 0 && (part-subtrahend) >= 0) {
			System.out.println("-"+subtrahend);
			part = part-subtrahend;
			subtrahend = subtrahend + 2;
			counter++;
		}
		return new int[]{part, counter};
	}

	public static void main(String[] args) {
		// test your implementation here
		sqrt(1049.76);
//		sqrt(4);
//		sqrt(1000000000);
//		sqrt(2147483647);
//		sqrt(4.41);
//		sqrt(2950.662412345);
	}
}
