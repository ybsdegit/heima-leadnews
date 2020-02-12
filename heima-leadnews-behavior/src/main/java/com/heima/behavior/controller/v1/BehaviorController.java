package com.heima.behavior.controller.v1;

import com.heima.behavior.apis.BehaviorControllerApi;
import com.heima.behavior.service.AppLikesBehaviorService;
import com.heima.behavior.service.AppShowBehaviorService;
import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/behavior")
public class BehaviorController implements BehaviorControllerApi {

    @Autowired
    private AppShowBehaviorService appShowBehaviorService;

    @Autowired
    private AppLikesBehaviorService appLikesBehaviorService;

    @Override
    @PostMapping("/save_behavior")
    public ResponseResult saveShowBehavior(@RequestBody ShowBehaviorDto dto) {
        return appShowBehaviorService.saveShowBehavior(dto);
    }

    @Override
    @PostMapping("/like_behavior")
    public ResponseResult saveLikesBehavior(LikesBehaviorDto dto) {
        return appLikesBehaviorService.saveLikesBehavior(dto);
    }
}
