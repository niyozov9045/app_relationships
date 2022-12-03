package uz.pdp.springbootapprelationships.dto;

import lombok.Data;

@Data
public class StudentDto { //MALUMOTLARNI TAHSISH UCHUN XIZMAT QILADI

    private String firstName;
    private Integer lastName;
    private String city;
    private String district;
    private String street;
    private Integer groupId;
}
