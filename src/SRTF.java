public class 
SRTF
{
	
	int noOfJobs = 0;
	int orderCount = 0;
	int jobNumber [];
	int orderOfJobs [];
	int oldJobIndex = 99;
	double orderOfBursts [];
	double burstCopy [];
	double grandData [][];
	double flagTime [];
	double decrementor = 0.00;
	double ttAve = 0.00;
	double wtAve = 0.00;
	boolean idle = false;
	boolean processedJobs [];
	
	protected 
	SRTF (double grandData [][], int jobNumber [], int noOfJobs) 
	{
		this.grandData = new double [noOfJobs][ProcessConstants.GRAND_ARRAY];
		this.jobNumber = new int [noOfJobs];
		this.flagTime = new double [99];
		this.orderOfJobs = new int [99];
		this.orderOfBursts = new double [99];
		this.processedJobs = new boolean [noOfJobs];
		this.burstCopy = new double [noOfJobs];
		
		this.grandData = grandData;
		this.jobNumber = jobNumber;
		this.noOfJobs = noOfJobs;
		
		for (int count = 0; count < noOfJobs; count++) {
			this.burstCopy [count] = grandData [count][1];
		}
		
		this.compute ();
	} 
	
	private void 
	compute () 
	{
		int counter = 0;
		int counter2 = 0;
		double timer = 0.00;
		int lowestJobIndex = 0;
		double totalBurst = 0.00;
		boolean isExhausted = false;
		
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
					
					temp = burstCopy [count2 + 1];
					burstCopy [count2 + 1] = burstCopy [count2];
					burstCopy [count2] = temp;
				}
			}
		} // for () sort arrival
		
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
		for (timer = grandData [0][0]; timer < totalBurst; timer += 1.00 ) { 
			double lowestJobBurst = ProcessConstants.MAX_BURST;
			lowestJobIndex = ProcessConstants.MIN_INDEX;
			
			for (int count = 0; count < noOfJobs; count++) {
				if (!processedJobs [count] && (burstCopy [count] != 0)) {
					if (grandData [count][1] < lowestJobBurst) {
						if (grandData [count][0] <= timer) {
							lowestJobBurst = grandData [count][1];
							lowestJobIndex = count;
						}
					}
				}
			}
			
			if (isExhausted) {
				oldJobIndex = lowestJobIndex;
				isExhausted = false;
			}
			
			// If highest job is not the defending champion
			if (lowestJobIndex != oldJobIndex) {
				if (oldJobIndex == 99) {
					oldJobIndex = lowestJobIndex;
					
					if ((decrementor + 1) - burstCopy [oldJobIndex] == 0) {
						processedJobs [oldJobIndex] = true;
						burstCopy [oldJobIndex] -= decrementor;
						grandData [oldJobIndex][4] = timer;
						
						System.out.println ("    \t|    " + String.format ("J %d   |", jobNumber [oldJobIndex]));
						System.out.println ("    \t|   " + String.format ("%.2f   |", (decrementor + 1)));
						System.out.println (String.format ("%.2f", timer) + "\t|__________|");
						
						orderOfBursts [orderCount] = decrementor + 1;
						orderOfJobs [orderCount] = oldJobIndex;
						orderCount++;
						
						decrementor = 0;
						
						flagTime [counter2] = timer;
						counter2++;
					}
				} else {
					decrementor += 1;
					if (decrementor - burstCopy [oldJobIndex] == 0) {
						processedJobs [oldJobIndex] = true;
						burstCopy [oldJobIndex] -= decrementor;
						grandData [oldJobIndex][4] = timer;
						
						System.out.println ("    \t|    " + String.format ("J %d   |", jobNumber [oldJobIndex]));
						System.out.println ("    \t|   " + String.format ("%.2f   |", decrementor));
						System.out.println (String.format ("%.2f", timer) + "\t|__________|");
						
						orderOfBursts [orderCount] = decrementor;
						orderOfJobs [orderCount] = oldJobIndex;
						orderCount++;
						
						oldJobIndex = oldJobIndex;
						decrementor = 0;
						
						flagTime [counter2] = timer;
						counter2++;
					} else {
						burstCopy [oldJobIndex] -= decrementor;
						
						System.out.println ("    \t|    " + String.format ("J %d   |", jobNumber [oldJobIndex]));
						System.out.println ("    \t|   " + String.format ("%.2f   |", decrementor));
						System.out.println (String.format ("%.2f", timer) + "\t|__________|");
						
						orderOfBursts [orderCount] = decrementor;
						orderOfJobs [orderCount] = oldJobIndex;
						orderCount++;
						
						oldJobIndex = lowestJobIndex;
						decrementor = 0;
						flagTime [counter2] = timer;
						counter2++;
					}
					
					decrementor = 0;
					oldJobIndex = lowestJobIndex;
				}
			} else {
				
				decrementor += 1;
				
				if (decrementor - burstCopy [oldJobIndex] == 0.00) {
					processedJobs [oldJobIndex] = true;
					burstCopy [oldJobIndex] -= decrementor;
					grandData [oldJobIndex][4] = timer;
					
					System.out.println ("    \t|    " + String.format ("J %d   |", jobNumber [oldJobIndex]));
					System.out.println ("    \t|   " + String.format ("%.2f   |", decrementor));
					System.out.println (String.format ("%.2f", timer) + "\t|__________|");
					
					orderOfBursts [orderCount] = decrementor;
					orderOfJobs [orderCount] = oldJobIndex;
					orderCount++;
					
					decrementor = 0;
					isExhausted = true;
					oldJobIndex = lowestJobIndex;
					
					flagTime [counter2] = timer;
					counter2++;
				}
			}
			
		} // for () Core
		
		oldJobIndex = lowestJobIndex;
		decrementor += 1;
		processedJobs [oldJobIndex] = true;
		burstCopy [oldJobIndex] -= decrementor;
		grandData [oldJobIndex][4] = timer;
		
		System.out.println ("    \t|    " + String.format ("J %d   |", jobNumber [oldJobIndex]));
		System.out.println ("    \t|   " + String.format ("%.2f   |", decrementor));
		System.out.println (String.format ("%.2f", timer) + "\t|__________|");
		
		orderOfBursts [orderCount] = decrementor;
		orderOfJobs [orderCount] = oldJobIndex;
		orderCount++;
		
		flagTime [counter2] = timer;
		counter2++;
		
		decrementor = 0;
		
		for (int count = 0; count < noOfJobs; count++) {
			grandData [count][5] = grandData [count][4] - grandData [count][0];
			grandData [count][6] = grandData [count][5] - grandData [count][1];
			
			ttAve += grandData [count][5];
			wtAve += grandData [count][6];
		}
		
		ttAve /= noOfJobs;
		wtAve /= noOfJobs;
		
		/* Sort by Job Number */
		for (int count = 0; count < noOfJobs; count++) {
			for (int count2 = 0; count2 < noOfJobs - 1; count2++) {
				if (jobNumber [count2 + 1] < jobNumber [count2]) {
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
					
					temp = burstCopy [count2 + 1];
					burstCopy [count2 + 1] = burstCopy [count2];
					burstCopy [count2] = temp;
				}
			}
		}
	}
	
	protected void 
	launchFrame () 
	{
		DrawGantt dg = new DrawGantt (grandData, jobNumber, ttAve, wtAve, flagTime, orderOfBursts, orderOfJobs, orderCount, noOfJobs, idle);
		dg.plaunchFrame ();
	}
	
}