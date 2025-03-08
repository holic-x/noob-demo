package com.noob.base.qrcode.controller;

import com.noob.base.qrcode.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/generate")
    public String generateQRCode() throws Exception {
        String token = "random_token_123"; // 生成一个随机token
        return qrCodeService.generateQRCode(token);
    }
}