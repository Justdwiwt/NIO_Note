package main.queue;

@SuppressWarnings("unused")
public class Student implements Comparable<Student> {

    private String name;
    private int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {

        //--指定排序规则：按分数做降序排序
        //--如果按分数做升序排序：this.score-o.score
        return o.score - this.score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", score=" + score + "]";
    }

}
