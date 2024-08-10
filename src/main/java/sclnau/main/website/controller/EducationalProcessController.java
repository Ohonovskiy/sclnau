package sclnau.main.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/educational-process")
public class EducationalProcessController {

// ENSURING

    @GetMapping("/ensuring-quality-of-education/internal-system-of-ensuring")
    public String ensuringSystemOfEnsuring(){
        return "/educational-process/ensuring-quality/internal-system-of-ensuring";
    }
}
