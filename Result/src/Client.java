
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
                    sr.showAllStudent();
                    
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
		int CountBelowAVG=0;// khởi tạo biến
		int CountAboveAVG=0;
		for (Student student : students) {
			if(student.getClass()==Student_Major.class) {
				Student_Major st = (Student_Major)student;
				if(st.getOveralMark()>50.0) { // kiểm tra xem có trên trung bình hay không
					CountAboveAVG++; // tùy theo lại thì tiến hành tăng giá trị
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
		// tạo file 
        FileWriter fw;
		try {
			fw = new FileWriter(file);// tiến hành tạo file
			 BufferedWriter bw = new BufferedWriter(fw);// tiến hành tạo buffer
				for (Student student : students) {
					bw.write(student.MakeStringForCSV());// viết giữ liệu đã được custom sẵn để thêm vào file csv
		        	bw.newLine();
				}
		        bw.close();// đóng file và buffer
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
			    	// check nếu đúng trường hợp
			    {   
			    	var temp=students.get(i); // đổi chỗ nếu đúng trường hợp theo thuật tuấn bubble sort
			    	
			    	students.set(i, students.get(j));
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
    		if(student.getStudentNo()==StudentID)// kiểm tra Student ID trong ArrayList 
    			//nếu đúng thì gán biến st để lưu đúng id và dừng vòng lặp
    		{
    			st=student;
    			break;
    		}
    			
		}
    	if(st.getStudentNo()==0) // kiểm tra xem st đã có giá trị hay chưa nếu chưa thì in not found
    	{ 
    		System.out.println("\n Not Found!!");
    	}
    	else {
    		System.out.println(st.reportGrade()); // nếu có thì in student đã được lưu trước đó
    	}
    	
    }

	public void showAllStudent() {
		for (Student student : students) {
			System.out.println(student.reportGrade());// in toàn bộ student ra bằng foreach
		}
	
	}

	public void removeStudent() {
		System.out.print("\nEnter ID Number:");
		long StudentID= sc.nextLong();  // nhập Id vào
		final Student StudentRemove =  new Student();
		int index = 0; //lưu nhớ lại vị trí của student trong mảng
		for (Student student : students) {
			if(student.getStudentNo()==StudentID) // kiểm tra xem có đúng student Id không
				
			{   System.out.print("\nDo you want to detete ? Y/Yes or N/No ");
				String confirm= sc.next();
			     if(confirm.equals("yes")||confirm.equals("Yes")||confirm.equals("Y")||confirm.equals("y"))
			     {
			    	 StudentRemove.setStudentNo(student.getStudentNo());// lưu thông tin vào trước khi xóa
					 StudentRemove.setLastName(student.getLastName());
				     StudentRemove.setFirstName(student.getFirstName());
				     students.remove(index);// vì nhận được index chính student cần xóa và tiến hành xóa khỏi mảng
				     break;
			     }
			} 
			index++;
		}
					    
		if(StudentRemove.getStudentNo()!=0) // kiểm tra student có tồn tại k có thì in ra không thì in notfound
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
			 // try catch và đọc file Student.csv
		    String line;
		    while ((line = br.readLine()) != null) {
		    	
		        String[] values = line.split(",");// cắt thông tin từ string khi lấy dữ liệu theo dòng 
		        Student st;
		        // lưu trữ các thông tin cần thiết
		        String FisrtName=values[1];
		        String LastName=values[2];
		        Long StudentNo=Long.parseLong(values[3]);
		        int Day = Integer.parseInt(values[4]);
		        int Month=Integer.parseInt(values[5]);
		        int Year= Integer.parseInt(values[6]);
		        if(values[0].toString().trim().equals("C"))// kiểm tra đầy vào để xác định đúng loại student
		        {    // gán thêm các thông tin cần thiết
		        	double asm1=Double.parseDouble(values[7]);
		        	double asm2=Double.parseDouble(values[8]);
		        	double pracWork=Double.parseDouble(values[9]);
		            double finalExam=Double.parseDouble(values[10]);
		        	st= new Student_Common("1",1,FisrtName,LastName,StudentNo,Day,Month,Year,asm1,asm2,pracWork,finalExam);   	 
		        	 
		        }
		        else {
		        	// gán thêm các thông tin cần thiết
		        	double assignmentMark=Double.parseDouble(values[7]);
		        	double projectMark=Double.parseDouble(values[8]);
		        	double weeklyLab=Double.parseDouble(values[9]);
		            double finalMajor=Double.parseDouble(values[10]);
		        	 st= new Student_Major("1",1,FisrtName,LastName,StudentNo,Day,Month,Year,assignmentMark,projectMark,weeklyLab,0);
		        }
		    	// sau khi có dữ liệu thì thêm st vào  ArrayList
		        students.add(st);	
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
