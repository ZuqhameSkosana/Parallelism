/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author zuqhame
 */

// Trees define a canopy which covers a square area of the landscape

public class Tree{
	
private
	int xpos;	// x-coordinate of center of tree canopy
	int ypos;	// y-coorindate of center of tree canopy
	float ext;	// extent of canopy out in vertical and horizontal from center
	
	static float growfactor = 1000.0f; // divide average sun exposure by this amount to get growth in extent
	
        
        
public	
	Tree(int x, int y, float e){
		xpos=x; ypos=y; ext=e;
	}
	
	synchronized int getX() {
		return xpos;
	}
	
	synchronized int getY() {
		return ypos;
	}
	
	synchronized float getExt() {
		return ext;
	}
	
	synchronized void setExt(float e) {
		ext = e;
	}

	// return the average sunlight for the cells covered by the tree
	synchronized float sunexposure(Land land){
		// to do 
                float average_to_be_returned=0;
                
                int x=getX();
                int y=getY();
                
                int new_x_s=x-(int)(getExt());  //get co-ordinates of the centre and minus the extent
                int new_y_s=y-(int)(getExt());  //get co-ordinates of the centre and minus the extent

                int c=0;
                
                if(new_x_s<0){
                new_x_s=0;
                }
                
                if(new_y_s<0){
                new_y_s=0;
                }
                
               
                for(int i=new_x_s;i < (x+Math.ceil(getExt())+1) ; i++){
                for(int j=new_y_s;j < (y+Math.ceil(getExt())+1) ; j++){
                     
                    //
                    if( ( i<land.getDimX() && j<land.getDimY() ) ){
                    //System.out.println("i = " + i + " j = " +j); 
                    average_to_be_returned+=land.getShade(i,j);  //get the value which are kept on updated
                    c=c+1;
                    }
               
                }
                }
                
                average_to_be_returned=average_to_be_returned/c;/*size_of_tree*/;
                
		return average_to_be_returned; // not correct
	}
	
	// is the tree extent within the provided range [minr, maxr)
	synchronized boolean inrange(float minr, float maxr) {
		return (ext >= minr && ext < maxr);
	}
	
	// grow a tree according to its sun exposure
	synchronized void sungrow(Land land) {
		// to do
                float one=getExt();
                float updating=(sunexposure(land)/1000);
                //System.out.println(updating);
                 one=one+(updating); //this gives the new value
                 
                 setExt(one);  //set to new extent , grow with the given growing factor.
                
                land.shadow(this);
                
	}
}

// threeGrow -controller 
// forest panel view -visualization
// land and tree and thread(updates controller) models carry data
