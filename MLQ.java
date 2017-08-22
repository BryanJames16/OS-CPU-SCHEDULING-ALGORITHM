public class 
MLQ 
{
	
	int noOfJobs = 0;
	int jobNumber [];
	int orderOfJobs [];
	double grandData [][];
	double flagTime [];
	double ttAve = 0.00;
	double wtAve = 0.00;
	boolean idle = false;
	
	double queue1 [][];
	double queue2 [][];
	double queue3 [][];
	
	int q1No [];
	int q2No [];
	int q3No [];
	
	int q1count = 0;
	int q2count = 0;
	int q3count = 0;
	
	int qAlgo [] = new int [9];
	
	String queueType [] = new String [99];
	String algoName [] = new String [99];
	int algoNo [] = new int [99];
	int noOfQueue = 0;
	
	protected 
		MLQ (double grandData [][], int jobNumber [], int noOfJobs, int noOfQueue, String queueType[], int algoNo[]) 
	{
		
		this.grandData = new double [noOfJobs][ProcessConstants.GRAND_ARRAY];
		this.jobNumber = new int [noOfJobs];
		this.flagTime = new double [noOfJobs + 2];
		this.orderOfJobs = new int [noOfJobs];
		
		this.queue1 = new double [noOfJobs][ProcessConstants.GRAND_ARRAY];
		this.queue2 = new double [noOfJobs][ProcessConstants.GRAND_ARRAY];
		this.queue3 = new double [noOfJobs][ProcessConstants.GRAND_ARRAY];
		
		this.q1No = new int [noOfJobs];
		this.q2No = new int [noOfJobs];
		this.q3No = new int [noOfJobs];
		
		this.grandData = grandData;
		this.jobNumber = jobNumber;
		this.noOfJobs = noOfJobs;
		
		for (int count = 0; count < noOfJobs; count++) {
			System.out.println ("Job " + jobNumber [count] + " : " + queueType [count] + " " + algoNo [count]);
		}
		
		this.compute ();
		
	} // MLQ Constructor
	
	private void 
	compute () 
	{
		
		int counter = 0;
		int counter2 = 0;
		int orderCount = 0;
		double timer = 0.00;
		double totalBurst = 0.00;
		
		if (noOfQueue == 2) {
			for (int count = 0; count < noOfJobs; count++) {
				if (queueType [count].equals ("High")) {
					qAlgo [0] = algoNo [count];
					q1No[count] = jobNumber [count];
					queue1 [q1count] = grandData [count];
					q1count++;
				} else {
					qAlgo [1] = algoNo [count];
					q2No[count] = jobNumber [count];
					queue2 [q2count] = grandData [count];
					q2count++;
				}
			}
		} else {
			for (int count = 0; count < noOfJobs; count++) {
				if (queueType [count].equals ("High")) {
					qAlgo [0] = algoNo [count];
					q1No[count] = jobNumber [count];
					queue1 [q1count] = grandData [count];
					q1count++;
				} else if (queueType [count].equals ("Mid")) {
					qAlgo [1] = algoNo [count];
					q2No[count] = jobNumber [count];
					queue2 [q2count] = grandData [count];
					q2count++;
				} else {
					qAlgo [2] = algoNo [count];
					q3No[count] = jobNumber [count];
					queue2 [q3count] = grandData [count];
					q3count++;
				}
			}
		}
		
		if (noOfQueue == 2) {
			
			switch (qAlgo [0]) {
				
				/* FCFS */
				case 1 : {
					
					/* Sort By Arrival */
					for (int count = 0; count < q1count; count++) {
						for (int count2 = 0; count2 < q1count - 1; count2++) {
							if (queue1 [count2 + 1][0] < queue1 [count2][0]) {
								
								double temp = 0.00;
								int tempInt = 0;
								
								tempInt = q1No [count2 + 1];
								q1No [count2 + 1] = q1No [count2];
								q1No [count2] = tempInt;
								
								temp = queue1 [count2 + 1][0];
								queue1 [count2 + 1][0] = queue1 [count2][0];
								queue1 [count2][0] = temp;
								
								temp = queue1 [count2 + 1][1];
								queue1 [count2 + 1][1] = queue1 [count2][1];
								queue1 [count2][1] = temp;
								
								temp = queue1 [count2 + 1][2];
								queue1 [count2 + 1][2] = queue1 [count2][2];
								queue1 [count2][2] = temp;
								
								temp = queue1 [count2 + 1][3];
								queue1 [count2 + 1][3] = queue1 [count2][3];
								queue1 [count2][3] = temp;
								
								temp = queue1 [count2 + 1][4];
								queue1 [count2 + 1][4] = queue1 [count2][4];
								queue1 [count2][4] = temp;
								
								temp = queue1 [count2 + 1][5];
								queue1 [count2 + 1][5] = queue1 [count2][5];
								queue1 [count2][5] = temp;
								
								temp = queue1 [count2 + 1][6];
								queue1 [count2 + 1][6] = queue1 [count2][6];
								queue1 [count2][6] = temp;
								
							}
						}
					} // sort by arrival
					
					break;
				}
				
				/* SJF */
				case 2 : {
					
					/* Sort By Arrival */
					for (int count = 0; count < q1count; count++) {
						for (int count2 = 0; count2 < q1count - 1; count2++) {
							if (queue1 [count2 + 1][0] < queue1 [count2][0]) {
								
								double temp = 0.00;
								int tempInt = 0;
								
								tempInt = q1No [count2 + 1];
								q1No [count2 + 1] = q1No [count2];
								q1No [count2] = tempInt;
								
								temp = queue1 [count2 + 1][0];
								queue1 [count2 + 1][0] = queue1 [count2][0];
								queue1 [count2][0] = temp;
								
								temp = queue1 [count2 + 1][1];
								queue1 [count2 + 1][1] = queue1 [count2][1];
								queue1 [count2][1] = temp;
								
								temp = queue1 [count2 + 1][2];
								queue1 [count2 + 1][2] = queue1 [count2][2];
								queue1 [count2][2] = temp;
								
								temp = queue1 [count2 + 1][3];
								queue1 [count2 + 1][3] = queue1 [count2][3];
								queue1 [count2][3] = temp;
								
								temp = queue1 [count2 + 1][4];
								queue1 [count2 + 1][4] = queue1 [count2][4];
								queue1 [count2][4] = temp;
								
								temp = queue1 [count2 + 1][5];
								queue1 [count2 + 1][5] = queue1 [count2][5];
								queue1 [count2][5] = temp;
								
								temp = queue1 [count2 + 1][6];
								queue1 [count2 + 1][6] = queue1 [count2][6];
								queue1 [count2][6] = temp;
								
							}
						}
					} // sort by arrival
					
					break;
				}
				
				/* PRIO */
				case 3 : {
					
					break;
				}
				
				/* Deadline */
				case 4 : {
					
					break;
				}
			}
			
		} else {
			
		}
		
	} // compute();
	
	protected void 
	launchFrame ()
	{
		
	} // launchFrame();
	
} // Class MLQ