public class 
RR 
{
	int noOfJobs = 0;
	int orderCount = 0;
	int jobNumber [];
	int orderOfJobs [];
	int oldJobIndex = 99;
	int queue [] = new int [99];
	int queueCount = 0;
	int runningCount = 0;
	double orderOfBursts [];
	double burstCopy [];
	double arrivalCopy [];
	double grandData [][];
	double flagTime [];
	double quantum = 0.00;
	double ttAve = 0.00;
	double wtAve = 0.00;
	boolean idle = false;
	boolean processedJobs [];
	boolean isTie [];
	
	int stack [][] = new int [99][99];
	boolean isAlive [][] = new boolean [99][99];
	double stackArrival [] = new double [99];
	int widthCount [] = new int [99];
	int stackCount = 0;
	int widest = 0;
	
	protected 
	RR (double grandData [][], int jobNumber [], int noOfJobs, double quantum) 
	{
		this.grandData = new double [noOfJobs][ProcessConstants.GRAND_ARRAY];
		this.jobNumber = new int [noOfJobs];
		this.flagTime = new double [99];
		this.orderOfJobs = new int [99];
		this.orderOfBursts = new double [99];
		this.processedJobs = new boolean [noOfJobs];
		this.arrivalCopy = new double [noOfJobs];
		this.burstCopy = new double [noOfJobs];
		this.quantum = quantum;
		this.isTie = new boolean [noOfJobs];
		this.stack = new int [99][noOfJobs];
		this.stackArrival = new double [99];
		
		this.grandData = grandData;
		this.jobNumber = jobNumber;
		this.noOfJobs = noOfJobs;
		
		for (int count = 0; count < noOfJobs; count++) {
			this.arrivalCopy [count] = grandData [count][0];
			this.burstCopy [count] = grandData [count][1];
			this.isTie [count] = false;
			this.widthCount [count] = 0;
		};
		
		this.compute ();
	}
	
	private void 
	compute () 
	{
		double timer = 0.00;
		double totalBurst = 0.00;
		boolean isExhausted = false;
		int counter2 = 0;
		
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
					
					temp = arrivalCopy [count2 + 1];
					arrivalCopy [count2 + 1] = arrivalCopy [count2];
					arrivalCopy [count2] = temp;
					
					temp = burstCopy [count2 + 1];
					burstCopy [count2 + 1] = burstCopy [count2];
					burstCopy [count2] = temp;
				}
			}
		} // for () sort arrival
		
		for (int count = 0; count < noOfJobs; count++) {
			try {
				if (grandData [count + 1][0] == grandData [count][0]) {
					isTie [count] = true;
				}
			} catch (ArrayIndexOutOfBoundsException aiobe) {
				
			}
		}
		
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
			
			queue [queueCount] = ProcessConstants.MAX_JOB;
			queueCount++;
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
		
		for (int count = 0; count < noOfJobs; count++) {
			if (count != 0) {
				if (arrivalCopy [count] == arrivalCopy [count - 1]) {
					
					stack [count - 1][widthCount [count - 1]] = jobNumber [count];
					isAlive [count - 1][widthCount [count - 1]] = true;
					stackArrival [count - 1] = arrivalCopy [count];
					widthCount [count - 1]++;
					
					if (widthCount [count] > widest) {
						widest = widthCount [count];
					}
					
				} else {
					stack [stackCount][widthCount [stackCount]] = jobNumber [count];
					isAlive [stackCount][widthCount [stackCount]] = true;
					stackArrival [stackCount] = arrivalCopy [count];
					widthCount [stackCount]++;
					stackCount++;
				}
			} else {
				stack [stackCount][0] = jobNumber [count];
				isAlive [stackCount][0] = true;
				stackArrival [stackCount] = arrivalCopy [count];
				widthCount [stackCount]++;
				stackCount++;
				widest++;
			}
		}
		
		int lowestJobIndex = ProcessConstants.MIN_INDEX;
		
		// Core
		for (timer = grandData [0][0]; timer < totalBurst; ) { 
			for (int count = 0; count < noOfJobs; count++) {
				for (int count2 = count; count2 < noOfJobs - 1; count2++) {
					
					if (arrivalCopy [count2] > arrivalCopy [count2 + 1]) {
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
						
						temp = arrivalCopy [count2 + 1];
						arrivalCopy [count2 + 1] = arrivalCopy [count2];
						arrivalCopy [count2] = temp;
						
						temp = burstCopy [count2 + 1];
						burstCopy [count2 + 1] = burstCopy [count2];
						burstCopy [count2] = temp;
					} else if ((arrivalCopy [count2] == arrivalCopy [count2 + 1]) && (!isTie [count2] && !isTie [count2 + 1])) {
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
						
						temp = arrivalCopy [count2 + 1];
						arrivalCopy [count2 + 1] = arrivalCopy [count2];
						arrivalCopy [count2] = temp;
						
						temp = burstCopy [count2 + 1];
						burstCopy [count2 + 1] = burstCopy [count2];
						burstCopy [count2] = temp;
						
						isTie [count2] = true;
						isTie [count2 + 1] = true;
						
						count = count2 + 3;
						break;
					}
					
					if ((isTie [count2] && isTie [count2 + 1]) && (count2 == 0)) {
						isTie [count2] = false;
						isTie [count2 + 1] = false;
						
						count = count2 + 2;
						break;
					}
					
				}
			} // for () sort arrival
			
			lowestJobIndex = 0;
			
			if (burstCopy [lowestJobIndex] - quantum <= 0) {
				processedJobs [lowestJobIndex] = true;
				timer += burstCopy [lowestJobIndex];
				grandData [lowestJobIndex][4] = timer;
				arrivalCopy [lowestJobIndex] = timer;
				orderOfJobs [orderCount] = jobNumber [lowestJobIndex];
				orderOfBursts [orderCount] = burstCopy [lowestJobIndex];
				flagTime [counter2] = timer;
				
				boolean isStacked = false;
				
				for (int count = 0; count < stackCount; count++) {
					if (arrivalCopy [lowestJobIndex] == stackArrival [count]) {
						//System.out.println ("Stacked Dead: " + stack [count] [widthCount [stackCount]]);
						stack [count][widthCount [count]] = jobNumber [lowestJobIndex];
						isAlive [stackCount][widthCount [stackCount]] = false;
						widthCount [count]++;
						isStacked = true;
						
						if (widthCount [count] > widest) {
							widest = widthCount [count];
						}
						
						break;
					}
				}
				
				if (!isStacked) {
					//System.out.println ("Unstacked Dead: " + stack [stackCount] [widthCount [stackCount]]);
					stack [stackCount][widthCount [stackCount]] = jobNumber [lowestJobIndex];
					isAlive [stackCount][widthCount [stackCount]] = false;
					stackArrival [stackCount] = arrivalCopy [lowestJobIndex];
					widthCount [stackCount]++;
					
					if (widthCount [stackCount] > widest) {
						widest = widthCount [stackCount];
					}
					
					stackCount++;
					
					for (int count = 0; count < stackCount; count++) {
						for (int count2 = 0; count2 < stackCount - 1; count2++) {
							if (stackArrival [count2 + 1] < stackArrival [count2]) {
								boolean tempBool = false;
								
								int temp2Int [] = stack [count2];
								stack [count2] = stack [count2 + 1];
								stack [count2 + 1] = temp2Int;
								
								boolean temp2Bool [] = isAlive [count2];
								isAlive [count2] = isAlive [count2 + 1];
								isAlive [count2 + 1] = temp2Bool;
								
								double tempDoub = stackArrival [count2];
								stackArrival [count2] = stackArrival [count2 + 1];
								stackArrival [count2 + 1] = tempDoub;
								
								int tempInt = widthCount [count2];
								widthCount [count2] = widthCount [count2 + 1];
								widthCount [count2 + 1] = tempInt;
							}
						}
					}
					
				}
				
				System.out.println ("    \t|    " + String.format ("J %d   |", jobNumber [lowestJobIndex]));
				System.out.println ("    \t|   " + String.format ("%.2f   |", (burstCopy [lowestJobIndex])));
				System.out.println (String.format ("%.2f", timer) + "\t|__________|");
				
				counter2++;
				orderCount++;
				burstCopy [lowestJobIndex] = ProcessConstants.MAX_INT;
				arrivalCopy [lowestJobIndex] = ProcessConstants.MAX_ARRIVAL;
				runningCount++;
				
			} else {
				burstCopy [lowestJobIndex] -= quantum;
				timer += quantum;
				arrivalCopy [lowestJobIndex] = timer;
				orderOfJobs [orderCount] = jobNumber [lowestJobIndex];
				orderOfBursts [orderCount] = quantum;
				flagTime [counter2] = timer;
				
				boolean isStacked = false;
				
				for (int count = 0; count < stackCount; count++) {
					if (arrivalCopy [lowestJobIndex] == stackArrival [count]) {
						try {
							stack [count][widthCount [count]] = jobNumber [lowestJobIndex];
							isAlive [stackCount][widthCount [stackCount]] = true;
							widthCount [count]++;
							isStacked = true;
							
							if (widthCount [count] > widest) {
								widest = widthCount [count];
							}
							
							break;
						} catch (ArrayIndexOutOfBoundsException aiobe) {
							
						}
					}
				}
				
				if (!isStacked) {
					stack [stackCount][widthCount [stackCount]] = jobNumber [lowestJobIndex];
					isAlive [stackCount][widthCount [stackCount]] = true;
					stackArrival [stackCount] = arrivalCopy [lowestJobIndex];
					widthCount [stackCount]++;
					
					if (widthCount [stackCount] > widest) {
						widest = widthCount [stackCount];
					}
					
					stackCount++;
					
					for (int count = 0; count < stackCount; count++) {
						for (int count2 = 0; count2 < stackCount - 1; count2++) {
							if (stackArrival [count2 + 1] < stackArrival [count2]) {
								boolean tempBool = false;
								
								int temp2Int [] = stack [count2];
								stack [count2] = stack [count2 + 1];
								stack [count2 + 1] = temp2Int;
								
								boolean temp2Bool [] = isAlive [count2];
								isAlive [count2] = isAlive [count2 + 1];
								isAlive [count2 + 1] = temp2Bool;
								
								double tempDoub = stackArrival [count2];
								stackArrival [count2] = stackArrival [count2 + 1];
								stackArrival [count2 + 1] = tempDoub;
								
								int tempInt = widthCount [count2];
								widthCount [count2] = widthCount [count2 + 1];
								widthCount [count2 + 1] = tempInt;
							}
						}
					}
					
				}
				
				System.out.println ("    \t|    " + String.format ("J %d   |", jobNumber [lowestJobIndex]));
				System.out.println ("    \t|   " + String.format ("%.2f   |", quantum));
				System.out.println (String.format ("%.2f", timer) + "\t|__________|");
				
				counter2++;
				orderCount++;
			}
			
		} // for () Core
		
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
		} // Sort by Job Number
	}
	
	private static int []
	insertIndex (int array[], int position, int value) 
	{
		
		int result [] = new int [array.length];
		
		for (int ctr = 0; ctr < position; ctr++) {
			result [ctr] = array [ctr];
		}
		
		result [position] = value;
		
		for (int ctr = position + 1; ctr < array.length; ctr++) {
			result [ctr] = array [ctr - 1];
		}
		
		return (result);
	}
	
	private static int 
	searchIndex  (int array[], int length, int value) 
	{
		
		int retVal = 0;
		int count = 0;
		boolean isFound = false;
		
		for (count = 0; count < length; count++) {
			if (value == array [count]) {
				retVal = count;
				isFound = true;
				break;
			}
		}
		
		if (!isFound) {
			retVal = ProcessConstants.MAX_NUMBER;
		}
		
		return (retVal);
	}
	
	protected void 
	launchFrame () 
	{
		RRDrawGantt rrdg = new RRDrawGantt (grandData, jobNumber, ttAve, wtAve, flagTime, orderOfBursts, orderOfJobs, orderCount, noOfJobs, idle, stack, isAlive, stackArrival, widthCount, stackCount, widest);
		rrdg.launchFrame ();
	}
}