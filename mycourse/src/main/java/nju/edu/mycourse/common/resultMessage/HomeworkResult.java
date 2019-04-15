package nju.edu.mycourse.common.resultMessage;

public enum HomeworkResult {
    homeworkPublishSuccess("作业发布成功"),
    homeworkHasBeenPublished("作业已发布"),
    homeworkPublishFail("作业发布失败"),

    homeworkModifySuccess("作业修改成功"),
    homeworkModifyFail("作业修改失败"),

    homeworkSubmitSuccess("作业提交成功"),
    homeworkSubmitFail("作业提交失败"),

    homeworkScoreUploadSuccess("作业成绩上传成功"),
    homeworkScoreUploadFail("作业成绩上传失败")
    ;

    private String literal;
    HomeworkResult(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return this.literal;
    }
}
