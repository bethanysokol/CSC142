import java.util.ArrayList;
import java.util.LinkedList;

public class Test {

	public int countChar(String phrase, char target) {
		int count = 0;
		for (int i = 0; i < phrase.length(); i++) {
			if (phrase.charAt(i) == target) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// questionthree();
//		testkeywordcount();
		pyramid();
	}

	private static void questionthree() {

		// int sum = 0;
		// for (int i = 1; i < 1000; i++) {
		// if (i % 3 == 0 || i % 5 == 0) {
		// sum += i;
		// System.out.println(i);
		// }
		// }
		// System.out.println(sum);
	}

	public static void testkeywordcount() {
		Test t = new Test();
		ArrayList<String> list = new ArrayList<String>();
		list.add("The classification of cats");
		list.add("staccato");
		list.add("catamaran");
		list.add("the caterpillar and the bobcat");
		System.out.println(t.keywordCount(list, "cat"));
		System.out.println(list);
		System.out.println(t.keywordCount(list, ""));
	}

	public int keywordCount(ArrayList<String> listOfStrings, String keyword) {
		int count = 0;

		if (!keyword.isEmpty()) {
			inspectList: for (String phrase : listOfStrings) {

				countPhrase: while (!phrase.isEmpty()) {

					int index = phrase.indexOf(keyword);

					if (index >= 0) {
						count++;

						index++;
						if (index < phrase.length()) {
							phrase = phrase.substring(index);
						} else {
							continue inspectList;
						}
					} else {
						continue inspectList;
					}

				}
			}
		}

		return count;
	}

	public static void pyramidBad() {

		int pyramidTop = 4;

		LinkedList<String> list = new LinkedList<String>();
		String line = "";
		int lineLength = pyramidTop;
		for (int i = 1; i <= lineLength; i++) {
			line += " " + i;
		}
		list.add(line);

		for(int i = 1; i < pyramidTop; i++){
			line = "";
			lineLength = pyramidTop - i;
			for (int j = 1; j <= lineLength; j++) {
				line += " " + j;
			}
			list.addFirst(line);
			list.addLast(line);
		}
		
		for (String s : list) {
			System.out.println(s);
		}
	}
	public static void pyramid() {

		int pyramidTop = 4;

		for(int i = 1; i <= 2*pyramidTop-1; i++){
			
			int lineLength = i;
			if (i > pyramidTop){
				lineLength = 2*pyramidTop-i;
			}
			for(int j = 1; j <= lineLength; j++){
				System.out.print(" " + j);
			}
			System.out.println();
		}
	}
}
