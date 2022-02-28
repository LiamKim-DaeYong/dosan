package com.samin.dosan.domain.board;

import com.samin.dosan.domain.board.repository.BoardRepository;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> findAll(SearchParam searchParam) {
        return boardRepository.findAll(searchParam);
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
    public Long save(Board board) {
        return boardRepository.save(board).getId();
    }
}
