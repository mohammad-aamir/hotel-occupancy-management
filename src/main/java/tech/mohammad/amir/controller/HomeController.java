package tech.mohammad.amir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import static tech.mohammad.amir.common.constant.Constants.SWAGGER_UI_REDIRECT_PATH;

@Controller
@ApiIgnore
public class HomeController {
    @GetMapping("/")
    public String home() {
        return SWAGGER_UI_REDIRECT_PATH;
    }
}
