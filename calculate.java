import java.util.*;

public class Calculator {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		ArrayList<Double> numList = new ArrayList<Double>();
		ArrayList<Double> answers = new ArrayList<Double>();

		System.out.println("");
		System.out.println("~~~ Welcome! ~~~");
		System.out.println("Calculator by Andrew Wang");
		System.out.println("Type \"inputs\" to view all avaliable inputs");
		System.out.println("");
		int countz = -1; // use to count answers

		while (true) {
			System.out.println("Welcome to home screen.");
			System.out.println("Enter a possible input:");
			String in = input.nextLine();
			if (in.equals("+") || in.equals("-") || in.equals("*") || in.equals("/")) {

				while (true) {
					System.out.println("Enter a number(s). Type \"exit\" to continue.");
					String inn = input.nextLine();

					// checks to see if the string entered is a number
					boolean isNumber = numeric(inn);

					if (inn.equals("exit") || inn.equals("Exit")) {
						break;
					} else if (isNumber) {
						double op = Double.parseDouble(inn);
						System.out.println(in + " " + inn);
						numList.add(op);
					} else if (inn.equals("pi")) {
						double op = Math.PI;
						System.out.println(in + " " + op);
						numList.add(op);
					} else if (inn.equals("ans") && answers.size() >= 1) {
						System.out.println("Which answer do you want to use?");
						while (true) {
							String answe = input.nextLine();

							boolean isAnsNum = numeric(answe);

							if (isAnsNum) {
								double answo = Double.parseDouble(answe);
								int answ = (int) answo;

								if (answers.size() > 0 && answ >= 0 && answ < answers.size()) {
									System.out.println(in + " " + answers.get(answ));
									numList.add(answers.get(answ));
									break;
								} else if (answers.size() > 0) {
									int ansiu = answers.size() - 1;
									System.out.println("Enter the index of the answer, " + "0-" + ansiu);
								} else {
									System.out.println("Please enter an index.");
								}
							} else {
								System.out.println("Please enter a number.");
							}
						}
					}

					else {
						System.out.println("Please enter a number.");
					}
				}

				double result = calculate(in, numList);

				if (in.equals("/") && numList.contains(0.0)) {
					int ccc = 0; // used for divide by zero
					for (int c = 1; c < numList.size(); c++) {
						if (numList.get(c) == 0) {
							System.out.println("The answer is: undefined.");
							ccc++;
							break;
						}
					}
					if (ccc > 0) {
						continue;
					}
				}

				int resInt = (int) result;

				if (result - resInt == 0) {
					System.out.println("The answer is: " + "" + resInt + "");
				} else {
					System.out.println("The answer is: " + "" + result + "");
				}
				System.out.println("");
				countz++;
				System.out.println("Result has been saved as answer " + "\"" + countz + "\"."
						+ " Type \"answers\" to view your answer history.");
				answers.add(result);
				numList.clear();

				System.out.println("");
			} else if (in.equals("factorial")) {
				System.out.println("Enter number:");
				double numsz = input.nextDouble();

				double resz = calculateFactorial(numsz);

				int reszInt = (int) resz;

				if (resz - reszInt == 0) {
					System.out.println("The answer is: " + "" + reszInt + "");
				} else {
					System.out.println("The answer is: " + "" + resz + "");
				}

				countz++;
				answers.add(resz);
			}

			// list all the possible inputs
			else if (in.equals("inputs")) {
				System.out.println("~~~ Avaliable Inputs! ~~~");
				System.out.println("Enter \"+\" for addition.");
				System.out.println("Enter \"-\" for subtraction.");
				System.out.println("Enter \"*\" for multiplication.");
				System.out.println("Enter \"/\" for division.");
				System.out.println("Enter \"factorial\" for factorial.");
				System.out.println("Enter \"answers\" to view your previous answers.");
				System.out.println("Enter \"quit\" to terminate code.");
				System.out.println("");
				System.out.println("~~~ Other ~~~");
				System.out.println("ans = select answer from before");
				System.out.println("pi = 3.14...");
				System.out.println("");
			}
			// if user enters quit, break loop and end code
			else if (in.equals("quit")) {
				System.out.println("Thank you!");
				break;
			}

			// view all answers. if no answers, it will print none.
			else if (in.equals("answers")) {
				System.out.println("~~~ Answers! ~~~");
				if (answers.size() > 0) {
					System.out.println("Answers History:");
					for (int c = 0; c < answers.size(); c++) {
						double ansDoub = answers.get(c);
						int ansInt = (int) ansDoub;

						if (ansDoub - ansInt == 0) {
							System.out.println("Answer " + c + " is: " + ansInt);
						} else {
							System.out.println("Answer " + c + " is: " + ansDoub);
						}
					}
					System.out.println("");
					System.out.println(
							"If you want to remove an answer, input \"remove\". If you want to clear all of your answers, type \"clear\". Otherwise, input \"quit\"");

					while (true) {
						String inAns = input.nextLine();

						if (inAns.equals("remove")) {
							System.out.println("Type a possible index.");

							while (true) {
								int indd = input.nextInt();

								if (indd >= 0 && indd < answers.size()) {
									double anssDoub = answers.get(indd);
									int anssInt = (int) anssDoub;

									if (anssDoub - anssInt == 0) {
										System.out.println("Removed index " + indd + ": " + anssInt);
									} else {
										System.out.println("Removed index " + indd + ": " + anssDoub);
									}

									answers.remove(indd);
									countz--;
									break;
								} else if (answers.size() == 0) {

									break;
								} else {
									System.out.println("Enter a possible index.");
								}
							}
						} else if (inAns.equals("quit")) {
							break;
						} else if (answers.size() == 0) {
							System.out.println("No more answers avaliable.");
							break;
						} else if (inAns.equals("clear")) {
							System.out.println("You have cleared all of your answers.");
							answers.removeAll(answers);
							countz = 0;
							break;
						} else {
							System.out.println("Please type \"remove\", \"clear\" or \"quit\"");
						}
					}

					System.out.println("");
				}

				else {
					System.out.println("Answers History:");
					System.out.println("No answers yet.");
					System.out.println("");
				}
			}

			else {
				System.out.println("Please type a correct input. Enter \"inputs\" to view all avaliable inputs.");
				System.out.println("");
			}
		}

		input.close();
	}

	public static boolean numeric(String inn) {
		try {
			Double testing = Double.parseDouble(inn);
			testing -= testing;
		}

		catch (Exception e) { // NumberFormatException | NullPointerException nfe
			return false;
		}
		return true;
	}

	public static double calculate(String in, ArrayList<Double> numList) {
		double res = 0;
		double len = numList.size();
		// addition
		if (in.equals("+")) {
			for (int c = 0; c < len; c++) {
				res += numList.get(c);
			}
		}
		// subtraction
		else if (in.equals("-")) {
			res = numList.get(0);
			for (int c = 0; c < len - 1; c++) {
				res -= numList.get(c + 1);
			}
		}
		// multiplication
		else if (in.equals("*")) {
			res = numList.get(0);
			for (int c = 0; c < len - 1; c++) {
				res *= numList.get(c + 1);
			}
		}
		// division
		else if (in.equals("/")) {
			res = numList.get(0);
			for (int c = 0; c < len - 1; c++) {
				res /= numList.get(c + 1);
			}
		}
		// returns result
		return res;
	}

	public static double calculateFactorial(Double numsz) {
		if (numsz == 0) {
			return numsz;
		} else if (numsz > 1) {
			return numsz * calculateFactorial(numsz - 1);
		}
		return numsz;
	}

}