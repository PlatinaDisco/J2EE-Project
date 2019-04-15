package nju.edu.mycourse.common.resultMessage;

public enum ApprovalResult {
    approve("通过"),
    disapprove("未通过");

    private String literal;
    ApprovalResult(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return this.literal;
    }
}
