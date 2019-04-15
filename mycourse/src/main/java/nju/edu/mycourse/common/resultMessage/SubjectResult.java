package nju.edu.mycourse.common.resultMessage;

public enum SubjectResult {
    subjectStartSuccess("课程创建成功"),
    subjectHasBeenStarted("课程已被创建"),
    subjectStartFail("课程创建失败"),
    subjectApproved("课程通过"),
    subjectApprovedFail("课程通过失败"),
    subjectDenial("课程被拒绝通过"),
    subjectMaterialUploadSuccess("课件上传成功"),
    subjectMaterialUploadFail("课件上传失败"),
    ;

    private String literal;
    SubjectResult(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return this.literal;
    }
}
