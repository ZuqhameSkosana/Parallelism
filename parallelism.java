/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author zuqhame
 */

public class parallelism implements/*extends*/ Runnable{
private Thread t_thread;

private SunData sun;

    public parallelism(SunData s) {
    sun=s;
    
    }

float num = 0.4f;

public void run(){
    //SunData sun=new SunData();
   // ForestPanel fr=new ForestPanel(sun.trees);
//use method to check tree layer inrange
//if extent of tree is in range call sungrow
//sungrow inreases extent of tree

//for all trees in a certain layer call sungrow on them
//for trees less than range decrease shade by 0.1

//for changing layers have two for loops
// first loop run from 0 to 10
// second loop goes through all trees 
int counting_c=0;
while(true){
float minh = 18.0f;
float maxh = 20.0f;
//Land n=new Land(sun.io, sun.ip);

Tree tr_ee;

//try{
//sun.readData("sample_input.txt");
TreeGrow khula=new TreeGrow();
for(int layer = 0; layer < 10; layer++) {
    khula.toString();
   for(int t = 0; t < sun.trees.length; t++){
    //   int x=sun.trees[t].getX();
      // int y=sun.trees[t].getY();
      // float v=sun.trees[t].getExt();
	//int rt = rndorder.get(t);
        tr_ee=sun.clone_tree[t];
        
        
        if(tr_ee.inrange(minh, maxh)==true){
            //tr_ee.setExt(tr_ee.getExt() + num);
            //num++;
           // System.out.println("inside thread");
            tr_ee.sungrow(sun.sunmap);//
            
            sun.sunmap.shadow(tr_ee);//
        
        }
	
		}
                        maxh =minh; //2.0f;
			minh = minh-2;  // next band of trees
                        
			//khula.timer+=khula.tock();
                        //if(khula.timer==3.1536E+16){
                            counting_c++;
                        //khula.field.setText(counting_c+"");
                      //  }
		}
}
//}catch(Exception e){
        //System.out.println("mxm "+e);
        //}
}
/*
public void start(){

    if(t_thread==null){
    t_thread=new Thread();
    t_thread.start();
    }
}
*/
}
    
    
