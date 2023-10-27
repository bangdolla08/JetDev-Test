package com.example.jetdevtest.repository.entity;

import com.example.jetdevtest.repository.entity.enums.FileLoadStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "file_library")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileLibrary {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private LocalDateTime createDate;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private FileLoadStatusEnum status = FileLoadStatusEnum.PENDING;

}
