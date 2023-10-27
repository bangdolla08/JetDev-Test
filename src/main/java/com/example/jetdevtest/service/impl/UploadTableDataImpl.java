package com.example.jetdevtest.service.impl;

import com.example.jetdevtest.exception.NotFoundException;
import com.example.jetdevtest.repository.FileLibraryRepository;
import com.example.jetdevtest.repository.TableDataRepository;
import com.example.jetdevtest.repository.entity.FileLibrary;
import com.example.jetdevtest.repository.entity.TableData;
import com.example.jetdevtest.service.FileService;
import com.example.jetdevtest.service.UploadTableData;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadTableDataImpl implements UploadTableData {

    private final FileService fileService;
    private final FileLibraryRepository fileLibraryRepository;
    private final TableDataRepository tableDataRepository;

    @SneakyThrows
    @Override
    public void readFromList() {
        FileLibrary fileLibrary = fileLibraryRepository.findById("db6af335-2c2e-42f3-9f97-9c2587b575b8")
                .orElseThrow(() -> new NotFoundException("File Not Found"));
        Resource resource = fileService.loadFileData(fileLibrary);
        XSSFWorkbook wb = new XSSFWorkbook(resource.getInputStream());
        XSSFSheet ws = wb.getSheetAt(0);
        Integer nameNumber = 0;
        Integer titleNumber = 0;
        Integer valueNumber = 0;
        Boolean isFirstRow = true;
        List<TableData> tableDataList = new ArrayList<>();
        for (Row row : ws) {
            Iterator<Cell> cellIterator = row.cellIterator();
            Integer i = 0;
            TableData.TableDataBuilder tableDataBuilder = TableData.builder();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (isFirstRow) {
                    if (cell.getStringCellValue().toLowerCase().matches(".*name*."))
                        nameNumber = i;
                    if (cell.getStringCellValue().toLowerCase().matches(".*title*."))
                        titleNumber = i;
                    if (cell.getStringCellValue().toLowerCase().matches(".*value*."))
                        valueNumber = i;
                } else {
                    if (Objects.equals(i, nameNumber)) {
                        tableDataBuilder.name(cell.getStringCellValue());
                    } else if (Objects.equals(i, titleNumber)) {
                        tableDataBuilder.title(cell.getStringCellValue());
                    } else if (Objects.equals(i, valueNumber)) {
                        tableDataBuilder.value(cell.getStringCellValue());
                    }
                }
                tableDataBuilder.fileLibraryId(fileLibrary);
                TableData tableData = tableDataBuilder.build();
                if (isHaveData(tableData.getName()) &&
                        isHaveData(tableData.getTitle()) &&
                        isHaveData(tableData.getValue())
                ) {
                    tableData.setId(UUID.randomUUID().toString());
                    tableDataList.add(tableData);
                }
                i++;
            }
            if (isFirstRow)
                isFirstRow = false;
        }
        tableDataRepository.saveAll(tableDataList);
    }

    private Boolean isHaveData(String string) {
        if (string == null)
            return false;
        return !string.isBlank() && !string.isEmpty();
    }
}
