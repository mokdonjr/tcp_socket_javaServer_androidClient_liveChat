package utility;

import java.util.ArrayList;

import shared.Student;

/* ȸ������ ���� �� 10���� ���� ȸ���� �ϵ��ڵ��� DB�� �����ϴ� Ŭ����.
 *  �̿� 1. StudentActivity���� ģ������� �� PreStudentDB�� ������ ��������.
 *  �̿� 2. Login�� �̿� �� �� �ֵ��� �غ���.
 *          (11��17�� ���� ���ߴܰ迡���� �������� ���̵�, ��й�ȣ üũ���� Ŭ���̾�Ʈ ��ü���� �ϱ����.)
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
        //if (!fakeStudents.isEmpty()) { fakeStudents.clear(); } // students �ʱ�ȭ
    	registeredStudents.add(new Student("admin", "admin", "������", "�Ѽ����б�", "��ǻ�Ͱ��к�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("aaa", "aaa", "�Ӳ���", "���հ����б�", "�濵�а�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("bbb", "bbb", "Ȳ����", "���ſ��ڴ��б�", "��ǰ�����а�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("ccc", "ccc", "��쵿", "���Ǵ��б�", "�����а�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("ddd", "ddd", "���", "�߾Ӵ��б�", "�Ź�����а�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("eee", "eee", "�ƹ���", "������б�", "���а�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("fff", "fff", "�����", "�Ѽ����б�", "��ǻ�Ͱ��к�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("ggg", "ggg", "�躸��", "�Ѽ����б�", "��ǻ�Ͱ��к�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("hhh", "hhh", "����ȯ", "�Ѽ����б�", "��ǻ�Ͱ��к�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("iii", "iii", "�̻���", "�Ѽ����б�", "��ǻ�Ͱ��к�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("jjj", "jjj", "��ö��", "�Ѽ����б�", "���������а�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("kkk", "kkk", "�迵��", "���δ��б�", "�����а�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("lll", "lll", "�̴���", "���δ��б�", "�����а�", 0, "�������� �������ּ���", ""));
    	registeredStudents.add(new Student("mmm", "mmm", "���н�", "���δ��б�", "�����а�", 0, "�������� �������ּ���", ""));
    }
}