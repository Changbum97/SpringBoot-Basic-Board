package com.study.basicboard.controller;

import com.study.basicboard.domain.dto.BoardDto;
import com.study.basicboard.domain.entity.Board;
import com.study.basicboard.domain.enum_class.BoardCategory;
import com.study.basicboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

        model.addAttribute("category", stringCategory);
        model.addAttribute("boards", boardService.getBoardList(category));
        return "boards/list";
    }

    @GetMapping("/{category}/{boardId}")
    public String boardDetailPage(@PathVariable String category, @PathVariable Long boardId, Model model,
                                  Authentication auth) {
        if (auth != null) {
            model.addAttribute("loginUserLoginId", auth.getName());
        }

        BoardDto boardDto = boardService.getBoard(boardId, category);
        // id에 해당하는 게시글이 없거나 카테고리가 일치하지 않는 경우
        if (boardDto == null) {
            model.addAttribute("message", "해당 게시글이 존재하지 않습니다");
            model.addAttribute("nextUrl", "/boards/" + category);
            return "printMessage";
        }

        model.addAttribute("boardDto", boardDto);
        model.addAttribute("category", category);
        return "boards/detail";
    }

//    @PostMapping("/{category}/{boardId}")
}
