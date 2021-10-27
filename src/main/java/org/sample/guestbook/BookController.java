package org.sample.guestbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class BookController {
    private BookEntryDTO savedValue;

    @GetMapping({"/"})
    public String loadBookEntry(Model model, @AuthenticationPrincipal OidcUser principal) {
        BookEntryDTO dto = new BookEntryDTO();
        dto.setName(principal.getName());
        model.addAttribute("bookentry", dto);
        model.addAttribute("latest", savedValue);
        return "bookentry";
    }

    @PostMapping({"/"})
    public String saveBookEntry(BookEntryDTO entry, Model model) {
        savedValue = entry;
        return "redirect:/";
    }
}