package coderefactor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Fileread {

	static String mode = "";

	public static void main(String[] args) {
		String fileName = args[0];
		String filterName = args[1];
		String filterValue = args[2];

		try {
			fileRead(fileName, filterName, filterValue);
		} catch (IOException e) {
		}

	}

	private static void fileRead(String fileName, String filterName, String filterValue) throws IOException {
		File f = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String st;
		String deli = " ; ";
		while ((st = br.readLine()) != null) {
			if (st.startsWith("F1")) {
				mode = "F1";
			} else if (st.startsWith("F2")) {
				mode = "F2";
			} else {
				if ("F1".equals(mode)) {
					deli = ",";
					splitdata(st, deli, filterValue, filterName);
				} else {
					deli = " ; ";
					splitdata(st, deli, filterValue, filterName);
				}
			}
		}
	}

	private static void splitdata(String data, String deli, String filterValue, String filterName) {

		String[] dataarray = data.split(deli);
		String modid = dataarray[2].replace("-", "");

		if (filterName.equalsIgnoreCase("CITY") && data.contains(filterValue)) {
			System.out.println(dataarray[0].replace('D', ' ').trim() + "," + modid);
		} else if (filterName.equalsIgnoreCase("id") && filterValue.equals(modid)) {
			System.out.println(dataarray[1]);
		}

	}

}
