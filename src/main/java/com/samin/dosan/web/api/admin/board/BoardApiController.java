package com.samin.dosan.web.api.admin.board;

import com.samin.dosan.domain.board.BoardService;
import com.samin.dosan.web.dto.board.BoardSave;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/board")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/add")
    public ResponseEntity saveBoard(Principal principal,
                                    @RequestPart(value = "content") BoardSave boardSave,
                                    @RequestPart(value = "delete", required = false) List<FileDto> deleteFiles,
                                    @RequestPart(value = "file", required = false) List<MultipartFile> uploadFiles) {

        boardSave.setWriter(principal.getName()); // 작성자
//        Board board = boardSave.toEntity();

        List<FileDto> newFiles = FileUtil.saveFiles(uploadFiles, "board");
//        boardService.save(board, newFiles, deleteFiles);
        //deleteFiles.stream().forEach(deleteFile -> FileUtil.delete(deleteFile.getSubDir(), deleteFile.getStorageName()));

        return ResponseEntity.ok().build();
    }
}
