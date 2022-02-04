package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.pdp.task1.entity.attachment.Attachment;


public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    boolean existsByOriginalName(String originalFilename);
}
