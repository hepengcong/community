package com.practice.community.controller;

import com.practice.community.Enum.ResultCode;
import com.practice.community.dto.ReplyDTO;
import com.practice.community.entity.Reply;
import com.practice.community.service.ReplyService;
import com.practice.community.util.JsonResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ReplyController {
    @Resource
    ReplyService replyService;

    @PostMapping("/reply")
    public JsonResult reply(@RequestBody Reply reply) {
        replyService.insert(reply);
        return new JsonResult(true, ResultCode.SUCCESS);

    }

    @GetMapping("/reply/delete")
    public JsonResult delete(@RequestBody Reply reply) {
        replyService.delete(reply);
        return new JsonResult(true, ResultCode.SUCCESS);

    }

    @GetMapping("/replies")
    public JsonResult replies(@RequestBody ReplyDTO reply) {
        return new JsonResult(true, ResultCode.SUCCESS, replyService.list(reply));

    }

    @RequestMapping("/recent")
    public JsonResult recent(@RequestBody ReplyDTO reply) {
        return new JsonResult(true, ResultCode.SUCCESS, replyService.listByTime(reply));
    }
}
