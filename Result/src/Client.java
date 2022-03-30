
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<Student>();
 
    int menu() {
        System.out.print("\n\n ********** MENU ********** ");
        System.out.print("\n\t 1 - Quit " +
                "\n\t 2 - Remove Student" +
                "\n\t 3 - Output all details  " +
                "\n\t 4 - Common Student with Below or Above average" + 
                "\n\t 5 - Report student grade by StudentNo" +
                "\n\t 6 -  Sort ArrayList by order of the StudentNo" +
                "\n\t 7 - Output the sorted ArrayList to CSV" +
                "\n\t\t What is your choice? \n\n");
        return sc.nextInt();
    }

    public static void main(String[] s) {
        Client sr = new Client();
        sr.readStudents();
        do {
            switch (sr.menu()) {
                case 1:
                    System.exit(0);
                case 2:
                    sr.removeStudent();
                    break;
                case 3:
                    sr.showCommonStudent();
                    break;
     				
                case 4:
					 sr.showCommonStudentBelowAboveAvg();
                    break;
                case 5:
                    sr.showReportThGradeStudent();
                    break;
                case 6:
					sr.SortListStudentByIDNumber();
                    break;
                case 7:
                	sr.OutputSortedArrayListSToCSV();
                    break;

                default:
                    System.out.print("\n Invalid choice!!");
            }
        } while (true);
    }

	public void showCommonStudentBelowAboveAvg() {
		int CountBelowAVG=0;
		int CountAboveAVG=0;
		for (Student student : students) {
			if(student.getClass()==Student_Major.class) {
				Student_Major st = (Student_Major)student;
				if(st.getOveralMark()>50.0) {
					CountAboveAVG++;
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
        FileWriter fw;
		try {
			fw = new FileWriter(file);
			 BufferedWriter bw = new BufferedWriter(fw);
				for (Student student : students) {
					bw.write(student.MakeStringForCSV());
		        	bw.newLine();
				}
		        bw.close();
		        fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}
	public void SortListStudentByIDNumber() {
		for(int i=0;i<students.size()-1;i++) // Sort bong bóng
		{
			for(int j=i;j<students.size();j++)
			{
			    if(students.get(i).getStudentNo()>students.get(j).getStudentNo())
			    {   
			    	var temp=students.get(i);
			    	
			    	students.set(i, students.get(j));
			    	students.set(j, temp);
			    }
			}
		}
		
		
		
	}
    public void showReportThGradeStudent() {
    	System.out.print("\nEnter ID Number:");
    	long StudentID= sc.nextLong();
    	Student st = new Student();
    	for (Student student : students) {
    		if(student.getStudentNo()==StudentID)
    		{
    			st=student;
    			break;
    		}
    			
		}
    	if(st.getStudentNo()==0)
    	{
    		System.out.println("\n Not Found!!");
    	}
    	else {
    		System.out.println(st.reportGrade());
    	}
    	
    }

	public void showCommonStudent() {
		for (Student student : students) {
			System.out.println(student.reportGrade());
		}
	
	}

	public void removeStudent() {
		System.out.print("\nEnter ID Number:");
		long StudentID= sc.nextLong();
		final Student StudentRemove = new Student();
		int index = 0;
		for (Student student : students) {
			if(student.getStudentNo()==StudentID)
				
			{   System.out.print("\nDo you want to detete ? Y/Yes or N/No ");
				String confirm= sc.next();
			     if(confirm.equals("yes")||confirm.equals("Yes")||confirm.equals("Y")||confirm.equals("y"))
			     {
			    	 StudentRemove.setStudentNo(student.getStudentNo());
					 StudentRemove.setLastName(student.getLastName());
				     StudentRemove.setFirstName(student.getFirstName());
				     students.remove(index);
				     break;
			     }
			} 
			index++;
		}
					    
		if(StudentRemove.getStudentNo()!=0)
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
		
		try (BufferedReader br = new BufferedReader(new FileReader("DanhSachSV.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	
		        String[] values = line.split(",");
		        Student st;
		        String FisrtName=values[1];
		        String LastName=values[2];
		        Long StudentNo=Long.parseLong(values[3]);
		        int Day = Integer.parseInt(values[4]);
		        int Month=Integer.parseInt(values[5]);
		        int Year= Integer.parseInt(values[6]);
		        if(values[0].toString().trim().equals("C"))
		        {    
		        	double asm1=Double.parseDouble(values[7]);
		        	double asm2=Double.parseDouble(values[8]);
		        	double pracWork=Double.parseDouble(values[9]);
		            double finalExam=Double.parseDouble(values[10]);
		        	st= new Student_Common("1",1,FisrtName,LastName,StudentNo,Day,Month,Year,asm1,asm2,pracWork,finalExam);   	 
		        	 
		        }
		        else {
		        	double assignmentMark=Double.parseDouble(values[7]);
		        	double projectMark=Double.parseDouble(values[8]);
		        	double weeklyLab=Double.parseDouble(values[9]);
		            double finalMajor=Double.parseDouble(values[10]);
		        	 st= new Student_Major("1",1,FisrtName,LastName,StudentNo,Day,Month,Year,assignmentMark,projectMark,weeklyLab,0);
		        }
		    	// thêm học sinh vào ArrayList
		        students.add(st);	
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
