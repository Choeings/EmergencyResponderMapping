import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EmerTraversal {

	private static int numRows;
	private static int numCols;
	private static int rs;
	private static char floodType;
	private static int r1;
	private static int c1;
	private static int r2;
	private static int c2;
	
	
	
	
    public static void main(String[] args) {
        

       
        char[][] map = read(args[0]);
        printMap(map);
        System.out.println("Rainfall Severity: ");
        
        Scanner scan = new Scanner(System.in);
        
        rs = scan.nextInt();
        
        
       findOrigin(map);
        
        printArray(map);
        
        System.out.println("Row Coordinate of Vehicles:");
        	r1 = scan.nextInt();
        System.out.println("Column Coordinate of Vehicles:");
          c1 = scan.nextInt();
        System.out.println("Row Coordinate of Emergency:");
          r2 = scan.nextInt();
        System.out.println("Column Coordinate of Emergency:");
        	c2 = scan.nextInt();
    
    boolean[][] check = new boolean[numRows][numCols];
    boolean p = findPath(map, check, r1, c1, r2, c2);

       
        if (p) {
            System.out.println("A safe pathway exists.");
        } else {
            System.out.println("No safe pathway exists.");
        }
    
        	
        	
    	
    }

    public static char[][] read(String filename) {
        char[][] map = null;
        try {
        	File fileIn = new File(filename);
            Scanner sc = new Scanner(fileIn);

           
             numRows = sc.nextInt();
            numCols = sc.nextInt();
            sc.nextLine();
            map = new char[numRows][numCols];

      
          	
            for (int i = 0; i < map.length; i++) {
            	String lineIn = sc.nextLine();
          		Scanner lineReader = new Scanner(lineIn);
          		lineReader.useDelimiter(",");
            
                for (int j =0; j < map[i].length; j++) {
                	if(lineReader.hasNext()){
                	String input = lineReader.next();
                    map[i][j] = input.charAt(0);
            		}
            }
            
            
            }
            
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error:" + filename);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return map;
    }

    public static void printMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    } 
    
    public static void findOrigin(char[][] map){
 
    	for(int i =0; i<map.length; i++){
    		for(int j=0; j<map[0].length; j++){
    				if(map[i][j] =='R'){
    					flood(map,i,j,rs,'R');
    					}
    			}
    	}
    	for(int i =0; i<map.length; i++){
    		for(int j=0; j<map[0].length; j++){
    				if(map[i][j] =='L'){
    					flood(map,i,j,rs,'L');
    				
    					}
    					
    	
    			}
    	
    	
    		}   
    } 
    
    
    
    
    
    

    	
		 private static void flood(char[][] map,int row, int col , int rs,char floodType){
			if(floodType=='R'){
			
				if(row+1 < map.length && map[row+1][col] !='R' && map[row+1][col] !='L' && map[row+1][col] -48 <=rs){
					map[row+1][col] = 'R';
    				flood(map,row+1,col,rs,floodType);
    				
    					}	
    			if(row-1 >=0 && map[row-1][col] !='R' && map[row-1][col] !='L' && map[row-1][col]-48 <= rs){
    					map[row-1][col] = 'R';
    					flood(map,row-1,col,rs,floodType);
    					
    					}
    			if(col+1 < map[row].length && map[row][col+1] !='R' && map[row][col+1] !='L' && map[row][col+1]-48 <= rs){
    					map[row][col+1] = 'R';
    					flood(map,row,col+1,rs,floodType);
    					
    					}
    				if(col-1 >= 0 && map[row][col-1] !='R' && map[row][col-1] !='L' && map[row][col-1] -48 <= rs){
    					map[row][col-1] = 'R';
    					flood(map,row,col-1,rs,floodType);
    					
    						}
    			}
    					    				
		
		
    		
    	if(floodType == 'L' ){
    		

    			if(row+1 < map.length && (map[row+1][col] !='R' && map[row+1][col] !='L' )&& ((map[row +1][col]-48)%2 == 0) && map[row+1][col] <= rs){
    				map[row+1][col] = 'L';
    				flood(map,row+1,col,rs,floodType);
    				
    				}	
    			if(row-1 >= 0 && (map[row-1][col] !='R' && map[row-1][col] !='L' )&& ((map[row-1][col]-48)%2 == 0) && map[row-1][col] <= rs){
    					map[row-1][col] = 'L';
    					flood(map,row-1,col,rs,floodType);
    					
    				}
    			if(col+1 < map[row].length && (map[row][col+1] !='R' && map[row][col+1] !='L') && ((map[row][col+1]-48)%2 == 0) && map[row][col+1] <= rs){
    			
    				map[row][col+1] = 'L';
    				flood(map,row,col+1,rs,floodType);
    				
    				}
    			if(col-1 >= 0 && (map[row][col-1] !='R' && map[row][col-1] !='L') && ((map[row][col-1]-48)%2 == 0) && map[row][col-1] <= rs){
    					map[row][col-1] = 'L';
    					flood(map,row,col-1,rs,floodType);
    				
    						}
    					}
    		}
    		
    public static void printArray(char[][] map){
    	for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
    
    
    	}
    	
    	// boolean to check if the postion is already searched
    	public static boolean findPath(char[][] map , boolean[][] check, int r1, int c1, int r2, int c2){
    		boolean toReturn = false;
    		
    		if(r1 == r2 && c1 == c2){
    				return toReturn = true;
    			}
    		check[r1][c1] = true;
    		// first or the current position of the vehicle is checked
    			
    		if(r1 +1 < map.length && !check[r1+1][c1] && map[r1+1][c1] !='R' && map[r1+1][c1] !='L'){
    				if(findPath(map,check,r1+1,c1,r2,c2)){
    					return toReturn =true;
    					}
    				}
    		if(r1 -1 >= 0 && !check[r1-1][c1] && map[r1-1][c1] !='R' && map[r1-1][c1] !='L'){
    				if(findPath(map,check,r1-1,c1,r2,c2)){
    					return toReturn =true;
    						}
    					}
    		
    		if(c1 +1 < map[r1].length && !check[r1][c1+1] && map[r1][c1+1] !='R' && map[r1][c1+1] !='L'){
    				if(findPath(map,check,r1,c1+1,r2,c2)){
    					return toReturn =true;
    		
    					}
    					
    				}
    		if(c1 -1 >=0 && !check[r1][c1-1] && map[r1][c1-1] !='R' && map[r1][c1-1] !='L'){
    				if(findPath(map,check,r1,c1-1,r2,c2)){
    					return toReturn = true;
    						}
    					}
    				return toReturn;	
    				
    	}
    		  	  
}

 






