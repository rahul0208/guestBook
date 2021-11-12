package org.sample.guestbook;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    private BookEntryDTO savedValue;

    @GetMapping({"/"})
    public String loadBookEntry(Model model, OAuth2AuthenticationToken principal) {
        BookEntryDTO dto = new BookEntryDTO();
        dto.setName(principal.getPrincipal().getAttributes().get("name").toString());
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