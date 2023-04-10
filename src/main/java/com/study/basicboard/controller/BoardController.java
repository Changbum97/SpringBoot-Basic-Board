package com.study.basicboard.controller;

import com.study.basicboard.domain.entity.Board;
import com.study.basicboard.domain.enum_class.BoardCategory;
import com.study.basicboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{stringCategory}")
    public String boardListPage(@PathVariable String stringCategory, Model model) {

        BoardCategory category;
        if (stringCategory.equalsIgnoreCase("FREE")) category = BoardCategory.FREE;
        else if (stringCategory.equalsIgnoreCase("GOLD")) category = BoardCategory.GOLD;
        else category = BoardCategory.GREETING;

        model.addAttribute("category", stringCategory.toLowerCase());
        model.addAttribute("boards", boardService.getBoardList(category));
        return "boards/list";
    }
}
