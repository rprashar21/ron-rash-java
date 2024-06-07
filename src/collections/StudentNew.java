package collections;

import java.util.Objects;

public class StudentNew {
    private String name;
    private int age;
    private String code;

    public StudentNew(final String name, final int age, final String code) {
        this.name = name;
        this.age = age;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "StudentNew{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final StudentNew that = (StudentNew) o;
        return age == that.age && Objects.equals(name, that.name) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, code);
    }
}
