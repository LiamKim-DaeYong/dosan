package com.samin.dosan.domain.board;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.board.repository.BoardRepository;
import com.samin.dosan.web.api.admin.board.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    public void updateBoard(Board board) {
        Board findBoard = boardRepository.findById(board.getId()).
                orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));

        findBoard.updateBoard(board);
    }

    @Transactional
    public Long save(Board board, List<FileDto> newFiles, List<FileDto> deleteFiles) {
        board = boardRepository.save(board);
        board.delFiles(deleteFiles.stream()
                .map(BoardFile::toEntity)
                .collect(Collectors.toList()));
        board.addFiles(newFiles.stream()
                .map(BoardFile::toEntity)
                .collect(Collectors.toList()));

        return board.getId();
    }
}
