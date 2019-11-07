package bean;

public class Room_info {
	private int room_no;
	private String name;
	private String device_num;//总设备数量
	private String device_capacity;//容量
	private String device_describe;//详情信息描述
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDevice_num() {
		return device_num;
	}
	public void setDevice_num(String device_num) {
		this.device_num = device_num;
	}
	public String getDevice_capacity() {
		return device_capacity;
	}
	public void setDevice_capacity(String device_capacity) {
		this.device_capacity = device_capacity;
	}
	public String getDevice_describe() {
		return device_describe;
	}
	public void setDevice_describe(String device_describe) {
		this.device_describe = device_describe;
	}
}
