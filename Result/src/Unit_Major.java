public class Unit_Major extends Unit {

    String unitID = "ICT167";
    int unitLevel = 2;
    double assignmentMark;
    double projectMark;
    double weeklyLab;
    double finalMajor;
    double overalMark;
    String finalGrade;
    String majorEnrollment;
    Unit_Major() { // Default constructor
        super();
        assignmentMark = projectMark = weeklyLab = finalMajor = overalMark = 0;
        finalGrade = "";
    }

    Unit_Major(double assignmentMark, double projectMark, double weeklyLab, double finalMajor) { // Parameterized constructor
        this.majorEnrollment=getMajorEnrollment();// lấy dữ liệu đã config sẵn cho loại là Major
        this.assignmentMark = assignmentMark;
        this.projectMark = projectMark;
        this.weeklyLab = weeklyLab;
        this.finalMajor = finalMajor;
        this.overalMark = 0.0;
        this.finalGrade = "";
    }

    void calculateWeightedAvg(){
        overalMark += (assignmentMark * 0.15); // The two assignment worths 15%
        overalMark += projectMark * 0.35; // the project work is worth 35%
        overalMark += weeklyLab * 0.10; // the weekly work is worth 15%
        overalMark += finalMajor * 0.40; //the final work is worth 40% of the final grade.
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

    double getOveralMark() {
    	calculateWeightedAvg(); // sẽ tính lại overalMark trước khi đưa lên cho Student Major
        return overalMark; // sau khi tính overalMark thì trả vê giá trị
    }
    public String getFinalGrade() {
    	calculateWeightedAvg();// sẽ tính lại finalGrade trước khi đưa lên cho Student Major
		return finalGrade;// sau khi tính finalGrade thì trả vê giá trị
	}
}
