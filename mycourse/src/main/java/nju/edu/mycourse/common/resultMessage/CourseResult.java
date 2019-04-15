package nju.edu.mycourse.common.resultMessage;

public enum CourseResult {
    courseStartSuccess("课程创建成功"),
    courseStartFail("课程创建失败"),
    courseHasBeenStarted("课程已被创建"),
    courseApproved("课程通过"),
    courseApprovedFail("课程通过失败"),
    courseDenial("课程未被通过"),

    testScoreFileUploadSuccess("考试成绩上传成功"),
    testScoreFileUploadFail("考试成绩上传失败"),
    totalGradeFileUploadSuccess("总评成绩上传成功"),
    totalGradeFileUploadFail("总评成绩上传失败"),

    courseInfoModificationSuccess("课程信息修改成功"),
    courseInfoModificationFail("课程信息修改失败"),

    courseSelectSuccess("选课成功"),
    courseSelectFail("选课失败"),
    courseHasBeenSelected("该课程已选"),
    courseLimitationReachesMax("选课人数已满"),
    courseDropSuccess("退课成功"),
    courseDropFail("退课失败"),
    ;

    private String literal;
    CourseResult(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return this.literal;
    }
}
