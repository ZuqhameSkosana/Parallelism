/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zuqhame
 */


public class thread extends Thread{
    
    
    public SunData sun;
    TreeGrow tgr=new TreeGrow();
    volatile boolean runs_ = true;   volatile boolean is_thread_paused = false;   final Object pasue_thread_lock = new Object();    
     
   
    public thread(SunData sundata){
    
        sun = sundata;
        
    }
    
    
  
     
      public void pasuing_the_theads() {
        
        is_thread_paused = true;
      //  counter=keeper;
        
    }

   
    
    @Override
    public void run(){
        
        while(runs_){
            
            float minh = 18.0f;
            float maxh = 20.0f;
            
            synchronized(pasue_thread_lock){
                    if (!runs_) { 
                    break;
                }
                if (is_thread_paused) {
                    try {
                        pasue_thread_lock.wait(); 
                    } catch (InterruptedException ex) {
                        break;
                    }
                    if (!runs_) { 
                        break;
                    }
                }}
            
                      int i=0;
                    while(i<10) {
                     
                
                           int j=0;
                           while(j < sun.clone_tree.length){
                               if(sun.clone_tree[j].inrange(minh, maxh)){

                                   sun.clone_tree[j].sungrow(sun.sunmap);

                               }
                             j++;

                            }
                            i++;
                            maxh = minh;  // next band of trees
                            minh =minh- 2.0f;
                            
                    }	
                    
                    
                //    System.out.println("year: " + tgr.counts_now);
            try {
                Thread.sleep((long)0.5);
            } catch (InterruptedException ex) {
                Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
            }
                    tgr.txt_field.setText(tgr.counts_now+"");
                    tgr.counts_now++;
                   
                }
        
    }
    
    
        
        
   
    
}
