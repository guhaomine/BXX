package bean;

public class App_info {
	private int app_no; //排课序号
	private int fk_course_no;//课程编号
	private int course_idx;//课程序号
	private int app_stu_num;//上课人数
	private int fk_class_no;//班级编号
	private int fk_teacher_no;//教工号
	private String app_term;//学期
	private int week;
	private int day;
	private int jie;
	public int getApp_no() {
		return app_no;
	}
	public void setApp_no(int app_no) {
		this.app_no = app_no;
	}
	public int getFk_course_no() {
		return fk_course_no;
	}
	public void setFk_course_no(int fk_course_no) {
		this.fk_course_no = fk_course_no;
	}
	public int getCourse_idx() {
		return course_idx;
	}
	public void setCourse_idx(int course_idx) {
		this.course_idx = course_idx;
	}
	public int getApp_stu_num() {
		return app_stu_num;
	}
	public void setApp_stu_num(int app_stu_num) {
		this.app_stu_num = app_stu_num;
	}
	public int getFk_class_no() {
		return fk_class_no;
	}
	public void setFk_class_no(int fk_class_no) {
		this.fk_class_no = fk_class_no;
	}
	public int getFk_teacher_no() {
		return fk_teacher_no;
	}
	public void setFk_teacher_no(int fk_teacher_no) {
		this.fk_teacher_no = fk_teacher_no;
	}
	public String getApp_term() {
		return app_term;
	}
	public void setApp_term(String app_term) {
		this.app_term = app_term;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getJie() {
		return jie;
	}
	public void setJie(int jie) {
		this.jie = jie;
	}
	
	
}
