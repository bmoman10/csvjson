package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Converter {
    /*
        Consider a CSV file like the following:
        
        ID,Total,Assignment 1,Assignment 2,Exam 1
        111278,611,146,128,337
        111352,867,227,228,412
        111373,461,96,90,275
        111305,835,220,217,398
        111399,898,226,229,443
        111160,454,77,125,252
        111276,579,130,111,338
        111241,973,236,237,500
        
        The corresponding JSON file would be as follows (note the curly braces):
        
        {
            "colHeaders":["Total","Assignment 1","Assignment 2","Exam 1"],
            "rowHeaders":["111278","111352","111373","111305","111399","111160","111276","111241"],
            "data":[[611,146,128,337],
                    [867,227,228,412],
                    [461,96,90,275],
                    [835,220,217,398],
                    [898,226,229,443],
                    [454,77,125,252],
                    [579,130,111,338],
                    [973,236,237,500]
            ]
        }  
    */
	
	private static String getColHeaders(List<String[]> lines) {
		String colHeaders = "";
		for(String[] token : lines) {
			if(token == lines.get(0)) {
				for(String element : token) {
					if(element != token[0] && element != token[token.length-1]) {
						colHeaders += "\"" + element + "\",";
					}
					if(element == token[token.length-1]) {
						colHeaders += "\"" + element + "\"]" + ",\n"; 
					}
				}
			}
		}
		return colHeaders;
	}
	
	private static String getRowHeaders(List<String[]> lines) {
		String rowHeaders = "";
		for(String[] token : lines) {
			if(token != lines.get(lines.size()-1) && token != lines.get(0)){
				for(String element : token) {
					if(element == token[0]) {
						rowHeaders += "\"" + element + "\",";
					}
				}
			}
			else if(token == lines.get(lines.size()-1)) {
				for(String element : token) {
					if(element == token[0]) {
						rowHeaders += "\"" + element + "\"]" + ",\n";
					}
				}
			}
		}
		return rowHeaders;
	}
	
	private static String getData(List<String[]> lines) {
		String data = "";
		for(String[] token : lines) {
			if(token != lines.get(0) && token != lines.get(lines.size()-1)) {
				for(String element : token) {
					if(element == token[1]) {
						data += "[" + element + ",";
					}
					else if(element != token[0] && element != token[token.length-1]) {
						data += element + ",";
					}
					else if(element == token[token.length-1]) {
						data += element + "]," + "\n" + "\t" + "\t";
					}
				}
			}
			if(token == lines.get(lines.size()-1)) {
				for(String element : token) {
					if(element == token[1]) {
						data += "[" + element + ",";
					}
					else if(element != token[0] && element != token[token.length-1]) {
						data += element + ",";
					}
					else if(element == token[token.length-1]) {
						data += element + "]" + "\n" + "\t" + "]" + "\n" + "}";
					}
				}
			}
		}
		return data;
	}
	
    
    @SuppressWarnings("unchecked")
    public static String csvToJson(String csvString) {
		String jsonString = "{\n";
		String colHeaders = "\t" + "\"colHeaders\":[";
		String rowHeaders = "\t" + "\"rowHeaders\":[";
		String data = "\t" + "\"data\":[";
		CSVReader reader = new CSVReader(new StringReader(csvString));
		try {
			List<String[]> lines = reader.readAll();
			colHeaders += getColHeaders(lines);
			rowHeaders += getRowHeaders(lines);
			data += getData(lines);
		}
		catch(IOException e) {}
		jsonString += colHeaders;
		jsonString += rowHeaders;
		jsonString += data;
        return jsonString;
    }
    
    public static String jsonToCsv(String jsonString) {
		String csvString = "";
		
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(jsonString);
			JSONArray array = (JSONArray)obj;
		} catch(Exception e) {}
		
        return "";
    }
	
	public static void main(String[] args) {
		String csvString = "\"ID\",\"Total\",\"Assignment 1\",\"Assignment 2\",\"Exam 1\"\n" +
					"\"111278\",\"611\",\"146\",\"128\",\"337\"\n" +
					"\"111352\",\"867\",\"227\",\"228\",\"412\"\n" +
					"\"111373\",\"461\",\"96\",\"90\",\"275\"\n" +
					"\"111305\",\"835\",\"220\",\"217\",\"398\"\n" +
					"\"111399\",\"898\",\"226\",\"229\",\"443\"\n" +
					"\"111160\",\"454\",\"77\",\"125\",\"252\"\n" +
					"\"111276\",\"579\",\"130\",\"111\",\"338\"\n" +
					"\"111241\",\"973\",\"236\",\"237\",\"500\"";
		String jsonString = "{" + "\n" +
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
		System.out.println(jsonToCsv(jsonString));
	}
}













