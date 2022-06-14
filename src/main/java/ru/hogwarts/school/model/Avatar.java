package ru.hogwarts.school.model;

import javax.persistence.*;

@Entity
public class Avatar {
   @Id
   @GeneratedValue
   private Long Id;
   private String filePath;
   private long fileSize;
   private String mediaTipe;

   @Lob
   private byte[] data;

   @OneToOne
   private Student student;

   public Long getId() {
      return Id;
   }

   public void setId(Long id) {
      Id = id;
   }

   public String getFilePath() {
      return filePath;
   }

   public void setFilePath(String fileName) {
      this.filePath = fileName;
   }

   public long getFileSize() {
      return fileSize;
   }

   public void setFileSize(long fileSize) {
      this.fileSize = fileSize;
   }

   public String getMediaTipe() {
      return mediaTipe;
   }

   public void setMediaTipe(String mediaTipe) {
      this.mediaTipe = mediaTipe;
   }

   public byte[] getData() {
      return data;
   }

   public void setData(byte[] data) {
      this.data = data;
   }

   public Student getStudent() {
      return student;
   }

   public void setStudent(Student student) {
      this.student = student;
   }
}
