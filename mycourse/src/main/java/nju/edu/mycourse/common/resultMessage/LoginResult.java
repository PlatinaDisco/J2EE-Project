package nju.edu.mycourse.common.resultMessage;

public enum LoginResult {
    wrongNjuEmailOrPassword("邮箱或密码错误"),
    loginSuccess("登陆成功"),
    loginFail("登录失败");

    private String literal;
    LoginResult(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return this.literal;
    }
}
