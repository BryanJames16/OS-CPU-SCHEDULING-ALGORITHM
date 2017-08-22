/**
	* <h1>Earliest Deadline Scheduling Algorithm</h1>
	* UserInterface Class
	* This file is a part of CPU_SCHEDULING_ALGORITHM project
	* 
	* @author Bryan James Torcelino Ilaga
	* @version 1.0
	* @since 02-18-2016
	* 
	* Section: BSIT 2-1D
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class 
UserInterface implements ActionListener
{
	
	int noOfJobs = 0;
	int jobNumber [];
	String queueType [] = new String [99];
	int algoNo [] = new int [99];
	int noOfQueue = 0;
	
	// grandData [][0] arrivalTime
	// grandData [][1] burstTime
	// grandData [][2] priority
	// grandData [][3] deadline
	// grandData [][4] exitTime
	// grandData [][5] totalTurn
	// grandData [][6] totalWait
	double grandData [][];
	
	Container contentPane;
	JFrame frame = new JFrame ("CPU Scheduling Algorithm");
	JPanel pnlCenter = new JPanel ();
	JPanel pnlBanner = new JPanel ();
	JPanel pnlButtons = new JPanel (new GridLayout (1, 2));
	JPanel pnlLeft = new JPanel (new GridLayout (5, 1));
	JPanel pnlRight = new JPanel (new GridLayout (5, 1));
	JPanel pnlBottom = new JPanel (new GridLayout (1, 1));
	
	JLabel lblBanner = new JLabel ("CPU Scheduling Algorithm");
	JButton btnFCFS = new JButton ("First Come First Serve");
	JButton btnSJF = new JButton ("Shortest Job First");
	JButton btnPrio = new JButton ("Priority");
	JButton btnPPrio = new JButton ("Preemptive Priority");
	JButton btnSRTF = new JButton ("Shortest Remaining Time First");
	JButton btnRR = new JButton ("Round Robin");
	JButton btnRRO = new JButton ("Round Robin with Overhead");
	JButton btnDeadline = new JButton ("Deadline");
	JButton btnMLQ = new JButton ("Multilevel Queue");
	JButton btnMLFQ = new JButton ("Multilevel with Feedback Queue");
	JButton btnGiven = new JButton ("Enter Given");
	
	Font bannerFont = new Font ("Consolas", Font.BOLD, 18);
	Font buttonFont = new Font ("Berlin Sans FB", Font.BOLD, 16);
	Font bottomFont = new Font ("Arial", Font.BOLD, 14);
	
	/**
		* Constructors
	*/
	/* Algorithm number 1 - 8 Constructor */
	protected 
	UserInterface (double grandData [][], int jobNumber [], int noOfJobs) 
	{
		this.grandData = new double [noOfJobs][ProcessConstants.GRAND_ARRAY];
		this.jobNumber = new int [noOfJobs];
		
		this.grandData = grandData;
		this.jobNumber = jobNumber;
		this.noOfJobs = noOfJobs;
	}
	
	/* Algorithm number 9 Constructor */
	protected 
	UserInterface (double grandData [][], int jobNumber [], int noOfJobs, String queueType [], int noOfQueue) 
	{
		this.grandData = new double [noOfJobs][ProcessConstants.GRAND_ARRAY];
		this.jobNumber = new int [noOfJobs];
		
		this.grandData = grandData;
		this.jobNumber = jobNumber;
		this.noOfJobs = noOfJobs;
		
		this.queueType = queueType;
		this.algoNo = new int [noOfJobs];
		this.noOfQueue = noOfQueue;
	}
	
	public void 
	launchFrame () 
	{
		
		contentPane = frame.getContentPane ();
		
		lblBanner.setFont (bannerFont);
		pnlBanner.add (lblBanner);
		pnlBanner.setBorder (BorderFactory.createEtchedBorder (EtchedBorder.RAISED, Color.BLACK, Color.BLACK));
		
		btnFCFS.setFont (buttonFont);
		btnSJF.setFont (buttonFont);
		btnPrio.setFont (buttonFont);
		btnPPrio.setFont (buttonFont);
		btnSRTF.setFont (buttonFont);
		btnRR.setFont (buttonFont);
		btnRRO.setFont (buttonFont);
		btnDeadline.setFont (buttonFont);
		btnMLQ.setFont (buttonFont);
		btnMLFQ.setFont (buttonFont);
		
		pnlLeft.add (btnFCFS);
		pnlLeft.add (btnSJF);
		pnlLeft.add (btnPrio);
		pnlLeft.add (btnPPrio);
		pnlLeft.add (btnSRTF);
		
		pnlRight.add (btnRR);
		pnlRight.add (btnRRO);
		pnlRight.add (btnDeadline);
		pnlRight.add (btnMLQ);
		pnlRight.add (btnMLFQ);
		
		pnlBottom.add (btnGiven);
		btnGiven.setFont (bottomFont);
		
		btnFCFS.addActionListener (this);
		btnSJF.addActionListener (this);
		btnPrio.addActionListener (this);
		btnPPrio.addActionListener (this);
		btnSRTF.addActionListener (this);
		btnRR.addActionListener (this);
		btnRRO.addActionListener (this);
		btnDeadline.addActionListener (this);
		btnMLQ.addActionListener (this);
		btnMLFQ.addActionListener (this);
		btnGiven.addActionListener (this);
		
		pnlButtons.add (pnlLeft);
		pnlButtons.add (pnlRight);
		
		contentPane.add (pnlBanner, BorderLayout.NORTH);
		contentPane.add (pnlButtons, BorderLayout.CENTER);
		contentPane.add (pnlBottom, BorderLayout.SOUTH);
		
		frame.setSize (800, 500);
		frame.setResizable (false);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setVisible (true);
		
	}
	
	public void 
	anchorUI () 
	{
		
	}
	
	@Override
	public void 
	actionPerformed (ActionEvent ae) 
	{
		if (ae.getSource() == btnGiven) {
			frame.dispose ();
			InputGiven ig = new InputGiven ();
			ig.launchFrame ();
		}
		if (ae.getSource () == btnFCFS) {
			frame.dispose ();
			FCFS fcfs = new FCFS (grandData, jobNumber, noOfJobs);
			fcfs.launchFrame ();
		}
		if (ae.getSource () == btnSJF) {
			frame.dispose ();
			SJF sjf = new SJF (grandData, jobNumber, noOfJobs);
			sjf.launchFrame ();
		}
		if (ae.getSource () == btnPrio) {
			frame.dispose ();
			PRIO prio = new PRIO (grandData, jobNumber, noOfJobs);
			prio.launchFrame ();
		}
		if (ae.getSource () == btnPPrio) {
			frame.dispose ();
			PPRIO pprio = new PPRIO (grandData, jobNumber, noOfJobs);
			pprio.launchFrame ();
		}
		if (ae.getSource () == btnSRTF) {
			frame.dispose ();
			SRTF srtf = new SRTF (grandData, jobNumber, noOfJobs);
			srtf.launchFrame ();
		}
		if (ae.getSource () == btnDeadline) {
			frame.dispose ();
			Deadline deadline = new Deadline (grandData, jobNumber, noOfJobs);
			deadline.launchFrame ();
		}
		if (ae.getSource () == btnRR) {
			
			double quantum = 0.00;
			
			try {
				quantum = Double.parseDouble (JOptionPane.showInputDialog (null, "Enter the time slice / quantum: ", "I N P U T", JOptionPane.INFORMATION_MESSAGE));
				if (quantum <= 0) {
					JOptionPane.showMessageDialog (null, "Invalid Quantum!", "E R R O R", JOptionPane.ERROR_MESSAGE);
				} else {
					frame.dispose ();
					RR rr = new RR (grandData, jobNumber, noOfJobs, quantum);
					rr.launchFrame ();
				}
			} catch (NumberFormatException nfe) {
				System.out.println ("Exception Caught: " + nfe);
				JOptionPane.showMessageDialog (null, "Invalid Quantum!", "E R R O R", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (ae.getSource () == btnRRO) {
			double quantum = 0.00;
			double overhead = 0.00;
			
			try {
				quantum = Double.parseDouble (JOptionPane.showInputDialog (null, "Enter the time slice / quantum: ", "I N P U T", JOptionPane.INFORMATION_MESSAGE));
				if (quantum <= 0) {
					JOptionPane.showMessageDialog (null, "Invalid Quantum!", "E R R O R", JOptionPane.ERROR_MESSAGE);
				} else {
					
					overhead = Double.parseDouble (JOptionPane.showInputDialog (null, "Enter the overhead: ", "I N P U T", JOptionPane.INFORMATION_MESSAGE));
					
					if (overhead <= 0) {
						JOptionPane.showMessageDialog (null, "Invalid Overhead!", "E R R O R", JOptionPane.ERROR_MESSAGE);
					} else {
						frame.dispose ();
						RRO rro = new RRO (grandData, jobNumber, noOfJobs, quantum, overhead);
						rro.launchFrame ();
					}
				}
			} catch (NumberFormatException nfe) {
				System.out.println ("Exception Caught: " + nfe);
				JOptionPane.showMessageDialog (null, "Invalid Input!", "E R R O R", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (ae.getSource () == btnMLQ) {
			/*
			int tempNumber [] = new int [3];
			boolean isCorrect = true;
			
			try {
				for (int count = 0; count < noOfQueue; count++) {
					String info = "Input the number of the algorithm number \nyou want to use for queue " + (count + 1) + ": \n" + 
										"1. FCFS\n"  + 
										"2. SJF\n" +
										"3. PRIO\n" +
										"4. Deadline\n";
										
					tempNumber[count] = Integer.parseInt (JOptionPane.showInputDialog (null, info, "INPUT", JOptionPane.INFORMATION_MESSAGE));
					
					if (tempNumber [count] > 4 || tempNumber [count] < 1) {
						JOptionPane.showMessageDialog (null, "Please enter a valid number!", "ERROR", JOptionPane.ERROR_MESSAGE);
						isCorrect = false;
					}
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog (null, "Please enter a valid number!", "ERROR", JOptionPane.ERROR_MESSAGE);
				isCorrect = false;
			}
			
			if (isCorrect) {
				for (int count = 0; count < noOfJobs; count++) {
					
					if (noOfQueue == 2) {
						if (queueType [count].equals ("High")) {
							algoNo [count] = tempNumber [0];
						} else {
							algoNo [count] = tempNumber [1];
						}
					} else {
						if (queueType [count].equals ("High")) {
							algoNo [count] = tempNumber [0];
						} else if (queueType [count].equals ("Mid")) {
							algoNo [count] = tempNumber [1];
						} else {
							algoNo [count] = tempNumber [2];
						}
					}
					
				}
			}
			frame.dispose ();
			MLQ mlq = new MLQ (grandData, jobNumber, noOfJobs, noOfQueue, queueType, algoNo);
			mlq.launchFrame ();
			*/
		}
		if (ae.getSource () == btnMLFQ) {
			
		}
	}
	
}