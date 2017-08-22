public class 
	ProcessConstants 
{
	
	/**
		* Grand Data Variable Map
	*/
	// grandData [][0] arrivalTime
	// grandData [][1] burstTime
	// grandData [][2] priority
	// grandData [][3] deadline
	// grandData [][4] exitTime
	// grandData [][5] totalTurn
	// grandData [][6] totalWait
	
	/**
		* All Purpose Values
	*/
	static int MIN_JOB			=		1;
	static int MIN_INDEX 		= 		0;
	static double MIN_BURST 	= 		0;
	
	static int MAX_JOB 			= 		32767;
	static int MAX_NUMBER 		= 		32767;
	static double MAX_INT 		= 		32767;
	static double MAX_ARRIVAL 	= 		32767;
	static double MAX_DEADLINE 	= 		1000;
	static double MAX_BURST 	= 		1000;
	
	static int GRAND_ARRAY 		= 		7;
	static double TIME_STEP 	= 		0.25;
	
	/**
		* CPU Scheduling Algorithms
	*/
	static int FCFS 			= 		1;
	static int SJF 				= 		2;
	static int PRIO 			= 		3;
	static int P_PRIO 			= 		4;
	static int SRTF 			= 		5;
	static int RR 				= 		6;
	static int RRO 				= 		7;
	static int Deadline 		= 		8;
	static int MLQ 				= 		9;
	static int MLFQ 			= 		10;
	
	/**
		* Priority Algorithms
	*/
	static int MIN_PRIO 		= 		0;
	static int MAX_PRIO 		= 		1000;
	
	/**
		* Deadline Algorithms
	*/
	static int LOWEST_DEADLINE 	= 		32767;
	
	/**
		* All Purpose Functions
	*/
	static double 
		decimalTruncate (double value) 
	{
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##", java.text.DecimalFormatSymbols.getInstance(java.util.Locale.ENGLISH));
		df.setRoundingMode(java.math.RoundingMode.DOWN);
		
		return (Double.parseDouble (df.format (value))); 
		
	} // decimalTruncate()
	
} // class ProcessConstants