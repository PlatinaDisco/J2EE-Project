package nju.edu.mycourse.common.resultMessage;

public enum LogoutResult {
    wrongNjuEmailOrPassword("邮箱或者密码错误"),
    logoutSuccess("注销成功"),
    logoutFail("注销失败");

    private String literal;
    LogoutResult(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return this.literal;
    }
}
