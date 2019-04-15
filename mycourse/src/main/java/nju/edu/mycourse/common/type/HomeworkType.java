package nju.edu.mycourse.common.type;

public enum HomeworkType {
    word("word"),
    pdf("pdf"),
    zip("zip");

    private String literal;
    HomeworkType(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
