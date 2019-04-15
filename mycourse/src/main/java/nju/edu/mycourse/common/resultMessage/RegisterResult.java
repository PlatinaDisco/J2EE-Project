package nju.edu.mycourse.common.resultMessage;

public enum RegisterResult {
    registerSuccess("注册成功"),
    registerFail("注册失败"),
    AccountHasBeenRegistered("该账号已注册"),
    sendVerificationCodeSuccess("激活验证码已发送至您的邮箱，请前往获取验证码"),
    sendVerificationCodeFail("激活验证码发送失败，请稍后再试"),
    nullOrWrongEmailFormat("无指定邮箱或邮箱格式错误，请使用正确邮箱格式注册"),
    wrongOrDueVerification("验证码错误或者已失效");

    private String literal;
    RegisterResult(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return this.literal;
    }
}
