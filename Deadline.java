public class 
Deadline 
{
	int noOfJobs = 0;
	int jobNumber [];
	int orderOfJobs [];
	double grandData [][];
	double flagTime [];
	double ttAve = 0.00;
	double wtAve = 0.00;
	boolean idle = false;
	boolean processedJobs [];
	
	protected 
	Deadline (double grandData [][], int jobNumber [], int noOfJobs) 
	{
		this.grandData = new double [noOfJobs][ProcessConstants.GRAND_ARRAY];
		this.jobNumber = new int [noOfJobs];
		this.flagTime = new double [noOfJobs + 2];
		this.orderOfJobs = new int [noOfJobs];
		this.processedJobs = new boolean [noOfJobs];
		
		this.grandData = grandData;
		this.jobNumber = jobNumber;
		this.noOfJobs = noOfJobs;
		
		this.compute ();
	}
	
	private void 
	compute () 
	{	
		int counter = 0;
		int counter2 = 0;
		int orderCount = 0;
		double timer = 0.00;
		double totalBurst = 0.00;
		int lowestJobIndex = 0;
		
		/* Sort by Arrival Time */
		for (int count = 0; count < noOfJobs; count++) {
			for (int count2 = 0; count2 < noOfJobs - 1; count2++) {
				if (grandData [count2 + 1][0] < grandData [count2][0]) {
					double temp = 0.00;
					int tempInt = 0;
					
					tempInt = jobNumber [count2 + 1];
					jobNumber [count2 + 1] = jobNumber [count2];
					jobNumber [count2] = tempInt;
					
					temp = grandData [count2 + 1][0];
					grandData [count2 + 1][0] = grandData [count2][0];
					grandData [count2][0] = temp;
					
					temp = grandData [count2 + 1][1];
					grandData [count2 + 1][1] = grandData [count2][1];
					grandData [count2][1] = temp;
					
					temp = grandData [count2 + 1][2];
					grandData [count2 + 1][2] = grandData [count2][2];
					grandData [count2][2] = temp;
					
					temp = grandData [count2 + 1][3];
					grandData [count2 + 1][3] = grandData [count2][3];
					grandData [count2][3] = temp;
					
					temp = grandData [count2 + 1][4];
					grandData [count2 + 1][4] = grandData [count2][4];
					grandData [count2][4] = temp;
					
					temp = grandData [count2 + 1][5];
					grandData [count2 + 1][5] = grandData [count2][5];
					grandData [count2][5] = temp;
					
					temp = grandData [count2 + 1][6];
					grandData [count2 + 1][6] = grandData [count2][6];
					grandData [count2][6] = temp;
				}
			}
		} // for () sort arrival
		
		
		
		for (int count = 0; count < noOfJobs; count++) {
			if (grandData [count][3] == 0.00) {
				grandData [count][3] = 1000.00;
			}
		}
		
		/* Sort by Deadline */
		for (int count = 0; count < noOfJobs; count++) {
			for (int count2 = 0; count2 < noOfJobs - 1; count2++) {
				if (grandData [count2 + 1][0] == grandData [count2][0]) {
					if (grandData [count2 + 1][3] < grandData [count2][3]) {
						double temp = 0.00;
						int tempInt = 0;
						
						tempInt = jobNumber [count2 + 1];
						jobNumber [count2 + 1] = jobNumber [count2];
						jobNumber [count2] = tempInt;
						
						temp = grandData [count2 + 1][0];
						grandData [count2 + 1][0] = grandData [count2][0];
						grandData [count2][0] = temp;
						
						temp = grandData [count2 + 1][1];
						grandData [count2 + 1][1] = grandData [count2][1];
						grandData [count2][1] = temp;
						
						temp = grandData [count2 + 1][2];
						grandData [count2 + 1][2] = grandData [count2][2];
						grandData [count2][2] = temp;
						
						temp = grandData [count2 + 1][3];
						grandData [count2 + 1][3] = grandData [count2][3];
						grandData [count2][3] = temp;
						
						temp = grandData [count2 + 1][4];
						grandData [count2 + 1][4] = grandData [count2][4];
						grandData [count2][4] = temp;
						
						temp = grandData [count2 + 1][5];
						grandData [count2 + 1][5] = grandData [count2][5];
						grandData [count2][5] = temp;
						
						temp = grandData [count2 + 1][6];
						grandData [count2 + 1][6] = grandData [count2][6];
						grandData [count2][6] = temp;
					}
				}
			}
		} // for () sort deadline
		
		if (grandData [0][0] != 0) {
			idle = true;
			timer = grandData [0][0];
			
			flagTime [counter2] = 0.00;
			counter2++;
			flagTime [counter2] = timer;
			counter2++;
			
			System.out.println ("0.00\t____________");
			System.out.println ("    \t|   IDLE   |");
			System.out.println ("    \t|   " + String.format ("%.2f", grandData [0][0]) + "   |");
			System.out.println (String.format ("%.2f", grandData [0][0]) + "\t|__________|");
		} else {
			timer = 0.00;
			flagTime [counter2] = timer;
			counter2++;
			System.out.println ("0.00\t____________");
		}
		
		totalBurst = grandData [0][0];
		
		for (int count = 0; count < noOfJobs; count++) {
			totalBurst += grandData [count][1];
		}
		
		for (int count = 0; count < noOfJobs; count++) {
			processedJobs [count] = false;
		} 
		
		// Core
		if (idle) {
			for (timer = grandData [0][0]; timer < totalBurst; ) { 
				double lowestJobDeadline = 32767;
				
				for (int count = 0; count < noOfJobs; count++) {
					if (!processedJobs [count]) {
						if (grandData [count][3] < lowestJobDeadline) {
							if (grandData [count][0] <= timer) {
								lowestJobDeadline = grandData [count][3];
								lowestJobIndex = count;
							}
						}
					}
				}
				
				processedJobs [lowestJobIndex] = true;
				timer += grandData [lowestJobIndex][1];
				grandData [lowestJobIndex][4] = timer;
				flagTime [counter2] = timer;
				orderOfJobs [orderCount] = lowestJobIndex;
				
				System.out.println ("    \t|    " + String.format ("J %d", jobNumber [lowestJobIndex]) + "   |");
				System.out.println ("    \t|   " + String.format ("%.2f", grandData [counter][1]) + "   |");
				System.out.println (String.format ("%.2f", timer) + "\t|__________|");
				
				counter++;
				counter2++;
				orderCount++;
			}
		} else {
			for (timer = grandData [0][0]; timer < totalBurst; ) { 
				double lowestJobDeadline = 32767;
				
				for (int count = 0; count < noOfJobs; count++) {
					if (!processedJobs [count]) {
						if (grandData [count][3] < lowestJobDeadline) {
							if (grandData [count][0] <= timer) {
								lowestJobDeadline = grandData [count][3];
								lowestJobIndex = count;
							}
						}
					}
				}
				
				processedJobs [lowestJobIndex] = true;
				timer += grandData [lowestJobIndex][1];
				grandData [lowestJobIndex][4] = timer;
				flagTime [counter2] = timer;
				orderOfJobs [orderCount] = lowestJobIndex;
				
				System.out.println ("    \t|    " + String.format ("J %d", jobNumber [lowestJobIndex]) + "   |");
				System.out.println ("    \t|   " + String.format ("%.2f", grandData [counter][1]) + "   |");
				System.out.println (String.format ("%.2f", timer) + "\t|__________|");
				
				counter++;
				counter2++;
				orderCount++;
			}
		}
		
		for (int count = 0; count < noOfJobs; count++) {
			grandData [count][5] = grandData [count][4] - grandData [count][0];
			grandData [count][6] = grandData [count][5] - grandData [count][1];
			
			ttAve += grandData [count][5];
			wtAve += grandData [count][6];
		}
		
		ttAve /= noOfJobs;
		wtAve /= noOfJobs;
		
	}
	
	protected void 
	launchFrame() 
	{
		
		DrawGantt dg = new DrawGantt (grandData, jobNumber, ttAve, wtAve, flagTime, orderOfJobs, noOfJobs, idle);
		dg.launchFrame ();
		
	} // launchFrame ()
	
} // class Deadline