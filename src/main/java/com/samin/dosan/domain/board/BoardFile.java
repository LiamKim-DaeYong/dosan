package com.samin.dosan.domain.board;

import com.samin.dosan.domain.file.Files;
import com.samin.dosan.web.api.admin.board.FileDto;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.function.Function;

@Entity
@NoArgsConstructor
public class BoardFile extends Files {

    public static BoardFile toEntity(FileDto fileDto) {

        // 확장자 (마지막 닷(.) 이후 문자열)
        Function<String, String> getExtension = (originalName) -> {
            int findDot = originalName.lastIndexOf(".");
            return (findDot != -1) ? originalName.substring(findDot) : "";
        };

//        BoardFile files = BoardFile.builder()
//                .id(fileDto.getId())
//                .originFilename(fileDto.getOriginalName())
//                .storeFileName(fileDto.getStorageName())
//                .contentType(fileDto.getContentType())
//                .extension(getExtension.apply(fileDto.getOriginalName()))
//                .fileSize(fileDto.getByteSize())
//                .build();
//        return files;
        return null;
    }
}
