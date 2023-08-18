package shop.mtcoding.ajax.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.ajax.dto.TechResponse;
import shop.mtcoding.ajax.model.Category;
import shop.mtcoding.ajax.model.CategoryRepository;
import shop.mtcoding.ajax.model.Tech;
import shop.mtcoding.ajax.model.TechRepository;

@Controller
public class TechController {
    @Autowired
    private TechRepository techRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("v1/test/tech")
    public @ResponseBody TechResponse.MainDTO techV1() {
        List<Category> categoryList = categoryRepository.findAll();
        List<Tech> techList = techRepository.findByCategoryId(1);
        System.out.println("=================================");
        TechResponse.MainDTO mainDTO = new TechResponse.MainDTO(categoryList, techList);
        return mainDTO; // messageconverter 발동 - json 변환
    }

    @GetMapping("/test/techlazy")
    public @ResponseBody List<Tech> techlazy() {
        // List<Category> categoryList = categoryRepository.findAll();
        List<Tech> techList = techRepository.findByCategoryId(1);
        System.out.println("=================================");
        return techList; // messageconverter 발동 - json 변환

        // return 할 때 json변환시 lazyloading되기때문에 카테고리 추가 조회되기 때문에
        // 엔티티 레이지로딩이 걸림 - DTO를 만들어야 함, 하나의 항아리가 필요함
        // 핵심 : lazyloading을 안하기 위해서 DTO로 변환
        // DTO는 영속화된 객체가 아님
    }

    @GetMapping("v2/test/tech")
    public @ResponseBody List<Tech> techV2() {
        List<Tech> techList = techRepository.findByCategoryId(1);
        return techList;
    }

    // ajax
    // 1. 빈껍데기 디자인을 준다 ( 데이터없음 )
    @GetMapping("/tech")
    public String tech() {
        return "main";
    }

}