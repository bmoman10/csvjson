package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConverterTest {
    private String csvString;
    private String jsonString;
	private Converter converter;

    @Before
    public void setUp() {
		csvString = "\"ID\",\"Total\",\"Assignment 1\",\"Assignment 2\",\"Exam 1\"\n" +
					"\"111278\",\"611\",\"146\",\"128\",\"337\"\n" +
					"\"111352\",\"867\",\"227\",\"228\",\"412\"\n" +
					"\"111373\",\"461\",\"96\",\"90\",\"275\"\n" +
					"\"111305\",\"835\",\"220\",\"217\",\"398\"\n" +
					"\"111399\",\"898\",\"226\",\"229\",\"443\"\n" +
					"\"111160\",\"454\",\"77\",\"125\",\"252\"\n" +
					"\"111276\",\"579\",\"130\",\"111\",\"338\"\n" +
					"\"111241\",\"973\",\"236\",\"237\",\"500\"";
		jsonString = "{" + "\n" +
			"\t" + "\"colHeaders\":[\"Total\",\"Assignment 1\",\"Assignment 2\",\"Exam 1\"],"  + "\n" + 
			"\t" + "\"rowHeaders\":[\"111278\",\"111352\",\"111373\",\"111305\",\"111399\",\"111160\",\"111276\",\"111241\"]," + "\n" +
			"\t" + "\"data\":[[611,146,128,337]," + "\n" +
					"\t" + "\t" + "[867,227,228,412]," + "\n" +
					"\t" + "\t" + "[461,96,90,275]," + "\n" +
					"\t" + "\t" + "[835,220,217,398]," + "\n" +
					"\t" + "\t" + "[898,226,229,443]," + "\n" + 
					"\t" + "\t" + "[454,77,125,252]," + "\n" +
					"\t" + "\t" + "[579,130,111,338]," + "\n" +
					"\t" + "\t" + "[973,236,237,500]" + "\n" +
				"\t" + "]" + "\n" +
		"}";
    }
	
    @Test
    public void testConvertCSVtoJSON() {
		String s = Converter.csvToJson(csvString);
        assertEquals(jsonString, s);
    }

    @Test
    public void testConvertJSONtoCSV() {
		String s = Converter.jsonToCsv(jsonString);
        assertEquals(csvString, s);
    }
}







