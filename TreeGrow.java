/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zuqhame
 */

import com.sun.media.jfxmedia.events.PlayerEvent;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class TreeGrow {
        static Tree t[];
	static long startTime = 0;
	static int frameX;
	static int frameY;
	static ForestPanel fp;
        static int year_counter = 0;
       
        
        static SunData sundata;
        static float timer=0;
        static JTextField txt_field;
        
        static JButton pause;
        static JButton play;
        static JButton end;
        static JButton reset;
        
        static boolean confirm=false;
        static thread param_;
       volatile static int counts_now=0;
        
	// start timer
	 static void tick(){
		startTime = System.currentTimeMillis();
	}
	
	// stop timer, return time elapsed in seconds
	 static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	
	public static void setupGUI(int frameX,int frameY,Tree [] trees) {
		Dimension fsize = new Dimension(800, 800);
		// Frame init and dimensions
    	JFrame frame = new JFrame("Photosynthesis"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setPreferredSize(fsize);
    	frame.setSize(800, 800);
        
    	
      	JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      	g.setPreferredSize(fsize);
 
		fp = new ForestPanel(trees);
		fp.setPreferredSize(new Dimension(frameX,frameY));
		JScrollPane scrollFrame = new JScrollPane(fp);
		fp.setAutoscrolls(true);
		scrollFrame.setPreferredSize(fsize);
	    g.add(scrollFrame);
        JPanel panel = new JPanel(new FlowLayout());
    	 reset = new JButton("reset");
	
       // reset.setEnabled(false);
        
       txt_field  = new JTextField("", 8);
        
        //--------------------------------------------------------------------------RESET BUTTON---------------------------------------------------------------------------
        reset.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        
                        
                       counts_now=0;
                       
                       txt_field.setText(""+counts_now);
                        
                        float new_extent=0.4f;
                         for (int i = 0; i < sundata.trees.length; i++) {
                        
                        
                            sundata.clone_tree[i].setExt(new_extent);
                            
                            
                        }
                         play.setEnabled(true);
                         
                        
                        if(confirm==true){
                        param_.suspend();
                        }
                         
                     
                       
                    }
        
        });
        
        panel.add(reset);
        
        //---------------------------------------------------------------------------PAUSE BUTTON---------------------------------------------------------------------------
        pause = new JButton("pause");
        pause.setEnabled(false);
         pause.addActionListener(new ActionListener() {
                   // private Object fjPool;
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        
                       
                      param_.pasuing_the_theads();
                       
                       
                    }
                });
        
        panel.add(pause);
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
        
        //-------------------------------------------------------------------------PLAY BUTTON-------------------------------------------------------------------------------
         play = new JButton("play");
         play.setEnabled(false);
          play.addActionListener(new ActionListener() {
                   // private Object fjPool;
                    @Override
                    public void actionPerformed(ActionEvent event) {
                       //checker=true;
                      param_=new thread(sundata);
                      param_.start();
                      pause.setEnabled(true);
                   
                     
                     confirm=true;
                     
                
                    }
                });
        
        panel.add(play);
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
        
        //-------------------------------------------------------------------------END BUTTON---------------------------------------------------------------------------------
         end = new JButton("end");
		
        end.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        int y=JOptionPane.showConfirmDialog(null, null, "do you really want to end simulation?", 0, 1);
                       
                        if(y==0){
                         System.exit(0);
                        }
                        
                        
                        
                       
                    }
                });
				
        panel.add(end);
        
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
        
        
        JLabel counter_label = new JLabel("Number of years: ");
        
        txt_field.setText(""+year_counter);
        panel.add(counter_label);
        panel.add(txt_field);
        g.add(panel);

      	frame.setLocationRelativeTo(null);  // Center window on screen.
      	frame.add(g); //add contents to window
        frame.setLayout(new BorderLayout());
        frame.setContentPane(g);     
        frame.setVisible(true);
        Thread fpt = new Thread(fp);
        fpt.start();
	}
	
        //---------------------------------------------------------------------------METHOD TO SET TREE EXTENT TO 0.4-------------------------------------------------------------------
        public static void update_to_0_4(String filename){
           
           
          float extent = 0.4f;
          sundata=new SunData();
          sundata.readData(filename);
          
                        
                        setupGUI(sundata.sunmap.getDimX(), sundata.sunmap.getDimY(), sundata.clone_tree);
                      
                 
        
        }
        
        //-----------------------------------------------------------------------------MAIN METHOD -----------------------------------------------------------------------------------------
		
	public static void main(String[] args) {
            String filenames=args[0];
            
		if(args.length != 1)
		{
			System.out.println("Incorrect number of command line arguments. Should have form: java treeGrow.java intputfilename");
			System.exit(0);
		}
				
		
		update_to_0_4(filenames);
                  
	}
}
