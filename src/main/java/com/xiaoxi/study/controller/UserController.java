package com.xiaoxi.study.controller;

import com.xiaoxi.study.common.Result;
import com.xiaoxi.study.dto.request.CreateUserRequestDTO;
import com.xiaoxi.study.dto.request.GetUserByIdRequestDTO;
import com.xiaoxi.study.dto.response.CreateUserResponseDTO;
import com.xiaoxi.study.dto.response.GetUserByIdResponseDTO;
import com.xiaoxi.study.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author liuteng
 */
@RestController
@RequestMapping(value = "/demo")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result<CreateUserResponseDTO> create(@Valid @RequestBody CreateUserRequestDTO request){
        return new Result<>(userService.save(request));
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    public Result<GetUserByIdResponseDTO> getUserById(@Valid @RequestBody GetUserByIdRequestDTO request){
        return new Result<>(userService.getUserById(request));
    }
}
