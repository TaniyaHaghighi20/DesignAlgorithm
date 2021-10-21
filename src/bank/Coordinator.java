package bank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Coordinator {

	private int[] generateArrayIntNumber(int min, int max, int n) {
		int[] array = new int[n];
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			array[i] = (int) (Math.random() * (max - min) + min);
		}
		return array;
	}

	private double[] generateArrayDoubleNumber(int min, int max, int n) {
		double[] array = new double[n];
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			array[i] = Math.random() * (max - min) + min;
		}
		return array;
	}

	private long getTime() {
		return System.nanoTime();
	}

	public void experiment(int n, int maxRep) throws IOException {
		Integer[] r = { 1000, 100000, 1000000, 10000000, 100000000, 1000000000 };
		ArrayList<Integer> ranges = new ArrayList<Integer>(Arrays.asList(r));
		BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

		for (int num = 100; num <= n; num += 50) {
			for (int j = 0; j < maxRep; j++) {
				System.out.println("Testing n= " + num);
				String[] city = { "Tehran", "Isfahan", "Shiraz", "Kerman", "Karaj", "Mashhad", "Tabriz", "Sari",
						"Elam" };
				int[] id = generateArrayIntNumber(1, 1000000000, num);
				double[] balance = generateArrayDoubleNumber(1000, 1000000, num);
				int[] citiesIndex = generateArrayIntNumber(0, 8, num);
				String name = "Robert";
				Bank bank1 = new Bank("bank1");
				Bank2 bank2 = new Bank2("Bank2");
				Bank3 bank3 = new Bank3("bank3");
				Bank4 bank4 = new Bank4("bank4");
				Bank4 bank5 = new Bank4("bank5");
				ArrayList<GeneralBank> bb = new ArrayList<>();

				bb.add(bank1);
				bb.add(bank2);
				bb.add(bank3);
				bb.add(bank4);
				bb.add(bank5);

				writer.write(num + ",");

				for (GeneralBank b : bb) {
					long begin = getTime();

					// add
					for (int k = 0; k < num; k++) {
						// struct.insert(keys[i],vals[i]);
						if (!b.addAccount(new Account(id[k], name, balance[k], city[citiesIndex[k]]))) {
							System.out.println(b.getName() + "     Existed id : error in insert   " + id[k]);
//							System.out.println("Previouse account with this id \n "+b.findAccount(id[k]).toString());
							System.out.println(
									"---------------------------------------------------------------------------------------------------");
						}
					}
					long finish = getTime();
					writer.write(/* struct.toString()+","+ */(finish - begin) / num + ",");

					// search
					begin = getTime();
					for (int i = 0; i < num; i++) {
						Account temp = b.findAccount(id[i]);
						if (temp == null) // test & validation
							System.out.println(b.toString() + " : error in search. search result=" + temp);
						// else
						// System.out.println("good");
					}
					finish = getTime();
					writer.write(/* struct.toString()+","+ */(finish - begin) / num + ",");

					// city aggregation total balance
					begin = getTime();
					b.getTotalBalancePerCity();
					finish = getTime();
					writer.write(/* struct.toString()+","+ */(finish - begin) + ",");

					// city aggregation total count
					begin = getTime();
					b.getTotalCountPerCity();
					finish = getTime();
					writer.write(/* struct.toString()+","+ */(finish - begin) + ",");

					// city aggregation count per range
					begin = getTime();
					b.getTotalCountPerRange(ranges);
					finish = getTime();
					writer.write(/* struct.toString()+","+ */(finish - begin) + ",");

				}
				writer.write("\n");

			}
		}
		writer.close();
	}
}
