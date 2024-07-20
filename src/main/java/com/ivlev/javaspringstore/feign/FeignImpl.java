package com.ivlev.javaspringstore.feign;

import com.ivlev.javaspringstore.model.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "message", url = "${bot.url}")
public interface FeignImpl {

    @PostMapping("/send")
    void sendMessage(@RequestBody MessageDto messageDto);

}
