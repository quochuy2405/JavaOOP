public class Student_Common extends Student  {
    final Unit_Common Student_CM;

    Student_Common() { // default constructor to store default values in instance variables
        super(); // being call inside method that override it in this subclass (child class)
        Student_CM=new Unit_Common();
    }

    Student_Common(String unitID, int unitLevel, String firstName, String lastName, long StudentNo, int day, int month, int year,
                   double asm1, double asm2, double pracWork, double finalExam) {
        super(unitID, unitLevel, firstName, lastName, StudentNo, day, month, year); // use the instance variables from superclass (parent class)
        Student_CM = new Unit_Common( asm1,  asm2,  pracWork,  finalExam);// tạo dữ liệu cho lớp unit common
    }

    //  calculating the weighted average of the student's performance in the assessment components
    public double  getOveralMark() {
   	 return Student_CM.getOveralMark();// truy xuát dữ liệu  OveralMark of student
    }
    
    public String getFinalGrade() {
    	return Student_CM.getFinalGrade();// truy xuát dữ liệu  FinalGrade of student
    }
   
    // Override reportGrade method to return student common information
    @Override
    public String reportGrade()
    {
        String showGrade = super.reportGrade(); // super keyword to call the toString method from the superclass (parent class_
        // sử dụng toán tử 3 ngôi để check unitLevel
        showGrade +=
                    "\n Unit Number: " + " " + unitID + " " +
                    "\n Unit Level: " +(unitLevel==1?"C":"M") + " " +
                    "\n Assignment1: " + Student_CM.assignment1 +
                    "\n Assignment2: " + Student_CM.assignment2 +
                    "\n Practical Work: " + Student_CM.practicalWork +
                    "\n Final Examination: " + Student_CM.finalExamination +
                    "\n Weighted Average: " + getOveralMark() +
                    "\n Grade: " + getFinalGrade()+
                    "\n----------------------------------";
        return showGrade;
    }
    @Override
    public String MakeStringForCSV() { // tạo ra để export đơn giản hơn
   	 String ForCSV=(unitLevel<2?"C":"M")+","+super.MakeStringForCSV()+","+unitID +","+unitLevel + ","
   			 		+Student_CM.assignment1+","+Student_CM.assignment2+","+
   			 		Student_CM.practicalWork +"," +Student_CM.finalExamination +"," 
   			 		+ getOveralMark() +"," + getFinalGrade();
   	 return ForCSV; 			 
   }
}
