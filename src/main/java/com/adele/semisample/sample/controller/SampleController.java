package com.adele.semisample.sample.controller;

import com.adele.semisample.sample.dto.SampleSearchCondition;
import com.adele.semisample.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SampleController {
    private final SampleService sampleService;
    @GetMapping("/")
    public String sampleAll(Model model) {
        model.addAttribute("samples", sampleService.selectAll());
        return "sample/home";
    }
    @GetMapping("/page/{currentPage}")
    public String samplePage(@PathVariable int currentPage, Model model) {
        model.addAttribute("samples", sampleService.selectPage(currentPage));
        return "sample/page";
    }

    @GetMapping("/pagesearch/{currentPage}")
    public String samplePageSearch(@PathVariable int currentPage, @ModelAttribute SampleSearchCondition searchCondition, Model model) {
        model.addAttribute("samples", sampleService.selectPageSearch(currentPage, searchCondition));
        return "sample/page";
    }
}
