package com.example.demo;


import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("test")
public class SampleController {
    @GetMapping
    public ModelAndView aaa() {
        Map<String, Object> models = new HashMap<>();

        models.put("age", 20);
        models.put("name", "owen.qqq");

        return new ModelAndView("main", models);
    }
}
