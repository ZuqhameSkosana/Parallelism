/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author zuqhame
 */


public class Land{
	
	// to do
	// sun exposure data here
        AtomicInteger iu=new AtomicInteger(0);
        
        public float twoD_arr[][];
        int ii=0;
        int ib=0;
        public float v=0;
        
	static float shadefraction = 0.1f; // only this fraction of light is transmitted by a tree
        static float clode_twoD_arr[][];
	Land(int dx, int dy) {
		// to do
                ii=dx;  // this value is returned in the getDimX method
                ib=dy;  // this value is returned in the getDimY method
                twoD_arr=new float[dx][dy]; //initialize 2d array
                clode_twoD_arr=twoD_arr;
	}

	// return the number of landscape cells in the x dimension
    synchronized int getDimX() {
		// to do
                
		return twoD_arr.length; // 
	}
	
	// return the number of landscape cells in the y dimension
      synchronized int getDimY() {
		// to do
               // int m=twoD_arr[0].length;
		return twoD_arr[0].length; // incorrect value
	}
	
	// Reset the shaded landscape to the same as the initial sun exposed landscape
	// Needs to be done after each growth pass of the simulator
	 
        synchronized void resetShade() {
		// to do
                for (int i = 0; i <getDimY() ; i++) {
                    for (int j = 0; j < getDimX(); j++) {
                        twoD_arr[i][j]=clode_twoD_arr[i][j];
                    }
            }
              
                
             //  twoD_arr= clode_twoD_arr;
	}
	
	// return the sun exposure of the initial unshaded landscape at position <x,y?
	synchronized float getFull(int x, int y) {
		// to do
         float num=clode_twoD_arr[x][y];   //========================================================================================
	 return num; // incorrect value
	}
	
	// set the sun exposure of the initial unshaded landscape at position <x,y> to <val>
	synchronized void setFull(int x, int y, float val) {
		// to do 
                //float the_value=val;
                twoD_arr[y][x]=val;      //add data into this array  --add val into the position x y given
                clode_twoD_arr[y][x]=val;
	}
	
	// return the current sun exposure of the shaded landscape at position <x,y>
	synchronized float getShade(int x, int y) {
		// to do 
                float current_sun_exposure=twoD_arr[x][y]; //=======================================================================================
                
		return current_sun_exposure; // incorrect value
	}
	
	// set the sun exposure of the shaded landscape at position <x,y> to <val>
        
	synchronized void setShade(int x, int y, float val){  //call this method to keep on updating the values after getting the average of a tree
		// to do
                float set_current_expoure=val;
                
                twoD_arr[x][y]=set_current_expoure;  //===================================================================================================
               
                
	}
	
	// reduce the sun exposure of the shaded landscape to 10% of the original
	// within the extent of <tree>
	synchronized void shadow(Tree tree){
		// to do
                float reduction=0;
                int x=tree.getX();
                int y=tree.getY();
                
                int new_x_s=x-(int)Math.ceil(tree.getExt());  //get co-ordinates of the centre and minus the extent
                int new_y_s=y-(int)Math.ceil(tree.getExt());  //get co-ordinates of the centre and minus the extent

             
                
                if(new_x_s<0){
                new_x_s=0;
                }
                
                if(new_y_s<0){
                new_y_s=0;
                }
                
                
                for(int i=new_x_s;i < (x+Math.ceil(tree.getExt())+1) ;i++){
                    //try{
                for(int j=new_y_s;j < (y+Math.ceil(tree.getExt())+1)  ;j++){
                 
                   if( ( i<getDimX()  && j<getDimY()) ){
                     
                    reduction=getShade(x, y)-((10/100)*getShade(i, j));
                    setShade( i , j , reduction);
                   
                   }
                    
                    }
                   
                }
                
	}
}
