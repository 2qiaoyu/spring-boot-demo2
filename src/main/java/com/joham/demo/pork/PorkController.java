package com.joham.demo.pork;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/pork")
public class PorkController {

    @Autowired
    private PorkService porkService;

    @PostMapping("/buy")
    public ResponseEntity<PorkInst> buyPork(@RequestParam("weight") Long weight,
                                            @RequestBody Map<String, Object> params) throws Exception {
        if (weight == null) {
            throw new Exception("invalid input: weight");
        }
        return ResponseEntity.ok(porkService.getPork(weight, params));
    }
}
