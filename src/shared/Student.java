package shared;

import java.io.Serializable;

/* 회원 정보 구조체
 * 우리의 회원은 Student타입으로 이름, 학교, 학과정보를 가지고 있다.
 * 회원가입시 이름, 학교, 학과정보를 반드시 기입하여 가입해야한다.
 *   생성자 1. public Student(String ID, String PW, String name, String school, String department) 를 이용해 회원가입을 받자.
 *   생성자 2. public Student(String ID, String PW, String name, String school, String department, int myImage, String myState) 를 이용해 PreStudentDB 가짜 학생을 하드코딩하자.
 */
public class Student implements Serializable{
    /* Fields */
    private String ID; // 로그인 시 필요한 아이디
    private String PW; // 로그인 시 필요한 비밀번호
    private String name; // 이름
    private String school; // 학교
    private String department; // 학과
    private int myImage; // 프로필사진 // : 근데 왜 int이지?
    private String myState; // 프로필명(상태메시지)
    private String loginResult; // 로그인시도 결과 (로그인 시도로 서버가 저장)

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
     * 회원가입 구현시 이 첫번째 생성자를 이용한다.
     * 단, 이 생성자에는 '프로필사진(myImage)' 와 '프로필명(myState)' 는 두지 않기로 한다.
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
    
    /* 클라이언트에서 로그인 시도시 입력받은 생성자로 서버에서는 필요 없음 (Serial맞추기 위해 작성됨) */
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