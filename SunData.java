/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author zuqhame
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

// Trees define a canopy which covers a square area of the landscape
public class SunData{
	
	Land sunmap; // regular grid with average daily sunlight stored at each grid point
	
        Tree [] trees; // array of individual tress located on the sunmap	
        Tree [] clone_tree;
        
        static int numTt=0;
	int io=0;
        int ip=0;
	// read in sun exposure and tree data from file
	void readData(String fileName){ 
		try{ 
			Scanner sc = new Scanner(new File(fileName)); //reading from file given
			
			// load sunmap
			int dimx = sc.nextInt(); // first (x) value of terrif
			int dimy = sc.nextInt();  //seconds (y) value of terrif
			
                        //io=dimx;
                        //ip=dimy;
                        
                        sunmap = new Land(dimx,dimy);  
			
                        for(int x = 0; x < dimx; x++)
				for(int y = 0; y < dimy; y++) {
					sunmap.setFull(x,y,sc.nextFloat());	//this adds data to the setfull (to 2d array)
				}
			sunmap.resetShade(); 
			
			// load forest
			int num_of_trees = sc.nextInt();
                        numTt=num_of_trees;
                        
			trees = new Tree[num_of_trees];
                        clone_tree=new Tree[num_of_trees];
                        
			for(int t=0; t < num_of_trees; t++)  //get the data for all the trees which in this case is 1 000 000 trees
			{
				int xloc = sc.nextInt();
				int yloc = sc.nextInt();
				float ext = (float) sc.nextInt();
                                
				trees[t] = new Tree(xloc, yloc, ext);  
                                clone_tree[t]=new Tree(xloc, yloc, ext); 
			}
			sc.close(); 
		} 
		catch (IOException e){ 
			System.out.println("Unable to open input file "+fileName);
			e.printStackTrace();
		}
		catch (java.util.InputMismatchException e){ 
			System.out.println("Malformed input file "+fileName);
			e.printStackTrace();
		}
	}
        
        
        
	
	// write tree data to file
	void writeData(String fileName){
		 try{ 
			 FileWriter fileWriter = new FileWriter(fileName);
			 PrintWriter printWriter = new PrintWriter(fileWriter);
			 printWriter.printf("%d\n", trees.length);
			 for(int t=0; t < trees.length; t++)
				 printWriter.printf("%d %d %f\n", trees[t].getX(), trees[t].getY(), trees[t].getExt());
			 printWriter.close();
		 }
		 catch (IOException e){
			 System.out.println("Unable to open output file "+fileName);
				e.printStackTrace();
                                
		 }
	}
	
}
