import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task_254 {
	public static void main(String[] args) {
		String outputPath = "OUTPUT.TXT";
		String inputPath = "INPUT.TXT";
		PriestChoicer test = new PriestChoicer(inputPath);
		try (BufferedWriter output = Files.newBufferedWriter(Paths.get(outputPath))) {
			output.write(test.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class PriestChoicer {
	private int countyAmount;
	private int[] priestArray;
	private int[][] noticeArray;

	public PriestChoicer(String path) {
		try (final BufferedReader input = Files.newBufferedReader(Paths.get(path))) {
			Object[] data = input.lines().toArray();
			countyAmount = Integer.parseInt((String) data[0]);
			String[] temp = ((String) data[1]).split(" ");
			priestArray = new int[temp.length];
			for (int i = 0; i < temp.length; i++) {
				priestArray[i] = Integer.parseInt(temp[i]);
			}
			int noticeAmount = Integer.parseInt((String) data[2]);
			noticeArray = new int[noticeAmount][2];
			for (int i = 3, j = 0; i < data.length; i++, j++) {
				temp = ((String) data[i]).split(" ");
				noticeArray[j][0] = Integer.parseInt(temp[0]);
				noticeArray[j][1] = Integer.parseInt(temp[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String choice() {
		int[] tempArray = new int[countyAmount];

		for(int i = 0; i < priestArray.length; i++) {
			int currentPriest = priestArray[i];
			for (int[] ints : noticeArray) {
				if (ints[0] == currentPriest) {
					tempArray[i] = ints[1];
				}
			}
		}

		for(int i = 0; i < countyAmount; i++) {
			if (tempArray[i] == 0) {
				tempArray[i] = priestArray[i];
			}
		}

		StringBuilder result = new StringBuilder();
		for (int value : tempArray) {
			result.append(value).append(" ");
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}

	@Override
	public String toString() {
		return choice();
	}
}