package utility;

import java.util.ArrayList;

import shared.Student;

/* 회원가입 없이 약 10명의 가상 회원을 하드코딩해 DB를 제공하는 클래스.
 *  이용 1. StudentActivity에서 친구목록을 이 PreStudentDB를 참조해 보여주자.
 *  이용 2. Login시 이용 할 수 있도록 해보자.
 *          (11월17일 현재 개발단계에서는 서버에서 아이디, 비밀번호 체크없이 클라이언트 자체에서 하기로함.)
 */
public class RegisteredStudentDB {
    /* Fields */
    private ArrayList<Student> registeredStudents; // PreStudentDB ----*-> Student

    /* getters and setters */
    public ArrayList<Student> getRegisteredStudents(){
        return this.registeredStudents;
    }

    /* Constructors */
    public RegisteredStudentDB(){
    	registeredStudents = new ArrayList<Student>();
        //if (!fakeStudents.isEmpty()) { fakeStudents.clear(); } // students 초기화
    	registeredStudents.add(new Student("admin", "admin", "관리자", "한성대학교", "컴퓨터공학부", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("aaa", "aaa", "임꺽정", "성균관대학교", "경영학과", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("bbb", "bbb", "황진이", "성신여자대학교", "식품영양학과", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("ccc", "ccc", "어우동", "숭실대학교", "국문학과", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("ddd", "ddd", "김논개", "중앙대학교", "신문방송학과", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("eee", "eee", "아무개", "경희대학교", "수학과", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("fff", "fff", "백승찬", "한성대학교", "컴퓨터공학부", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("ggg", "ggg", "김보현", "한성대학교", "컴퓨터공학부", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("hhh", "hhh", "정인환", "한성대학교", "컴퓨터공학부", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("iii", "iii", "이상한", "한성대학교", "컴퓨터공학부", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("jjj", "jjj", "김철수", "한성대학교", "지식정보학과", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("kkk", "kkk", "김영희", "국민대학교", "기계공학과", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("lll", "lll", "이눅스", "국민대학교", "기계공학과", 0, "프로필을 설정해주세요", ""));
    	registeredStudents.add(new Student("mmm", "mmm", "류닉스", "국민대학교", "기계공학과", 0, "프로필을 설정해주세요", ""));
    }
}