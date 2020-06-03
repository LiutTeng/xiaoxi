package com.xiaoxi.study.controller;

import com.xiaoxi.study.common.Result;
import com.xiaoxi.study.dto.request.*;
import com.xiaoxi.study.dto.response.*;
import com.xiaoxi.study.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author liuteng
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "用户信息接口", tags = "UserController")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户", notes = "创建用户")
    public Result<CreateUserResponseDTO> create(@ApiParam(required = true, name = "request", value = "创建用户请求实体")
                                                @Valid @RequestBody CreateUserRequestDTO request){
        return new Result<>(userService.save(request));
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    @ApiOperation(value = "通过ID查询用户", notes = "通过ID查询用户")
    public Result<GetUserByIdResponseDTO> getUserById(@ApiParam(required = true, name = "request", value = "查询用户信息请求实体")
                                                      @Valid @RequestBody GetUserByIdRequestDTO request){
        return new Result<>(userService.getUserById(request));
    }

    @PostMapping(value = "getUserByPhone")
    @ApiOperation(value = "通过手机号查询用户", notes = "通过手机号查询用户")
    public Result<GetUserByPhoneResponseDTO> getUserByPhone(@ApiParam(required = true, name = "request", value = "查询用户信息请求实体")
                                                            @Valid @RequestBody GetUserByPhoneRequestDTO request) {
        return new Result<>(userService.getUserByPhone(request));
    }

    @PostMapping(value = "getUserByName")
    @ApiOperation(value = "通过姓名查询用户", notes = "通过姓名查询用户")
    public Result<GetUserByNameResponseDTO> getUserByName(@ApiParam(required = true, name = "request", value = "查询用户信息请求实体")
                                                          @Valid @RequestBody GetUserByNameRequestDTO request) {
        return new Result<>(userService.getUserByName(request));
    }

    @PostMapping(value = "search")
    @ApiOperation(value = "条件分页查询", notes = "条件分页查询")
    public Result<SearchUserResponseDTO> search(@ApiParam(required = true, name = "request", value = "查询用户信息请求实体")
                                                @Valid @RequestBody SearchUserRequestDTO request) {
        return new Result<>(userService.search(request));
    }
}
