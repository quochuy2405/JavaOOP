public class Student_Major extends Student {
    final Unit_Major Student_MJ;

    Student_Major() { // Default constructor
        super();
        Student_MJ = new Unit_Major();
    }

    Student_Major(String unitID, int unitLevel, String firstName, String lastName, long StudentNo,int day, int month, int year,
                  double assignmentMark, double projectMark, double weeklyLab, double finalMajor) { // Parameterized constructor
    	
    		super(unitID, unitLevel, firstName, lastName, StudentNo, day, month, year);
    		Student_MJ = new Unit_Major(assignmentMark ,projectMark ,weeklyLab,finalMajor);
    }
     public double  getOveralMark() {
    	 return Student_MJ.getOveralMark();
     }

	public String  getFinalGrade() {
		 return Student_MJ.getFinalGrade();
	}
	
	
    // Override reportGrade method to return student major information
    @Override
    public String reportGrade() // this is automatically called when the main method print out the instance of object created
    {   
        String showGrade = super.reportGrade();
        showGrade +="\n Unit Number: " + " " + unitID + " " +
                    "\n Unit Level: " + (unitLevel<2?"C":"M") + " " +
                    "\n Assignment: " + Student_MJ.assignmentMark +
                    "\n Project Mark: " + Student_MJ.projectMark +
                    "\n Practical Work: " + Student_MJ.weeklyLab +
                    "\n Final Examination: " + Student_MJ.finalMajor +
                    "\n Weighted Average: " + getOveralMark() +
                    "\n Grade: " + getFinalGrade()+
                    "\n----------------------------------";
        return showGrade;
        
    }
    @Override
    public String MakeStringForCSV() {  // tạo ra để export đơn giản hơn
   	 String ForCSV=(unitLevel==1?"C":"M")+","+super.MakeStringForCSV()+","+unitID +","+unitLevel + ","
   			 		+Student_MJ.assignmentMark+","+Student_MJ.projectMark+","+
   			 		Student_MJ.weeklyLab +"," +Student_MJ.finalMajor +"," 
   			 		+ getOveralMark() +"," + getFinalGrade();
   	 return ForCSV; 			 
   }

}
