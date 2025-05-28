public class Course {
    private String name;
    private String id;
    private Teacher teacher;

    public Course(String name, String id) {
        this.name = name;
        this.id = id;
        this.teacher = null;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}