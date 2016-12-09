package shared;

import java.io.Serializable;

/* ȸ�� ���� ����ü
 * �츮�� ȸ���� StudentŸ������ �̸�, �б�, �а������� ������ �ִ�.
 * ȸ�����Խ� �̸�, �б�, �а������� �ݵ�� �����Ͽ� �����ؾ��Ѵ�.
 *   ������ 1. public Student(String ID, String PW, String name, String school, String department) �� �̿��� ȸ�������� ����.
 *   ������ 2. public Student(String ID, String PW, String name, String school, String department, int myImage, String myState) �� �̿��� PreStudentDB ��¥ �л��� �ϵ��ڵ�����.
 */
public class Student implements Serializable{
    /* Fields */
    private String ID; // �α��� �� �ʿ��� ���̵�
    private String PW; // �α��� �� �ʿ��� ��й�ȣ
    private String name; // �̸�
    private String school; // �б�
    private String department; // �а�
    private int myImage; // �����ʻ��� // : �ٵ� �� int����?
    private String myState; // �����ʸ�(���¸޽���)
    private String loginResult; // �α��νõ� ��� (�α��� �õ��� ������ ����)

    /* getters and setters */
    public String getID() { return this.ID; }
    public void setID(String ID) { this.ID = ID; }
    public String getPW() { return this.PW; }
    public void setPW(String PW) { this.PW = PW; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public String getSchool() { return this.school; }
    public void setSchool(String school) { this.school = school; }
    public String getDepartment() { return this.department; }
    public void setDepartment(String department) { this.department = department; }
    public int getMyImage() { return this.myImage; }
    public void setMyImage(int myImage) { this.myImage = myImage; }
    public String getMyState() { return this.myState; }
    public void setMyState(String myState) { this.myState = myState; }
    public String getLoginResult() { return this.loginResult; }
    public void setLoginResult(String loginResult){ this.loginResult = loginResult; }

    /* Constructors
     * ȸ������ ������ �� ù��° �����ڸ� �̿��Ѵ�.
     * ��, �� �����ڿ��� '�����ʻ���(myImage)' �� '�����ʸ�(myState)' �� ���� �ʱ�� �Ѵ�.
     */
    public Student(String ID, String PW, String name, String school, String department, int myImage, String myState, String loginResult){
        this.ID = ID;
        this.PW = PW;
        this.name = name;
        this.school = school;
        this.department = department;
        this.myImage = myImage;
        this.myState = myState;
        this.loginResult = loginResult;
    }
    
    /* Ŭ���̾�Ʈ���� �α��� �õ��� �Է¹��� �����ڷ� ���������� �ʿ� ���� (Serial���߱� ���� �ۼ���) */
    public Student(String ID, String PW){
        this.ID = ID;
        this.PW = PW;
        this.name = "";
        this.school = "";
        this.department = "";
        this.myImage = 0;
        this.myState = "";
        this.loginResult = "";
    }
    
    public Student(){
    	
    }
    
	@Override
	public String toString() {
		return "Student [ID=" + ID + ", PW=" + PW + ", name=" + name + ", school=" + school + ", department="
				+ department + ", myImage=" + myImage + ", myState=" + myState + ", loginResult=" + loginResult + "]";
	}
    
    
}