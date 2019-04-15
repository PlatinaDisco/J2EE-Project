package nju.edu.mycourse.common.resultMessage;

public enum ModificationResult {
    userInfoModificationSuccess("用户信息修改成功"),
    userInfoModificationFail("用户信息修改失败");

    private String literal;
    ModificationResult(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return this.literal;
    }
}
