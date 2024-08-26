package com.adele.semisample.sample.controller;

import com.adele.semisample.common.exception.BusinessException;
import com.adele.semisample.common.exception.ErrorCode;
import com.adele.semisample.sample.dto.SampleSearchCondition;
import com.adele.semisample.sample.dto.SampleWriteDTO;
import com.adele.semisample.sample.service.SampleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SampleController {
    private final SampleService sampleService;
    @GetMapping("/")
    public String sampleAll(Model model) {
        model.addAttribute("samples", sampleService.selectAll());
        throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        // return "sample/home";
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

    @PostMapping("/write")
    @ResponseBody
    public ResponseEntity<String> write(@RequestBody @Valid SampleWriteDTO dto) {
        if("error".equals(dto.getContent())){
            throw new RuntimeException();
        }
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/writePage")
    public String writePage() {
        return "sample/write";
    }

    @PostMapping("/writePage")
    public String writePage(@ModelAttribute @Valid SampleWriteDTO dto) {
        if("error".equals(dto.getContent())){
            throw new RuntimeException("exception occur");
        }
        return "sample/home";
    }
}
