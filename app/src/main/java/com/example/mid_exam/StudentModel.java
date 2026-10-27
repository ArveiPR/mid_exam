package com.example.mid_exam;

public class StudentModel {
    private Integer id;
    private String studentName;
    private String studentId;
    private int isEnrolled; // 1 jika terdaftar (dicentang), 0 jika tidak

    // Constructor untuk data baru (belum ada ID dari database)
    public StudentModel(String studentName, String studentId, int isEnrolled) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.isEnrolled = isEnrolled;
    }

    // Constructor untuk data yang sudah ada di database (lengkap dengan ID)
    public StudentModel(Integer id, String studentName, String studentId, int isEnrolled) {
        this.id = id;
        this.studentName = studentName;
        this.studentId = studentId;
        this.isEnrolled = isEnrolled;
    }

    // Getter dan Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public int getIsEnrolled() { return isEnrolled; }
    public void setIsEnrolled(int isEnrolled) { this.isEnrolled = isEnrolled; }
}