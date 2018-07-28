import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dataStructures.TriangleBinaryTree;

public class Main {

	public static void main(String[] args) {
		
		int triangleSize = 0;
		int[][] triangle = null;
				    
		String triangleFilePathName = "triangle.txt";
		
		triangleSize = getTriangleSizeFromFile(triangleSize, triangleFilePathName);
	      
	    triangle = new int[triangleSize][triangleSize];
		
		triangle = constructTriangleFromFile(triangle, triangleFilePathName);
		
		int maxSum = findMaxSumInTriangle(triangle);
		
		System.out.println("Max Sum: " + maxSum);
	}



	private static int findMaxSumInTriangle(int[][] triangle) {
		int[][] path = new int[triangle.length-1][triangle.length];

		int[][] sumTriangle = new int[triangle.length][triangle.length];
		
		copyArray(triangle, sumTriangle);
		
		int k = 0;
		for (int i=triangle.length-2; i>=0; i--) {
			
			for (int j=0; j<=i; j++) {
				
				if ((isEvenNumber(triangle[i][j]))
					 &&(!isEvenNumber(triangle[i+1][j]))
					 &&(!isEvenNumber(triangle[i+1][j+1]))) {
					
					sumTriangle[i][j] += Math.max(sumTriangle[i+1][j], sumTriangle[i+1][j+1]);
					
					path[k][i+1] = Math.max(triangle[i+1][j], triangle[i+1][j+1]);
					k++;
					
				}
				else if ((!isEvenNumber(triangle[i][j]))
						 &&(isEvenNumber(triangle[i+1][j]))
						 &&(isEvenNumber(triangle[i+1][j+1]))) {
						
					sumTriangle[i][j] += Math.max(sumTriangle[i+1][j], sumTriangle[i+1][j+1]);
					
					path[k][i+1] = Math.max(triangle[i+1][j], triangle[i+1][j+1]);
					k++;
				}
				else if ((isEvenNumber(triangle[i][j]))
						 &&(!isEvenNumber(triangle[i+1][j]))
						 &&(isEvenNumber(triangle[i+1][j+1]))) {
					
					
					sumTriangle[i][j] += sumTriangle[i+1][j];
					
					path[k][i+1] = triangle[i+1][j];
					k++;
				}
				else if ((isEvenNumber(triangle[i][j]))
						 &&(isEvenNumber(triangle[i+1][j]))
						 &&(!isEvenNumber(triangle[i+1][j+1]))) {
					
					
					sumTriangle[i][j] += sumTriangle[i+1][j+1];
					
					path[k][i+1] = triangle[i+1][j+1];
					k++;
				}
				else if ((!isEvenNumber(triangle[i][j]))
						 &&(!isEvenNumber(triangle[i+1][j]))
						 &&(isEvenNumber(triangle[i+1][j+1]))) {
						
					sumTriangle[i][j] += sumTriangle[i+1][j+1];
					
					path[k][i+1] = triangle[i+1][j+1];
					k++;
				}
				else if ((!isEvenNumber(triangle[i][j]))
						 &&(isEvenNumber(triangle[i+1][j]))
						 &&(!isEvenNumber(triangle[i+1][j+1]))) {
						
					sumTriangle[i][j] += sumTriangle[i+1][j];
					
					path[k][i+1] = triangle[i+1][j];
					k++;
				}
				else {
					path[k][i+1] = -1;
					k++;
				}
				
			}	
			k=0;
			
		}
		
		int maxSum = sumTriangle[0][0];
		return maxSum;
	}



	private static void copyArray(int[][] source, int[][] destination) {
		for (int i=0; i<source.length; i++) {
			
			for (int j=0; j<source.length; j++) {
				
				destination[i][j] = source[i][j];
				
			}
			
		}
	}



	private static int[][] constructTriangleFromFile(int[][] triangle, String filePath) {
		
		File triangleFile = new File(filePath);
		
		BufferedReader bufReader;
		try {
		    String line;
		    
		    bufReader = new BufferedReader(new FileReader(triangleFile));
		    int i = 0;
		    while ((line = bufReader.readLine()) != null) {
		        String[] triangleElementsValues = line.split(" ");
		        
		        int j = 0;
		        for(String value: triangleElementsValues) {
		        	
		        	triangle[i][j] = Integer.parseInt(value);		   
		        	j++;
		        }
		        i++;
		    }
		} catch (IOException e) {

			e.printStackTrace();
		}
		return triangle;
	}



	private static int getTriangleSizeFromFile(int triangleSize, String filePath) {
		
		File triangleFile = new File(filePath);
		
		BufferedReader bufReader = null;
		try {
			bufReader  = new BufferedReader(new FileReader(triangleFile));
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		
		try {
			while ((bufReader.readLine()) != null) {
				triangleSize++;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return triangleSize;
	}



	private static boolean isEvenNumber(int number) {

		return number % 2 == 0;
		
	}
	
}
