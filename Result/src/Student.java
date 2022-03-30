public class Student 
{
   String unitID = "ICT159";
   int unitLevel;
   private String firstName;
   private String  lastName;
   private long StudentNo;
   private int day, month, year;

   public Student() { // default constructor
      firstName = "";
      lastName = "";
      day = month = year = (int) (StudentNo = 0);
   }
   public Student(String unitID, int unitLevel, String firstName, String lastName, long StudentNo, int day, int month, int year) { // constructor with parameter and a set of values in curly braces
      this.firstName = firstName;
      this.lastName = lastName;
      this.StudentNo = StudentNo;
      this.day = day;
      this.month = month;
      this.year = year;
   }

   /**
    * Setter Methods
    * @param
    */
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   } // set the object instance variable equals the argument variable

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public void setStudentNo(long StudentNo) {
      this.StudentNo = StudentNo;
   }

   public void setDOB(int day, int month, int year)
   {
      this.day = day;
      this.month = month;
      this.year = year;
   }

   /**
    * Getter Methods
    * @return
    */
   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public long getStudentNo() {
      return StudentNo;
   }

   public int getDay()
   {
      return day;
   }

   public int getMonth()
   {
      return month;
   }

   public int getYear()
   {
      return year;
   }

   String getDOB()
   {
      return day + "/" + month + "/" + year;
   }

//   public String reportGrade(){ // just print to the screen a message â€œThere is no grade here.â€�
//      return ("There is no grade here");
//   }

   // equals method to check both name and  StudentNo of a Student
   // Overriding equals() to compare parameter object name and DOB with instance object name and DOB
   // Returns true if same other wise returns false
   public boolean equals(Student b) // parameter object Student b
   {
   // Checks if the parameter object other is equals to implicit object
      if (b == this)
         return true;

   // Checks if parameter object other is not an instance of Student class
      if (!(b instanceof Student)) // if parameter of Student is differ with b will return false
         return false;

   // Type cast parameter object other to Student class object
      Student class1 = (Student) b;

   // Concatenates to get complete name for instance object
      String instanceName =  firstName + lastName;

   // Concatenates to get complete name for parameter object
      String parameterName = class1.firstName + class1.lastName;

   // Checks name and DOB if same returns true
      if(instanceName.equalsIgnoreCase(parameterName) && // if instance object name = parameter name will return true
              getDOB().equalsIgnoreCase(class1.getDOB())) // equalsIgnoreCase is a String method to compare String variables
                                                         // without checking Lower or Upper Cases exception
         return true;

   // Otherwise returns false
      else
         return false;
   }
   public String MakeStringForCSV() {  // tạo ra để export đơn giản hơn
	   return firstName+","+lastName+","+StudentNo+","+getDOB();
   }
   // toString() to return Student info
   public String reportGrade() // this is automatically called when the main method print out the instance of object created
   {
      String Show=
    		  "\n Name: " + " " + getFirstName() + " " + getLastName() +
              "\n ID: " + getStudentNo() +
              "\n DOB: " + getDOB() ;
      return Show;
   }

}



