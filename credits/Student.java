import java.util.HashMap;
import java.util.Map;

public class Student {
    public String name, surname;
    public int grade;
    public Student(String name, String surname, int grade){
        this.name = name;
        this.surname = surname;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(obj.getClass() != getClass())
            return false;
        Student student = (Student) obj;
        return  student.name.equals(name)       &&
                student.surname.equals(surname) &&
                student.grade == grade;
    }

    int stringHash(String str){
        int hash = 0;
        for(int i=0; i<str.length(); i++){
            hash *= 31;
            hash += str.charAt(i);
        }
        return hash;
    }

    @Override
    public int hashCode(){
        return 31*31*stringHash(name) + 31*stringHash(surname) + grade;
    }

    @Override
    public String toString(){
        return name + " " + surname + ", " + grade + "th grade";
    }
}


class StudentTest{
    static HashMap<Student, Integer> count(Student[] students){
        HashMap<Student, Integer> res = new HashMap<Student, Integer>();
        for(Student student: students){
            if(res.containsKey(student))
                res.put(student, res.get(student) + 1);
            else
                res.put(student, 1);
        }
        return res;
    }
    public static void main(String[] args){
        Student students[] = {
                new Student("Vasya", "Petrov", 8),
                new Student("Sergey", "Kazakov", 13),
                new Student("Vasya", "Petrov", 8),
                new Student("Harry", "Potter", 6)
        };
        HashMap<Student, Integer> mappedStudents = count(students);

        for(Map.Entry<Student, Integer> entry: mappedStudents.entrySet()){
            System.out.println(entry.getKey() + " x" + entry.getValue() );
        }
    }
}
