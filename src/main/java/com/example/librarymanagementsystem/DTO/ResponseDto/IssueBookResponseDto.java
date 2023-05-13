package com.example.librarymanagementsystem.DTO.ResponseDto;

import com.example.librarymanagementsystem.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssueBookResponseDto {
    private String transectionNumber;
    private TransactionStatus transectionStatus;
    private String bookName;
}
