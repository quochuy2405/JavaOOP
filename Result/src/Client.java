
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<Student>();
 
    int menu() {
        System.out.print("\n\n ********** MENU ********** ");
        System.out.print("\n\t 1 - Quit " +
        		"\n\t 2 - Add Student" +
                "\n\t 3 - Remove Student" +
                "\n\t 4 - Output all details  " +
                "\n\t 5 - Common Student with Below or Above average" + 
                "\n\t 6 - Report student grade by StudentNo" +
                "\n\t 7 -  Sort ArrayList by order of the StudentNo" +
                "\n\t 8 - Output the sorted ArrayList to CSV" +
                "\n\t\t What is your choice? \n\n");
        return sc.nextInt();
    }

    public static void main(String[] s) {
        Client sr = new Client();
        
        do {
            switch (sr.menu()) {
                case 1:
                    System.exit(0);
                case 2:
                	 sr.readStudents();
                      break;
                case 3:
                    sr.removeStudent();
                    break;
                case 4:
                    sr.showAllStudent();
                    
                    break;	
                case 5:
					 sr.showCommonStudentBelowAboveAvg();
                    break;
                case 6:
                    sr.showReportThGradeStudent();
                    break;
                case 7:
					sr.SortListStudentByIDNumber();
                    break;
                case 8:
                	sr.OutputSortedArrayListSToCSV();
                    break;

                default:
                    System.out.print("\n Invalid choice!!");
            }
        } while (true);
    }

	public void showCommonStudentBelowAboveAvg() {
		int CountBelowAVG=0;// kh???i t???o bi???n
		int CountAboveAVG=0;
		for (Student st : students) { // g??n l???n l?????t t???ng student v??o trong st 
			if(st.getClass()==Student_Common.class) {
				Student_Common st_common = (Student_Common)st;// ??p ki???u// st ki???u Student// n??n ph???i ??p ki???u v??? student_Common
				if(st_common.getOveralMark()>50.0) { // ki???m tra xem c?? tr??n trung b??nh hay kh??ng
					CountAboveAVG++; // t??y theo l???i th?? ti???n h??nh t??ng gi?? tr??? +1
				}
				else {
					CountBelowAVG++;
				}
			}
		}
		   System.out.println("CountBelowAVG: "+CountBelowAVG);
		   System.out.println("CountAboveAVG: "+CountAboveAVG);
	}
	public void OutputSortedArrayListSToCSV() {
		File file = new File("OutPut.csv");
		// t???o file 
        FileWriter fw;
		try {
			fw = new FileWriter(file);// ti???n h??nh t???o file
			 BufferedWriter bw = new BufferedWriter(fw);// ti???n h??nh t???o buffer// b??? nh??? ?????m 
				for (Student student : students) {
					bw.write(student.MakeStringForCSV());// vi???t gi??? li???u ???? ???????c custom s???n ????? th??m v??o file csv
		        	bw.newLine();
				}
		        bw.close();// ????ng file v?? buffer
		        fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}
	public void SortListStudentByIDNumber() { 
		for(int i=0;i<students.size()-1;i++) // Sort bong b??ng
		{
			for(int j=i+1;j<students.size();j++)
			{
			    if(students.get(i).getStudentNo()>students.get(j).getStudentNo())// h???c thuatan to??n bubble
			    	// check n???u ????ng tr?????ng h???p
			    {   
			    	var temp=students.get(i); // ?????i ch??? n???u ????ng tr?????ng h???p theo thu???t tu???n bubble sort
			    	students.set(i, students.get(j)); // swap
			    	students.set(j, temp);
			    }
			}
		}
		System.out.println("-------ArrayList was sorted-----------");
		
		
	}
    public void showReportThGradeStudent() {
    	System.out.print("\nEnter ID Number:");
    	long StudentID= sc.nextLong();
    	Student st = new Student();
    	for (Student student : students) {
    		if(student.getStudentNo()==StudentID)// ki???m tra Student ID trong ArrayList 
    			//n???u ????ng th?? g??n bi???n st ????? l??u ????ng id v?? d???ng v??ng l???p
    		{
    			st=student;
    			break;
    		}
    			
		}
    	if(st.getStudentNo()==0) // ki???m tra xem st ???? c?? gi?? tr??? hay ch??a n???u ch??a th?? in not found
    	{ 
    		System.out.println("\n Not Found!!");
    	}
    	else {
    		System.out.println(st.reportGrade()); // n???u c?? th?? in student ???? ???????c l??u tr?????c ????
    	}
    	
    }

	public void showAllStudent() { 
		
		
		for (Student student : students) {
			System.out.println(student.reportGrade());// in to??n b??? student ra b???ng foreach
		}
	
	}

	public void removeStudent() {
		
		System.out.print("\nEnter ID Number:");
		long StudentID= sc.nextLong();  // nh???p Id v??o
		Student StudentRemove =  new Student();// bi???n nh??? 
		int index = 0; //l??u nh??? l???i v??? tr?? c???a student trong m???ng
		for (Student student : students) {
			if(student.getStudentNo()==StudentID) // ki???m tra xem c?? ????ng student Id kh??ng
				
			{   System.out.print("\nDo you want to detete ? Y/Yes or N/No ");
				String confirm= sc.next();
			     if(confirm.equals("yes")||confirm.equals("Yes")||confirm.equals("Y")||confirm.equals("y"))
			     {
			    	 StudentRemove=student;
				     students.remove(index);// v?? nh???n ???????c index ch??nh student c???n x??a v?? ti???n h??nh x??a kh???i m???ng
				     break;
			     }
			} 
			index++;// kh??ng t??m ???????c th?? t??ng l??n 
		}
					    
		if(StudentRemove.getStudentNo()!=0) // ki???m tra student c?? t???n t???i k c?? th?? in ra kh??ng th?? in notfound
		{
			  System.out.println("-------Student Remove-----------");
			  System.out.println("First Name: "+StudentRemove.getFirstName());
			  System.out.println("Last Name: "+StudentRemove.getLastName());
			  System.out.println("Student Code ID: "+StudentRemove.getStudentNo());
		}
		else {
			System.out.println("-------Student Not Found-----------");
		}
	  
	}



	public void readStudents() {
		
		try (BufferedReader br = new BufferedReader(new FileReader("Students.csv"))) {
			 // try catch v?? ?????c file Student.csv
		    String line;
		    while ((line = br.readLine()) != null) {
		    	
		        String[] values = line.split(",");// c???t th??ng tin t??? string khi l???y d??? li???u theo d??ng 
		        Student st;
		        // l??u tr??? c??c th??ng tin c???n thi???t
		        String FisrtName=values[1];
		        String LastName=values[2];
		        Long StudentNo=Long.parseLong(values[3]);
		        int Day = Integer.parseInt(values[4]);
		        int Month=Integer.parseInt(values[5]);
		        int Year= Integer.parseInt(values[6]);
		        if(values[0].toString().trim().equals("C"))// ki???m tra ?????y v??o ????? x??c ?????nh ????ng lo???i student
		        {    // g??n th??m c??c th??ng tin c???n thi???t
		        	double asm1=Double.parseDouble(values[7]);
		        	double asm2=Double.parseDouble(values[8]);
		        	double pracWork=Double.parseDouble(values[9]);
		            double finalExam=Double.parseDouble(values[10]);
		        	st= new Student_Common(FisrtName,LastName,StudentNo,Day,Month,Year,asm1,asm2,pracWork,finalExam);   	 
		        	// v?? st l?? cha c???a Student_Common n??n theo t??nh ??a h??nh th?? c?? th??? kh???i t???o ???????c h??m con
		        }
		        else {
		        	// g??n th??m c??c th??ng tin c???n thi???t
		        	double assignmentMark=Double.parseDouble(values[7]);
		        	double projectMark=Double.parseDouble(values[8]);
		        	double weeklyLab=Double.parseDouble(values[9]);
		            double finalMajor=Double.parseDouble(values[10]);
		        	 st= new Student_Major(FisrtName,LastName,StudentNo,Day,Month,Year,assignmentMark,projectMark,weeklyLab,0);
		        }
		    	// sau khi c?? d??? li???u th?? th??m st v??o  ArrayList
		        students.add(st);	
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {// input out push student
			e.printStackTrace();
		}
		
	}
}
