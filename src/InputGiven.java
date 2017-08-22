import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class 
InputGiven implements ActionListener
{
	
	int noOfJobs = 0;
	int jobNumber [];
	double grandData [][];
	
	int noOfQueue = 0;
	String [] queueType = new String [99];
	String [] queueType2 = new String [] {"High", "Low"};
	String [] queueType3 = new String [] {"High", "Mid", "Low"};
	
	Container contentPane;
	JFrame frame = new JFrame ("CPU Scheduling Algorithm: Input Given");
	JScrollPane scrollPane;
	
	JPanel pnlCenter = new JPanel ();
	JPanel pnlArray[] = new JPanel [99];
	JPanel pnlButton = new JPanel (new GridLayout (1, 4));
	
	JLabel lblJobNumberHead = new JLabel ("Job");
	JLabel lblArrivalTime = new JLabel ("Arrival Time");
	JLabel lblBurstTime = new JLabel ("Burst Time");
	JLabel lblPriority = new JLabel ("Priority");
	JLabel lblDeadline = new JLabel ("Deadline");
	JLabel lblQueue = new JLabel ("Queue");
	
	JLabel lblNumber[] = new JLabel [99];
	JTextField tfArrival[] = new JTextField [99];
	JTextField tfBurst[] = new JTextField [99];
	JTextField tfPrio[] = new JTextField [99];
	JTextField tfDeadline[] = new JTextField [99];
	
	JCheckBox cbPrio = new JCheckBox ("Include Priority");
	JCheckBox cbDeadline = new JCheckBox ("Include Deadline");
	JCheckBox cbQueue = new JCheckBox ("Include Queue");
	
	JComboBox <String> cboQueue [] = new JComboBox [99];
	
	JButton btnSubmit = new JButton ("Submit");
	JButton btnClear = new JButton ("Clear");
	JButton btnReset = new JButton ("Reset");
	JButton btnBack = new JButton ("Back");
	
	public void 
	launchFrame () 
	{
		
		/* Prompt the user to input the number of jobs to be processed */
		while (true) {
			String inputData = new String ("");
		
			inputData = JOptionPane.showInputDialog (null, "Please enter the number of jobs to be processed: ", "I N P U T", JOptionPane.INFORMATION_MESSAGE);
			try {
				if (inputData.equals(null)) {
					JOptionPane.showMessageDialog (null, "Please input a valid number!", "E R R O R", JOptionPane.ERROR_MESSAGE);
					
				} else {
					noOfJobs = Integer.parseInt (inputData);
				}
				
				if (noOfJobs < ProcessConstants.MIN_JOB) {
					JOptionPane.showMessageDialog (null, "Jobs input must greater than zero", "E R R O R", JOptionPane.ERROR_MESSAGE);
				} else {
					break;
				}
			} catch (NumberFormatException nfe) {
				System.err.println ("Exception Caught: " + nfe);
				JOptionPane.showMessageDialog (null, "Please enter a valid number!", "E R R O R", JOptionPane.ERROR_MESSAGE);
				
			} catch (NullPointerException npe) {
				System.err.println ("Exception Caught: " + npe);
				JOptionPane.showMessageDialog (null, "Invalid Input!", "E R R O R", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		contentPane = frame.getContentPane ();
		
		pnlCenter.setLayout (new GridLayout (noOfJobs + 5, 1));
		pnlArray = new JPanel [noOfJobs + 1];
		tfArrival = new JTextField [noOfJobs];
		tfBurst = new JTextField [noOfJobs];
		tfPrio = new JTextField [noOfJobs];
		tfDeadline = new JTextField [noOfJobs];
		cboQueue = new JComboBox [noOfJobs];
		
		pnlArray [0] = new JPanel (new GridLayout (1, 6));
		pnlArray [0].add (lblJobNumberHead);
		pnlArray [0].add (lblArrivalTime);
		pnlArray [0].add (lblBurstTime);
		pnlArray [0].add (lblPriority);
		pnlArray [0].add (lblDeadline);
		pnlArray [0].add (lblQueue);
		pnlCenter.add (pnlArray [0]);
		
		lblJobNumberHead.setHorizontalAlignment (SwingConstants.CENTER);
		lblArrivalTime.setHorizontalAlignment (SwingConstants.CENTER);
		lblBurstTime.setHorizontalAlignment (SwingConstants.CENTER);
		lblPriority.setHorizontalAlignment (SwingConstants.CENTER);
		lblDeadline.setHorizontalAlignment (SwingConstants.CENTER);
		
		for (int count = 1; count < noOfJobs + 1; count++) {
			pnlArray [count] = new JPanel (new GridLayout (1, 6));
			tfArrival [count - 1] = new JTextField ();
			tfBurst [count - 1] = new JTextField ();
			tfPrio [count - 1] = new JTextField ();
			tfDeadline [count - 1] = new JTextField ();
			cboQueue [count - 1] = new JComboBox <> ();
			
			tfPrio [count - 1].setEnabled (false);
			tfDeadline [count - 1].setEnabled (false);
			cboQueue [count - 1].setEnabled (false);
			
			lblNumber[count - 1] = new JLabel ("" + count);
			lblNumber[count - 1].setHorizontalAlignment (SwingConstants.CENTER);
			
			pnlArray [count].add (lblNumber [count - 1]);
			pnlArray [count].add (tfArrival [count - 1]);
			pnlArray [count].add (tfBurst [count - 1]);
			pnlArray [count].add (tfPrio [count - 1]);
			pnlArray [count].add (tfDeadline [count - 1]);
			pnlArray [count].add (cboQueue [count - 1]);
			
			pnlCenter.add (pnlArray [count]);
		}
		
		pnlButton.add (btnSubmit);
		pnlButton.add (btnClear);
		pnlButton.add (btnReset);
		pnlButton.add (btnBack);
		
		pnlCenter.add (cbPrio);
		pnlCenter.add (cbDeadline);
		pnlCenter.add (cbQueue);
		pnlCenter.add (pnlButton);
		
		btnSubmit.addActionListener (this);
		btnClear.addActionListener (this);
		btnReset.addActionListener (this);
		btnBack.addActionListener (this);
		cbPrio.addActionListener (this);
		cbDeadline.addActionListener (this);
		cbQueue.addActionListener (this);
		
		scrollPane = new JScrollPane (pnlCenter);
		
		contentPane.add (scrollPane, BorderLayout.CENTER);
		
		frame.setSize (500, 300);
		frame.setResizable (false);
		frame.setTitle ("CPU Scheduling Algorithm: Input Given");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setVisible (true);
		
	}
	
	@Override
	public void 
	actionPerformed (ActionEvent ae) 
	{
		if (ae.getSource () == btnSubmit) {
			
			boolean isValid = true;
			
			jobNumber = new int [noOfJobs];
			grandData = new double [noOfJobs][ProcessConstants.GRAND_ARRAY];
			
			for (int count = 0; count < noOfJobs; count++) {
				jobNumber [count] = count + 1;
				
				try {
					
					if (tfArrival [count].getText ().equals (null) || tfBurst [count].getText ().equals (null) ) {
						if ((cbPrio.isSelected() && tfPrio [count].getText ().equals (null)) || (cbPrio.isSelected() && tfPrio [count].getText ().equals (""))) {
							JOptionPane.showMessageDialog (null, "Please enter a valid Priority!", "E R R O R", JOptionPane.ERROR_MESSAGE);
							isValid = false;
							break;
						} else if ((cbDeadline.isSelected() && tfDeadline [count].getText ().equals (null)) || (cbDeadline.isSelected() && tfDeadline [count].getText ().equals (""))) {
							JOptionPane.showMessageDialog (null, "Please enter a valid Deadline!", "E R R O R", JOptionPane.ERROR_MESSAGE);
							isValid = false;
							break;
						} else {
							JOptionPane.showMessageDialog (null, "Please enter a valid number!", "E R R O R", JOptionPane.ERROR_MESSAGE);
							isValid = false;
							break;
						}
					} else {
						grandData [count][0] = Double.parseDouble (tfArrival [count].getText ());
						grandData [count][1] = Double.parseDouble (tfBurst [count].getText ());
						if (cbPrio.isSelected()) {
							grandData [count][2] = Double.parseDouble (tfPrio [count].getText ());
						}
						if (cbDeadline.isSelected()) {
							String data = tfDeadline [count].getText ();
							
							if (data.equals (null) || data.equals ("") || data.equals (" ") || data.equals ("0") || data.equals ("-") || data.equals ("--") || data.equals ("---")) {
								grandData [count][3] = 0.00;
							} else {
								grandData [count][3] = Double.parseDouble (data);
							}
						}
					}
				} catch (NumberFormatException nfe) {
					System.err.println ("Exception Caught: " + nfe);
					JOptionPane.showMessageDialog (null, "Please enter a valid number!", "E R R O R", JOptionPane.ERROR_MESSAGE);
					isValid = false;
					break;
				} catch (NullPointerException npe) {
					System.err.println ("Exception Caught: " + npe);
					JOptionPane.showMessageDialog (null, "Please enter a valid number!", "E R R O R", JOptionPane.ERROR_MESSAGE);
					isValid = false;
					break;
				}
				
				if (!isValid) {
					return;
				}
			}
			
			if (cbQueue.isSelected()) {
				
				for (int count = 0; count < noOfJobs; count++) {
					queueType [count] = new String ("");
					if (noOfQueue == 2) {
						if (cboQueue[count].getSelectedIndex() == 0) {
							queueType [count] = "High";
						} else {
							queueType [count] = "Low";
						}
					} else {
						if (cboQueue[count].getSelectedIndex() == 0) {
							queueType [count] = "High";
						} else if (cboQueue[count].getSelectedIndex() == 1) {
							queueType [count] = "Mid";
						} else {
							queueType [count] = "Low";
						}
					}
				}
				
				frame.dispose ();
				UserInterface ui = new UserInterface (grandData, jobNumber, noOfJobs, queueType, noOfQueue);
				ui.launchFrame ();
			} else {
				frame.dispose ();
				UserInterface ui = new UserInterface (grandData, jobNumber, noOfJobs);
				ui.launchFrame ();
			}
		}
		if (ae.getSource () == btnClear) {
			for (int count = 0; count < noOfJobs; count++) {
				tfArrival [count].setText ("");
				tfBurst [count].setText ("");
				tfPrio [count].setText ("");
				tfDeadline [count].setText ("");
			}
		}
		if (ae.getSource () == btnReset) {
			for (int count = 0; count < noOfJobs; count++) {
				tfArrival [count].setText ("");
				tfBurst [count].setText ("");
				tfPrio [count].setText ("");
				tfDeadline [count].setText ("");
			}
			
			cbPrio.setSelected (false);
			cbDeadline.setSelected (false);
			cbQueue.setSelected (false);
			
			frame.dispose ();
			InputGiven ig = new InputGiven ();
			ig.launchFrame ();
		}
		if (ae.getSource () == btnBack) {
			frame.dispose ();
			UserInterface ui = new UserInterface ();
			ui.launchFrame ();
		}
		if (ae.getSource () == cbPrio) {
			if (cbPrio.isSelected()) {
				for (int count = 0; count < noOfJobs; count++) {
					tfPrio [count].setEnabled (true);
				}
			} else {
				for (int count = 0; count < noOfJobs; count++) {
					tfPrio [count].setEnabled (false);
				}
			}
		}
		if (ae.getSource () == cbDeadline) {
			if (cbDeadline.isSelected()) {
				for (int count = 0; count < noOfJobs; count++) {
					tfDeadline [count].setEnabled (true);
				}
			} else {
				for (int count = 0; count < noOfJobs; count++) {
					tfDeadline [count].setEnabled (false);
				}
			}
		}
		if (ae.getSource () == cbQueue) {
			if (cbQueue.isSelected ()) {
				
				try {
					int queueNumber = Integer.parseInt (JOptionPane.showInputDialog (null, "Please enter the numbner of queue (2 / 3):", "Queue", JOptionPane.INFORMATION_MESSAGE));
					if (queueNumber < 2 || queueNumber > 3) {
						JOptionPane.showMessageDialog (null, "Queue Must Only Be 2 Or 3", "ERROR", JOptionPane.ERROR_MESSAGE);
					} else {
						if (queueNumber == 2) {
							for (int count = 0; count < noOfJobs; count++) {
								cboQueue [count].addItem ("High");
								cboQueue [count].addItem ("Low");
								cboQueue [count].setEnabled (true);
							}
						} else {
							for (int count = 0; count < noOfJobs; count++) {
								cboQueue [count].addItem ("High");
								cboQueue [count].addItem ("Mid");
								cboQueue [count].addItem ("Low");
								cboQueue [count].setEnabled (true);
							}
						}
						
						noOfQueue = queueNumber;
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog (null, "Please enter a valid number!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				for (int count = 0; count < noOfJobs; count++) {
					cboQueue [count].removeAllItems();
					cboQueue [count].setEnabled (false);
				}
				noOfQueue = 0;
			}
		}
	}
	
}