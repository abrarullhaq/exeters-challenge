package TranslateWordsChallenge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class TranslateWordsChallenge {
	public static void main(String[] args) throws IOException {
		Long start = System.nanoTime();
		long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		// --------->(1)
		// find_words.txt path
		FileReader words = new FileReader("C:/Users/S ABRARULL HAQ/Desktop/TranslateWordsChallenge/find_words.txt");
		// --------->(2)
		// create one new txt file with the name of french_dic and copy all the
		// translated french from excel sheet and paste it in french_dic
		// Note-->put all the files in the same location
		FileReader words_in_french = new FileReader("C:/Users/S ABRARULL HAQ/Desktop/TranslateWordsChallenge/french_dic.txt");

		BufferedReader bf_words = new BufferedReader(words);
		BufferedReader bf_words_in_french = new BufferedReader(words_in_french);

		String[] words_array = new String[1000];
		String[] words_array_in_french = new String[1000];
		String s;
		int count = 0;
		while ((s = bf_words.readLine()) != null) {
			words_array[count] = s;
			count++;
		}
		int count1 = 0;
		while ((s = bf_words_in_french.readLine()) != null) {
			words_array_in_french[count1] = s;
			count1++;
		}

		Map<String, String> map = new LinkedHashMap<String, String>();

		for (int i = 0; i < words_array.length; i++) {
			map.put(words_array[i], words_array_in_french[i]);
		}

		// --------->(3)
		// put the t8 shakespeare txt file location here
		String snew = "C:/Users/S ABRARULL HAQ/Desktop/TranslateWordsChallenge/t8.shakespeare.txt";
		Path path = Paths.get(snew);

		Charset charset = StandardCharsets.UTF_8;

		String content = new String(Files.readAllBytes(path), charset);

		// --------->(4)
		// output file location where you gonna create your output file
		// try to put everything in the same location
		File file = new File("C:/Users/S ABRARULL HAQ/Desktop/New folder/t8.shakespeare.translated.txt");
		file.createNewFile();

		// --------->(5)
		// same file location as mentioned in (4)
		FileWriter fww = new FileWriter("C:/Users/S ABRARULL HAQ/Desktop/New folder/t8.shakespeare.translated.txt");

		BufferedWriter bfww = new BufferedWriter(fww);

		for (int i = 0; i < content.length(); i++) {

			bfww.write(content.charAt(i));
		}

		// --------->(6)
		// --------->same file location as mentioned in (4)
		String out_string = "C:/Users/S ABRARULL HAQ/Desktop/New folder/t8.shakespeare.translated.txt";

		Path out_path = Paths.get(out_string);

		Charset out_charset = StandardCharsets.UTF_8;

		String out_content = new String(Files.readAllBytes(out_path), out_charset);

		for (Map.Entry<String, String> temp : map.entrySet()) {
			out_content = out_content.replaceAll(temp.getKey(), temp.getValue());
			Files.write(out_path, out_content.getBytes(out_charset));
		}

		String line, word = "";

		ArrayList<String> arrayString = new ArrayList<String>();

		FileReader fileReader = new FileReader("C:/Users/S ABRARULL HAQ/Desktop/TranslateWordsChallenge/t8.shakespeare.txt");

		@SuppressWarnings("resource")
		BufferedReader bufferReader = new BufferedReader(fileReader);

		while ((line = bufferReader.readLine()) != null) {
			String splString[] = line.toLowerCase().split("([,.\\s]+)");
			for (String temp : splString) {
				arrayString.add(temp);
			}
		}
		File freqFile = new File("C:/Users/S ABRARULL HAQ/Desktop/New folder/freqFileOutput.txt");
		freqFile.createNewFile();

		FileWriter freqFileWriter = new FileWriter("C:/Users/S ABRARULL HAQ/Desktop/New folder/freqFileOutput.txt");


		freqFileWriter.write("English word : French word : Frequency");
		freqFileWriter.write("\n");

		for (Map.Entry<String, String> temp : map.entrySet()) {
			freqFileWriter.write(temp.getKey() + " , " + temp.getValue() + " , "
					+ Collections.frequency(arrayString, temp.getKey()) + " : " + "\n");
		}

		long end = System.nanoTime();
		long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

		long timeElapsed = end - start;
		long actualMemUsed=afterUsedMem-beforeUsedMem;

		double elapsedTimeInSecond = (double) timeElapsed / 1_000_000_000;

		System.out.println("Time to process : "+(int)elapsedTimeInSecond+" seconds");
		System.out.println("Memory used:   "+(actualMemUsed/1000000));

		System.out.println("Translate Words Challenge : Files Translated Successfully :)");

	}

}
