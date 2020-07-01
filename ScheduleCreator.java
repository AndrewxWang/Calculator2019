import java.util.*;

public class schedule {
	public static void main (String [] args) {
		ArrayList<String> classList = new ArrayList<String>();
		Scanner in = new Scanner(System.in);

		int count = 1;
		while (true) {
			System.out.println("Type \"done\" when you are finished. Enter class for period " + count + ".");
			String clas = in.nextLine();
			if (clas.equals("done")) {
				if (classList.size() > 1) {
					break;
				}
				else {
					System.out.println("Please enter at least one class.");
					continue;
				}
			}
			if (clas.equals("")) {
				System.out.println("Please enter a class.");
				continue;
			}
			classList.add(clas);
			count++;
		}	

		System.out.println("You have a total of " + classList.size() + " classes.");
		System.out.println(" ");
		System.out.println("Schedule:");

		for (int c = 0; c < classList.size(); c++) {
			int ce = c+1;
			System.out.println("Period: " + ce + " - Class: " + classList.get(c));
		}

		System.out.println(" ");
		System.out.println("Classes avaliable: " + classList.size());
		System.out.println("Do you want to remove a class? (yes/no)");
		while (true) {

			String yesno = in.nextLine();
			if (yesno.equals("yes") && classList.size() >= 1){
				while (true) {
					System.out.println("Which class? (1-" + classList.size() + ")");
					try {
						int classRemove = in.nextInt();
						if (classRemove <= classList.size()) {
							System.out.println("Removed " + classList.get(classRemove-1) + ".");
							classList.remove(classRemove-1);
							break;
						}
						else {
							System.out.println("Not allowed.");
						}
					}

					catch (Exception e) {
						System.out.println("Please enter a number.");
						break;
					}

				}
			}
			else if (yesno.equals("no")) {
				break;
			}
			else if (classList.size() < 1) {
				System.out.println("No more classes to remove.");
				break;
			}
			else {
				System.out.println("Classes avaliable: " + classList.size());
				System.out.println("Do you want to remove a class? (yes/no)");
			}
		}
		System.out.println("Do you want to add any other classes? (yes/no)");
		while (true) {
			String yesno2 = in.nextLine();

			if (yesno2.equals("yes")) {
				int clist = classList.size()+1;
				System.out.println("Enter class:");
				String classP = in.nextLine();
				System.out.println("What period do you want to add this class to? (1-" + clist + ")");
				try {
					int periodAdd = in.nextInt();
					if (periodAdd <= classList.size()+1) {
						classList.add(periodAdd-1, classP);
						System.out.println("Added " + classList.get(periodAdd-1) + ".");
					}
					else {
						System.out.println("Not avaliable.");
					}
				}
				catch (Exception e) {
					System.out.println("Please enter a number.");
					break;
				}
			}
			else if (yesno2.equals("no")) {
				break;
			}
			else {
				System.out.println("Do you want to add any other classes? (yes/no)");
			}
		}

		System.out.println("You have a total of " + classList.size() + " classes.");
		System.out.println(" ");
		System.out.println("Schedule:");
		for (int c = 0; c < classList.size(); c++) {
			int ce = c+1;
			System.out.println("Period: " + ce + " - Class: " + classList.get(c));
		}

		in.close();
	}
}
