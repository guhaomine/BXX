package bean;

public class Exp_item {
	private int exp_item_no;
	private String exp_item_hour;//实验学时数
	private String exp_type;
	private String exp_item_name;//实验名称
	private String fk_course_no;//课程编号
	public int getExp_item_no() {
		return exp_item_no;
	}
	public void setExp_item_no(int exp_item_no) {
		this.exp_item_no = exp_item_no;
	}
	public String getExp_item_hour() {
		return exp_item_hour;
	}
	public void setExp_item_hour(String exp_item_hour) {
		this.exp_item_hour = exp_item_hour;
	}
	public String getExp_type() {
		return exp_type;
	}
	public void setExp_type(String exp_type) {
		this.exp_type = exp_type;
	}
	public String getExp_item_name() {
		return exp_item_name;
	}
	public void setExp_item_name(String exp_item_name) {
		this.exp_item_name = exp_item_name;
	}
	public String getFk_course_no() {
		return fk_course_no;
	}
	public void setFk_course_no(String fk_course_no) {
		this.fk_course_no = fk_course_no;
	}
	
}
