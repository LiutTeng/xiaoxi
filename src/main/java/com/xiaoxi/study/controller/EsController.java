package com.xiaoxi.study.controller;

import com.xiaoxi.study.common.Result;
import com.xiaoxi.study.dto.request.GetUserByNameRequestDTO;
import com.xiaoxi.study.dto.request.GetUserByPhoneRequestDTO;
import com.xiaoxi.study.dto.request.SearchUserRequestDTO;
import com.xiaoxi.study.dto.response.GetUserByNameResponseDTO;
import com.xiaoxi.study.dto.response.GetUserByPhoneResponseDTO;
import com.xiaoxi.study.dto.response.SearchUserResponseDTO;
import com.xiaoxi.study.entity.UserEsEntity;
import com.xiaoxi.study.service.EsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public Result<GetUserByPhoneResponseDTO> getUserByPhone(@Valid @RequestBody GetUserByPhoneRequestDTO request) {
        return new Result<>(esService.getUserByPhone(request));
    }

    @PostMapping(value = "getUserByName")
    public Result<GetUserByNameResponseDTO> getUserByName(@Valid @RequestBody GetUserByNameRequestDTO request) {
        return new Result<>(esService.getUserByName(request));
    }

    @PostMapping(value = "search")
    public Result<SearchUserResponseDTO> search(@Valid @RequestBody SearchUserRequestDTO request) {
        return new Result<>(esService.search(request));
    }
}
