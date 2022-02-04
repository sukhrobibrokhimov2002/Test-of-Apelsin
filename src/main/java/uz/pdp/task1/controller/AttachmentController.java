package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.task1.entity.attachment.Attachment;
import uz.pdp.task1.payload.response.Result;
import uz.pdp.task1.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) throws IOException {
        Result result = attachmentService.uploadImage(request);
        return result;
    }


    @GetMapping
    public Page<Attachment> getAll(@RequestParam Integer page) {
        Page<Attachment> all = attachmentService.getAll(page);
        return all;
    }

    @GetMapping("/{id}")
    public Attachment getOneById(@PathVariable Integer id) {
        Attachment oneById = attachmentService.getOneById(id);
        return oneById;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = attachmentService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, MultipartHttpServletRequest request) throws IOException {

        Result edit = attachmentService.edit(id, request);
        return edit;
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.download(id, response);
    }


}
