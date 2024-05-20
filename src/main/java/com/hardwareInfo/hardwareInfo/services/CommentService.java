package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.CommentEntity;
import com.hardwareInfo.hardwareInfo.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentEntity createEntity(CommentEntity commentEntity){
       return commentRepository.save(commentEntity);
    }
    @PutMapping
    public Optional<CommentEntity> updateComment(CommentEntity commentEntity, Long id){
        return commentRepository.findById(id).map(comment -> {
            comment.setGraphicsCard(commentEntity.getGraphicsCard());
            comment.setText(commentEntity.getText());
            comment.setCreatedBy(commentEntity.getCreatedBy());
         return comment;
        });
    }

    @DeleteMapping
    public void deleteComment(Long id){


        commentRepository.deleteById(id);
    }
}
