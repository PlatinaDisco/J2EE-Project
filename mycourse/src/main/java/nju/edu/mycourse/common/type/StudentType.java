package nju.edu.mycourse.common.type;

public enum StudentType {
    undergraduate("本科生"),
    graduate("研究生"),
    doctoral("博士生");

    private String literal;
    StudentType(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return this.literal;
    }
}


