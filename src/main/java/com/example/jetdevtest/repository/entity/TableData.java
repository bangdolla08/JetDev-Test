package com.example.jetdevtest.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "table_data")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableData {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "file_library_id")
    private FileLibrary fileLibraryId;

}
