package com.xiaoxi.study.controller;

import com.xiaoxi.study.entity.UserEsEntity;
import com.xiaoxi.study.service.EsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liuteng
 */
@RestController
@RequestMapping(value = "/es")
public class EsController {

    private EsService esService;

    public EsController(EsService esService) {
        this.esService = esService;
    }

    @PostMapping(value = "getUserByPhone")
    public List<UserEsEntity> getUserByPhone(String phone) {
        return esService.getUserByPhone(phone);
    }
}
