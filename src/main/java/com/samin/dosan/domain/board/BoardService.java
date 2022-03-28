package com.samin.dosan.domain.board;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.SecurityUtils;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.board.repository.BoardRepository;
import com.samin.dosan.domain.user.SessionUser;
import com.samin.dosan.web.dto.board.BoardSave;
import com.samin.dosan.web.dto.board.BoardUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> findAll(SearchParam searchParam, Pageable pageable) {
        return boardRepository.findAll(searchParam, pageable);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
    }

    @Transactional
    public Long save(BoardSave saveData) {
        SessionUser loginUser = SecurityUtils.getLoginUser();
        Board board = Board.of(saveData, loginUser.getUserNm());

        saveData.getFiles().stream().forEach(file -> {
            try {
                UploadFile uploadFile = FileUtils.fileUpload(file);
                board.addFile(uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        boardRepository.save(board);

        return board.getId();
    }

    @Transactional
    public void update(Long id, BoardUpdate updateData) {
        Board board = findById(id);
        board.update(updateData);
    }
}
