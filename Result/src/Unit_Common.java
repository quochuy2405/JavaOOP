public class Unit_Common extends Unit {
    String unitID = "ICT167";
    int unitLevel = 1;
    double assignment1;
    double assignment2;
    double practicalWork;
    double finalExamination;
    double overalMark;
    String finalGrade;
    String commonEnrollment;

   public Unit_Common() { // default constructor to store default values in instance variables
        super(); // being call inside method that overriden it in this subclass (child class)
        assignment1 = assignment2 = practicalWork = finalExamination = overalMark = 0.0;
        finalGrade = "";
    }

   public Unit_Common(double asm1, double asm2, double pracWork, double finalExam) {
        this.commonEnrollment = getCommonEnrollment();// lấy dữ liệu đã config sẵn cho loại là Common
        this.assignment1 = asm1;
        this.assignment2 = asm2;
        this.practicalWork = pracWork;
        this.finalExamination = finalExam;
        this.overalMark = 0.0;
        this.finalGrade = "";
    }

    //  calculating the weighted average of the student's performance in the assessment components
    void calculateWeightedAvg(){
        overalMark += (assignment1 * 0.30) + (assignment2 * 0.30); // The two assignments together count for a total of 60%
        // (30% each) of the final grade
        overalMark += practicalWork * 0.15; // the practical work is worth 15%
        overalMark += finalExamination * 0.25; //the final exam is worth 25% of the final grade.

        if (overalMark >=0)
        {
     	   if(overalMark >= 80.0) //  An overall mark of 80 or higher is an HD
                finalGrade = "HD";
            else if(overalMark >= 70.0) // overall mark of 70 or higher (but less than 80) is a D
                finalGrade = "D";
            else if(overalMark >= 60.0) // an overall mark of 60 or higher (but less than 70) is a C
                finalGrade = "C";
            else if(overalMark >= 50.0) // an overall mark of 50 or higher (but less than 60) is a P
                finalGrade = "P";
            else
                finalGrade = "N"; // and an overall mark below 50 is an N
        }
         else 
         	finalGrade= getFinalGrade();
    }

    double getOveralMark() { // handling exception
    	calculateWeightedAvg();// sẽ tính lại overalMark trước khi đưa lên cho Student Common
        return overalMark;// sau khi tính overalMark thì trả vê giá trị
    }
    public String getFinalGrade() {
    	calculateWeightedAvg();// sẽ tính lại finalGrade trước khi đưa lên cho Student Common
		return finalGrade;// sau khi tính finalGrade thì trả vê giá trị
	}
}
