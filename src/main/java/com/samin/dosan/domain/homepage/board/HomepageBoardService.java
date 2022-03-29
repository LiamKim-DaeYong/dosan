package com.samin.dosan.domain.homepage.board;

import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.homepage.type.BoardType;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.board.repository.HomepageBoardRepository;
import com.samin.dosan.web.dto.homepage.board.HomepageBoardSave;
import com.samin.dosan.web.dto.homepage.board.HomepageBoardUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomepageBoardService {

    private final HomepageBoardRepository homepageBoardRepository;

    public Page<HomepageBoard> findAll(SearchParam searchParam, Pageable pageable, BoardType boardType) {
        return homepageBoardRepository.findAll(searchParam, pageable, boardType);
    }

    public HomepageBoard findById(Long id) {
        return homepageBoardRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(HomepageBoardSave saveData, BoardType boardType) {
        HomepageBoard board = HomepageBoard.of(saveData, boardType);

        saveData.getFiles().stream().forEach(file -> {
            try {
                UploadFile uploadFile = FileUtils.fileUpload(file);
                board.addFile(uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        homepageBoardRepository.save(board);

        return board.getId();
    }

    @Transactional
    public void update(Long id, HomepageBoardUpdate updateData) {
        HomepageBoard board = findById(id);
        board.update(updateData);
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
