package com.xiaoxi.study.service;

import com.xiaoxi.study.dto.request.GetUserByNameRequestDTO;
import com.xiaoxi.study.dto.request.GetUserByPhoneRequestDTO;
import com.xiaoxi.study.dto.request.SearchUserRequestDTO;
import com.xiaoxi.study.dto.response.GetUserByNameResponseDTO;
import com.xiaoxi.study.dto.response.GetUserByPhoneResponseDTO;
import com.xiaoxi.study.dto.response.SearchUserResponseDTO;


/**
 * @author liuteng
 */
public interface EsService {

    GetUserByPhoneResponseDTO getUserByPhone(GetUserByPhoneRequestDTO request);

    GetUserByNameResponseDTO getUserByName(GetUserByNameRequestDTO request);

    SearchUserResponseDTO search(SearchUserRequestDTO request);
}
