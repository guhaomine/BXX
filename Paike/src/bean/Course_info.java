package bean;

public class Course_info {
	private int course_no;
	private String course_name;
	private String course_hour;//总学时
	private String course_exp_hour;//实验总学时
	private String course_gredit;//学分
	public int getCourse_no() {
		return course_no;
	}
	public void setCourse_no(int course_no) {
		this.course_no = course_no;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_hour() {
		return course_hour;
	}
	public void setCourse_hour(String course_hour) {
		this.course_hour = course_hour;
	}
	public String getCourse_exp_hour() {
		return course_exp_hour;
	}
	public void setCourse_exp_hour(String course_exp_hour) {
		this.course_exp_hour = course_exp_hour;
	}
	public String getCourse_gredit() {
		return course_gredit;
	}
	public void setCourse_gredit(String course_gredit) {
		this.course_gredit = course_gredit;
	}
	
}
