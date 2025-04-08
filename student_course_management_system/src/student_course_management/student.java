package student_course_management;
import java.time.LocalDateTime;
enum Course{
	JAVA,
	PYTHON
}
public class student {
	int id;
	String name;
	String prefbatch;
	String allocatedbatch;
	Course course;
	String status;
	LocalDateTime joineddate;
	Integer paidamount;
	Integer pendingamount;
	@Override
	public String toString() {
		return "student [id=" + id + ", name=" + name + ", prefbatch=" + prefbatch + ", allocatedbatch="
				+ allocatedbatch + ", course=" + course + ", status=" + status + ", joineddate=" + joineddate
				+ ", paidamount=" + paidamount + ", pendingamount=" + pendingamount + "]";
	}
	public Integer getPendingamount() {
		return pendingamount;
	}
	public void setPendingamount(Integer pendingamount) {
		this.pendingamount = pendingamount;
	}
	public student(int id, String name, String prefbatch, Course course, String status, LocalDateTime joineddate,
			Integer paidamount) {
		super();
		this.id = id;
		this.name = name;
		this.prefbatch = prefbatch;
		this.course = course;
		this.status = status;
		this.joineddate = joineddate;
		this.paidamount = paidamount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrefbatch() {
		return prefbatch;
	}
	public void setPrefbatch(String prefbatch) {
		this.prefbatch = prefbatch;
	}
	public String getAllocatedbatch() {
		return allocatedbatch;
	}
	public void setAllocatedbatch(String allocatedbatch) {
		this.allocatedbatch = allocatedbatch;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getJoineddate() {
		return joineddate;
	}
	public void setJoineddate(LocalDateTime joineddate) {
		this.joineddate = joineddate;
	}
	public Integer getPaidamount() {
		return paidamount;
	}
	public void setPaidamount(Integer paidamount) {
		this.paidamount = paidamount;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub/	
	}
}