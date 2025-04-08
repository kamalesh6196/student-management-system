package student_course_management;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class Projectmain {
	static int id=1;
	static HashMap<String,Integer> jbatches=new HashMap<String, Integer>();
	static HashMap<String, Integer>pbatches=new HashMap<String, Integer>();
	static List<student>joinedmembers=new ArrayList<student>();
	static HashMap<String, HashMap<String, Integer>> batchbycourse=new HashMap<String, HashMap<String, Integer>>();
	static HashMap<String, Integer>coursepay=new HashMap<String, Integer>();
	static void initialize() {
		jbatches.put("weekdays", 3);
		jbatches.put("weekends",1);
		pbatches.put("weekdays", 3);
		pbatches.put("weekends",1);
		batchbycourse.put("JAVA", jbatches);
		batchbycourse.put("PYTHON", pbatches);
		 coursepay.put("JAVA", 15000);
		coursepay.put("PYTHON", 17000);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		initialize();
		Boolean flag=true;
		while(flag) {
			System.out.println("1.show available course\n2.join course\n3.show unpaid members\n4.pay pending amount\n5.confirm course completion\n6.exit");
			System.out.println("Enter ur choice");
			int choice=sc.nextInt();
			switch(choice){
				case 1:
					showavailable();
					break;
				case 2:
					student std=joincourse(sc);
					validatecourse(std);
					if(std.getAllocatedbatch()==null) {
						System.out.println("sorry seats not available");
						continue;
					}
					if(std.getPendingamount()==null) {
						System.out.println("sorry your transaction was declined");
						continue;						
					}
					joinedmembers.add(std);
					System.out.println("you have been joined sucessfully");
					id++;
					break;
				case 3:								
					System.out.println("List of unpaid members");
					joinedmembers.forEach(e->{						
					if(e.getPendingamount()>0) {						
						System.out.println(e);
					}					
					});				
					break;	
				case 4:
					System.out.println("enter your id");
					int stdid=sc.nextInt();
					sc.nextLine();
					boolean isavailable=joinedmembers.stream().anyMatch(e->e.getId()==stdid);
					if(!isavailable) {
						System.out.println("sorry you had not joined before");
					}else {
						paypendingamount(stdid,sc);
					}
					break;
				case 5:
					System.out.println("enter your id to confirm your completion");
					int stntid=sc.nextInt();
					sc.nextLine();
					coursecompletion(stntid);
					break;
				case 6:
					joinedmembers.forEach(e->{
						if(e.getStatus()=="COMPLETED") {
							System.out.println(e);
						}
					});
					System.out.println("Thanks for choosing my project");
					flag=false;
					break;	
			}
			sc.nextLine();		
			System.out.println("Do you wish to continue Y/N");
			String isprocess=sc.nextLine();
			if(isprocess.equalsIgnoreCase("N")) {
				System.out.println("Thanks for choosing my project");
				flag=false;
			}	
		}
	}	
	private static void paypendingamount(int stdid,Scanner sc) {
		// TODO Auto-generated method stub
		joinedmembers.forEach(e->{
			if(e.getId()==stdid) {
				if(e.getPendingamount()==0) {
					System.out.println("you had fully paid the amount");
				}
				else {
					System.out.println("you had a pending amount of "+e.getPendingamount());
					System.out.println("please enter the amount you wish to pay");
					int pay=sc.nextInt();
					sc.nextLine();
					if(pay<=e.getPendingamount()&&pay>0) {
						e.setPendingamount(e.getPendingamount()-pay);
					    
						System.out.println("you ad the pending amount of "+e.getPendingamount());
				
					}
					else {
						System.out.println("please pay the amount between 0 to "+e.getPendingamount());
					}
				}		
			}
		});
	}
	private static void coursecompletion(int stntid) {
		// TODO Auto-generated method stub
		boolean isavailable=joinedmembers.stream().anyMatch(e->e.getId()==stntid);
		if(!isavailable) {
			System.out.println("sorry you had not joined before");
		}
		boolean iscompleted=joinedmembers.stream().anyMatch(e->e.getId()==stntid&&e.getStatus()=="COMPLETED");
		if(iscompleted) {
			System.out.println("Invalid request.you already completed");
			return;
		}
		joinedmembers.forEach(e->{
			if(e.getPendingamount()==0) {
			if(e.getId()==stntid) {
				e.setStatus("COMPLETED");
			System.out.println("you had sucessfully completed the course ");
			}
			}else {
				System.out.println("pending amount of "+e.getPendingamount()+ " is there");
			}
		});
	}
	private static void validatecourse(student std) {
		// TODO Auto-generated method stub	
		batchbycourse.forEach((k,v)->{
			if(k.equals(std.getCourse().toString())&&v.get(std.getPrefbatch())>0){
				Integer currentseats=v.get(std.getPrefbatch());
				std.setAllocatedbatch(std.getPrefbatch());
				coursepay.forEach((k1,v1)->{
					if(k1.equals(std.getCourse().toString())&&std.getPaidamount()<=v1&&std.getPaidamount()>0) {
						std.setPendingamount(v1-std.getPaidamount());
					}
				});
				if(std.getAllocatedbatch()!=null&&std.getPendingamount()!=null) {
				if(std.getCourse().toString().equals("JAVA")) {
						jbatches.put(std.prefbatch, currentseats-1);
						batchbycourse.put(k, jbatches);
					}
					else {
						pbatches.put(std.prefbatch, currentseats-1);
						batchbycourse.put(k, pbatches);
					}
				}
			}
		});
	}             
	private static void showavailable() {
		// TODO Auto-generated method stub
		coursepay.forEach((k,v)->{
		System.out.println(k+":"+v);	
		});
	}
	private static student joincourse(Scanner sc) {
		// TODO Auto-generated method stub
		sc.nextLine();		
		System.out.println("enter your name");
		String name=sc.nextLine();		
		System.out.println("enter your course");
		String course=sc.nextLine();
		System.out.println("enter your preffered batch");
		String prefbatch=sc.nextLine();
		System.out.println("enter your installment amount");
		Integer amount=sc.nextInt();
		sc.nextLine();
		student std=new student(id,name,prefbatch,Course.valueOf(course),"NEW",LocalDateTime.now(),amount);
		return std;
	}
	}