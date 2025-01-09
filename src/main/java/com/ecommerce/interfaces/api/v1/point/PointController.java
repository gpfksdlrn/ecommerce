package com.ecommerce.interfaces.api.v1.point;

import com.ecommerce.application.point.ChargeUserAmountResult;
import com.ecommerce.application.point.GetUserAmountResult;
import com.ecommerce.application.point.PointFacade;
import com.ecommerce.interfaces.api.v1.point.req.ChargeUserAmountReq;
import com.ecommerce.interfaces.api.v1.point.res.ChargeUserAmountRes;
import com.ecommerce.interfaces.common.CommonRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/points")
public class PointController {
    private final PointFacade pointFacade;

    @GetMapping("/{userId}/amount")
    public CommonRes<GetUserAmountResult> getUserAmount(@PathVariable Long userId) {
        return CommonRes.success(pointFacade.getUserPoint(userId));
    }

    @PostMapping("/{userId}/charge")
    public CommonRes<ChargeUserAmountRes> chargeUserAmount(@PathVariable Long userId, @RequestBody ChargeUserAmountReq req) {
        ChargeUserAmountResult result = pointFacade.chargeUserPoint(userId, req.chargePoint());
        return CommonRes.success(new ChargeUserAmountRes(result.userId(), result.chargePoint(), result.amount()));
    }
}